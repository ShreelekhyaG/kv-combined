package com.stackroute.knowledgevault.populator.controller;

import com.github.jsonldjava.core.JsonLdOptions;
import com.github.jsonldjava.core.JsonLdProcessor;
import com.github.jsonldjava.utils.JsonUtils;
import com.stackroute.knowledgevault.domain.*;
import com.stackroute.knowledgevault.domain.Cause;
import com.stackroute.knowledgevault.domain.MedicalCondition;
import com.stackroute.knowledgevault.populator.repository.StructureRepo;
import com.stackroute.knowledgevault.populator.service.*;
import org.neo4j.driver.v1.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.*;
import java.util.*;

@RestController
@RequestMapping("/api/v1")
public class Convertor {
    @Autowired
    private MedicalConditionService medicalConditionService;
    @Autowired
    private CauseService causeService;
    @Autowired
    private AnatomyService anatomyService;
    @Autowired
    private SymptomService symptomService;
    @Autowired
    private TreatmentService treatmentService;
    @Autowired
    StructureRepo structureRepo;

   @Autowired
    MedicalGraphService medicalGraphService;

    public Convertor(MedicalGraphService medicalGraphService) {
        this.medicalGraphService = medicalGraphService;
    }

    static Long symptomId=0L;
    static Long anatomyId=1L;
    static Long treatmentId=0L;
    static Long causeId=0L;
    static Long id=1L;
    private Driver driver;
    @Autowired
    ReadJsonld readJsonld;

    @GetMapping("/createmt/{input}")
    public void createMT(@PathVariable(value="input") String input) {
        structureRepo.createMT(input);
    }
    @GetMapping("/createmtr/{input}")
    public void createMTR(@PathVariable(value="input") String input) {
        structureRepo.createMTR(input);
    }
    @GetMapping("/creater/{node1}/{rel}/{node2}")
    public void createStructure(@PathVariable(value="node1") String node1,@PathVariable(value="rel") String rel,@PathVariable(value="node2") String node2){
        structureRepo.createR(node1,rel,node2);
    }
    @GetMapping("/ra/{input}")
    public void input(@PathVariable(value="input") String input) throws IOException {
        InputStream inputStream = new FileInputStream("populator/src/main/Docs/"+input+".json");
        Object jsonObject = JsonUtils.fromInputStream(inputStream);
        Map context = new HashMap();
        Map<String, Object> root = (Map) jsonObject;
        MedicalCondition medicalCondition=readJsonld.getMedicalCondition(root);
        Anatomy anatomy=readJsonld.getAnatomy(root);
        List<MedicalSymptom> medicalSymptomList =readJsonld.getSymptoms(root);
        //medicalGraphService.populate(1L,medicalCondition,anatomy, medicalSymptomList);
        medicalGraphService.makegraph(1,medicalCondition,anatomy,medicalSymptomList);

    }
    @GetMapping("/{input}")
    public void parse(@PathVariable(value="input") String input) throws IOException {
        ResponseEntity responseEntity;
        InputStream inputStream = new FileInputStream("populator/src/main/Docs/"+input+".json");
        Object jsonObject = JsonUtils.fromInputStream(inputStream);
        Map context = new HashMap();
        JsonLdOptions options = new JsonLdOptions();
        Object compact = JsonLdProcessor.compact(jsonObject, context, options);
        String symptomName="",symptomType="";
        boolean anatomyPresent=false,causePresent=false,conditionPresent=false,symptomPresent=false,treatmentPresent=false;
        Anatomy anatomy=null;
        Map<String, Object> root = (Map) jsonObject;
        String type=(String) root.get("@type");
        String name=(String)root.get("alternateName");
        Map<String,String> anatomyMap= (Map<String, String>) root.get("associatedAnatomy");
        String anatomyName=anatomyMap.get("name");
        String anatomyType=anatomyMap.get("@type");
        List<Anatomy> anatomyList=anatomyService.anatomyList();

            anatomy=new Anatomy(anatomyName,anatomyType);
            anatomyService.saveAnatomy(anatomy);

        Map<String,String> codeMap= (Map<String, String>) root.get("code");
        Map<String,List> differentialDiagnosis= (Map<String, List>) root.get("differentialDiagnosis");
        List<Map<String,String>> distinguishingSignList=differentialDiagnosis.get("distinguishingSign");
        Iterator signIterator = distinguishingSignList.iterator();
        List<MedicalSymptom> medicalSymptomList =new ArrayList<>();
        while(signIterator.hasNext()){
            symptomPresent=false;
            Map<String,String> sym= (Map<String, String>) signIterator.next();
            symptomName=sym.get("name");
            symptomType=sym.get("@type");
            List<MedicalSymptom> medicalSymptomList1 =symptomService.symptomList();


                symptomId++;
                MedicalSymptom medicalSymptom = new MedicalSymptom(  symptomName,symptomType);
                symptomService.saveSymptom(medicalSymptom);
                medicalSymptomList.add(medicalSymptom);

        }

        List<Map<String,Object>> treatment= (List<Map<String, Object>>) root.get("possibleTreatment");
        Iterator treatmentIterator=treatment.iterator();
        String treatmentName="",treatmentType="";

        List<Treatment> treatmentList=new ArrayList<>();
        while(treatmentIterator.hasNext()){
            treatmentPresent=false;
            Map<String,Object> stringObjectMap= (Map<String, Object>) treatmentIterator.next();
            treatmentName= (String) stringObjectMap.get("name");
            treatmentType= (String) stringObjectMap.get("@type");
            List<Treatment> treatmentList1=treatmentService.treatmentList();


                treatmentId++;
                ArrayList<Map<String, String>> dose = (ArrayList<Map<String, String>>) stringObjectMap.get("DoseSchedule");
                String doseUnit = dose.get(0).get("doseUnit");
                String frequency = dose.get(1).get("frequency");
                Treatment treatment1 = new Treatment(  treatmentName,treatmentType, doseUnit, frequency);
                treatmentService.saveTreatment(treatment1);
                treatmentList.add(treatment1);

        }



        ArrayList<Map<String, String>> causeList = (ArrayList<Map<String, String>>) root.get("cause");
        Iterator mapIterator = causeList.iterator();
        String causeName="",causeType="";

        List<Cause> causeList1=new ArrayList<>();

        while (mapIterator.hasNext()) {
            causePresent=false;
            Map<String,String> causeMap = (Map<String, String>) mapIterator.next();
            causeName=(String)causeMap.get("name");


                causeType = (String) causeMap.get("@type");
                causeId++;
                Cause cause = new Cause( causeType, causeName);
                causeService.saveCause(cause);
                causeList1.add(cause);

        }
        medicalConditionService.saveCondition(new MedicalCondition(name,type,causeList1,anatomy, medicalSymptomList,treatmentList));
        System.out.println(medicalConditionService.conditionList());
    }

}