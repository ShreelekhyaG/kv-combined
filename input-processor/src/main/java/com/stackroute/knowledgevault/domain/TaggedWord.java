package com.stackroute.knowledgevault.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaggedWord {
    private Integer sentenceId;
    private Integer wordId;
    private String token;
    private String lemma;
    private String posTag;
    private String tag;
}
