package com.example.sentenser.document.modifier;

import com.example.sentenser.exception.ModifyException;
import edu.stanford.nlp.pipeline.CoreDocument;

import java.util.ArrayList;
import java.util.List;

public class SentenceModifier implements DataModifier {

    @Override
    public List<String> modify(CoreDocument coreDocument) throws ModifyException {
        List<String> result = new ArrayList<>();
        coreDocument.sentences().forEach(e -> result.add(e.text()));

        if (result.isEmpty()){
            throw new ModifyException("Cannot obtain sentences");
        }

        return result;
    }
}
