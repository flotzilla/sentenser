package com.example.sentenser.service;

import com.example.sentenser.document.modifier.DataModifier;
import com.example.sentenser.exception.ModifyException;
import edu.stanford.nlp.pipeline.CoreDocument;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DocumentModifier {

    public List<String> modify(CoreDocument document, DataModifier modifier) throws ModifyException {
        return modifier.modify(document);
    }
}
