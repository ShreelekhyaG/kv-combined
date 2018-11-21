package com.stackroute.knowledgevault.extractorservice.controller;

import com.stackroute.knowledgevault.domain.ExtractedFileData;
import com.stackroute.knowledgevault.extractorservice.service.ExtractorService;
import org.apache.tika.exception.TikaException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;
import org.xml.sax.SAXException;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping(value = "extractor-service")
public class ExtractorController {

    @Autowired
    private ExtractorService service;

    @Autowired
    private KafkaTemplate<String, ExtractedFileData> kafkaTemplate;

    private static final String TOPIC = "extracted";

    private String initialPath = "/knowledge-vault/extractor-service/src/main/resources/";

    /* Fetches all the files from the specified folder in path */
    @GetMapping("{path}")
    public ResponseEntity<?> displayAllFiles(@PathVariable("path") String path) {

        List<File> allFiles = service.getAllFiles(initialPath + path); //Fetching all files from the specified path

        return new ResponseEntity<>(allFiles, HttpStatus.OK);
    }

    /* Extract all the files present inside the folder specified in the path */
    @GetMapping(value = "{path}/extract")
    public ResponseEntity<?> extractAllFiles(@PathVariable("path") String path) {

        ResponseEntity responseEntity = null;
        List<File> allFiles = service.getAllFiles(initialPath + path);
        HashMap<Integer, String> extractedDocs = new HashMap<>();

        try {
            for (File instance : allFiles) {
                    ExtractedFileData data = service.extractOneFile(instance); //Fetching metadata and content
                    extractedDocs.put(data.getId(), instance.getName());

                    /* The following line will send our ExtractedFileData object containing all the information about
                    the document to the Kafka server */
                    //kafkaTemplate.send(TOPIC, data);
            }
            responseEntity = new ResponseEntity<HashMap<Integer, String>>(extractedDocs, HttpStatus.OK);
        }
        catch (IOException e) {
            responseEntity = new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        catch (TikaException | SAXException e) {
            responseEntity = new ResponseEntity<String>(e.getMessage(), HttpStatus.EXPECTATION_FAILED);
        }

        return responseEntity;
    }

    /* Will display the document type of all the files inside the folder specified in the path according to the conventions
    of the tika library */
    @GetMapping("{path}/docTypes")
    public ResponseEntity<?> displayDocTypes(@PathVariable("path") String path) {

        ResponseEntity responseEntity;
        List<File> allFiles = service.getAllFiles(initialPath + path);
        List<String> docTypes = null;
        try {
            docTypes = service.detectDocType(allFiles); //Fetching document types
            responseEntity = new ResponseEntity<List<String>>(docTypes, HttpStatus.OK);
        } catch (IOException e) {
            responseEntity = new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (TikaException e) {
            responseEntity = new ResponseEntity<String>(e.getMessage(), HttpStatus.EXPECTATION_FAILED);
        }

        return responseEntity;
    }

    /* Extracts the particular file specified in the path */
    @GetMapping("{path}/extract/{file}")
    public ResponseEntity<?> extractFile(@PathVariable("path") String path, @PathVariable("file") File file) {

        ExtractedFileData data;
        ResponseEntity responseEntity = null;
        List<File> allFiles = service.getAllFiles(initialPath + path);

        try {
            for (File instance : allFiles) {
                //Looking for the file inside the folder specified in the path
                if (instance.getName().equals(file.getName())) {
                    data = service.extractOneFile(instance);

                    kafkaTemplate.send(TOPIC, data);
                    kafkaTemplate.send("extracted2", data);
                    responseEntity = new ResponseEntity<ExtractedFileData>(data, HttpStatus.OK);
                    break;
                }

            }
        }
        catch (IOException e) {
            responseEntity = new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        catch (TikaException | SAXException e) {
            responseEntity = new ResponseEntity<String>(e.getMessage(), HttpStatus.EXPECTATION_FAILED);
        }

        return responseEntity;
    }

}