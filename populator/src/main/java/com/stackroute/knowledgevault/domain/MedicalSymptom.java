package com.stackroute.knowledgevault.domain;

import lombok.*;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.Index;
import org.neo4j.ogm.annotation.NodeEntity;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@NodeEntity
public class MedicalSymptom {
    @Id
    @Index(unique=true, primary=true)
    private String symptomName;
    private String type;

//    public MedicalSymptom( String symptomName,String type) {
//        this.type = type;
//        this.symptomName = symptomName;
//    }
}
