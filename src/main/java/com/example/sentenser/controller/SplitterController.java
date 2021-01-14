package com.example.sentenser.controller;

import javax.validation.Valid;

import com.example.sentenser.document.modifier.SentenceModifier;
import com.example.sentenser.exception.ModifyException;
import com.example.sentenser.model.RawText;
import com.example.sentenser.model.Text;
import com.example.sentenser.service.DocumentModifier;
import com.example.sentenser.service.NLPService;
import edu.stanford.nlp.pipeline.CoreDocument;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE, value = "/api")

@Validated
public class SplitterController {

    final NLPService NLPService;
    final DocumentModifier documentModifier;

    public SplitterController(com.example.sentenser.service.NLPService nlpService, DocumentModifier documentModifier) {
        this.NLPService = nlpService;
        this.documentModifier = documentModifier;
    }

    @PostMapping(value = "/splitter", produces = "application/json")
    public ResponseEntity<Text> create(@Valid @RequestBody RawText text) {
        Text parsedData = new Text();
        try {
            CoreDocument coreDocument = NLPService.splitIntoSentence(text.getTextData());
            List<String> modify = documentModifier.modify(coreDocument, new SentenceModifier());
            parsedData.setData(modify);
        } catch (ModifyException e){
            return new ResponseEntity<>(parsedData, HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(parsedData, HttpStatus.OK);
    }
}
