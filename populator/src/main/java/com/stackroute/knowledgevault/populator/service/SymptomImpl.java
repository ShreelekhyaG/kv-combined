package com.stackroute.knowledgevault.populator.service;

import com.stackroute.knowledgevault.domain.MedicalSymptom;
import com.stackroute.knowledgevault.populator.repository.SymptomRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SymptomImpl implements SymptomService {
    private SymptomRepo symptomRepo;
    @Autowired
    public SymptomImpl(SymptomRepo symptomRepo){
        this.symptomRepo=symptomRepo;
    }
    @Override
    public MedicalSymptom saveSymptom(MedicalSymptom medicalSymptom) {
        return symptomRepo.save(medicalSymptom);
    }

    @Override
    public List<MedicalSymptom> symptomList() {
        List<MedicalSymptom> medicalSymptomList = (List)symptomRepo.findAll();
        return medicalSymptomList;
    }
}
