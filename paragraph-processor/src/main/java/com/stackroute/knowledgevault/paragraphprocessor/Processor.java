package com.stackroute.knowledgevault.paragraphprocessor;

import com.stackroute.knowledgevault.paragraphprocessor.algos.FullTextSearch;
import com.stackroute.knowledgevault.paragraphprocessor.algos.FullTextSearchImpl;
import com.stackroute.knowledgevault.paragraphprocessor.algos.POSTagging;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

public class Processor {

    private static final Logger LOGGER = LoggerFactory.getLogger(Processor.class);

    private FullTextSearchImpl fullTextSearch = new FullTextSearchImpl();

    public void setFullTextSearch(FullTextSearchImpl fullTextSearch) {
        this.fullTextSearch = fullTextSearch;
    }

    public FullTextSearch getFullTextSearch() {
        return this.fullTextSearch;
    }

    public Processor() {
        getFullTextSearch().setFilesPath("src/main/java/com/stackroute/knowledgevault/paragraphprocessor/assets/taggerResource");
        getFullTextSearch().setIndexPath("src/main/java/com/stackroute/knowledgevault/paragraphprocessor/assets/taggerIndices");
        getFullTextSearch().indexer();
    }

    /**
     * This utility function spits out the relevant information
     * with proper tagging from the input paragraph
     * @param paragraph: the input paragraph
     * @return: tagged keywords that makes sense
     */
    public Map<String,String> paraProcessing(String paragraph) {

        Map<String,String> tags = new HashMap<>();
        Map<String,String> pos_tagged = POSTagging.tagger(paragraph);
        List<String> keywords = new ArrayList<>();
        for(Map.Entry<String,String> entry: pos_tagged.entrySet()) {
            String pos = entry.getValue();
            if(pos.compareTo("NN")==0 || pos.compareTo("NNS")==0 || pos.compareTo("JJ")==0) {
                keywords.add(entry.getKey());
            }
        }

        for(String keyword: keywords) {
            String tag = keywordMapping(keyword);
            LOGGER.info("tagged value: {}",tag);

            tags.put(keyword,keywordMapping(keyword));
        }

        for (Map.Entry<String,String> entry : tags.entrySet()) {
            LOGGER.info("{  word : {} , tag : {} }", entry.getKey(), entry.getValue());
        }
        return tags;
    }

    public String patMatch(String key) {
        String pat = "^(\\d+)(?=([day]|[week]|[month]|[year]))";
        Pattern pattern = Pattern.compile(pat);
        if(pattern.matcher(key).matches()) {
            return "duration";
        }
        return "not found";
    }

    public String keywordMapping(String keyword) {
        String tag = getFullTextSearch().search(keyword).get(0);
        LOGGER.info(tag);
        if(tag.compareTo("not found")==0) {
            return patMatch(tag);
        }
        return tag;
    }
}
