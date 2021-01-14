package com.example.sentenser.document.modifier;

import com.example.sentenser.exception.ModifyException;
import edu.stanford.nlp.pipeline.CoreDocument;

import java.util.List;

public interface DataModifier {

    public List<String> modify(CoreDocument coreDocument) throws ModifyException;
}
