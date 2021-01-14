package com.example.sentenser.service;

import edu.stanford.nlp.pipeline.CoreDocument;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.util.PropertiesUtils;
import org.springframework.stereotype.Service;

import java.util.*;

@Service()
public class NLPService {

    public CoreDocument splitIntoSentence(String string) {
        Properties props = new Properties();
        props.setProperty("annotators", "tokenize,ssplit");

        CoreDocument doc =  new CoreDocument(string);
        this.processPipeline(doc, props);

        return doc;
    }

    public CoreDocument createPersonNERFromTokens(String preTokenizedInput) {
        Properties props = PropertiesUtils.asProperties(
                "annotators", "tokenize, ssplit, pos, lemma, ner",
                // didn't work out for this model, stuck in here for a while
//                "ner.model", "edu/stanford/nlp/models/srparser/englishSR.ser.gz",
                // for space-separated tokens
                "ssplit.eolonly", "true",
                "tokenize.whitespace", "true"
        );

        CoreDocument coreDocument = new CoreDocument(preTokenizedInput);
        this.processPipeline(coreDocument, props);

        return coreDocument;
    }


    public void processPipeline(CoreDocument doc, Properties props) {
        StanfordCoreNLP core = new StanfordCoreNLP(props);
        core.annotate(doc);
    }
}
