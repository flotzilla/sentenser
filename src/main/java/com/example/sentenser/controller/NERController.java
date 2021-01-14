package com.example.sentenser.controller;

import com.example.sentenser.document.modifier.ConcatenateModifier;
import com.example.sentenser.exception.ModifyException;
import com.example.sentenser.model.SplitTextRequest;
import com.example.sentenser.model.SplitTextResponse;
import com.example.sentenser.service.DocumentModifier;
import com.example.sentenser.service.NLPService;
import edu.stanford.nlp.pipeline.CoreDocument;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE, value = "/api")
public class NERController {

    final NLPService NLPService;
    final DocumentModifier documentModifier;

    public NERController(NLPService NLPService, DocumentModifier documentModifier) {
        this.NLPService = NLPService;
        this.documentModifier = documentModifier;
    }

    @PostMapping(value = "/ner_pipeline", produces = "application/json")
    public ResponseEntity<SplitTextResponse> create(@Valid @RequestBody SplitTextRequest text) {
        SplitTextResponse result = new SplitTextResponse();

        try {
            for (List<String> t : text.getData()) {
                CoreDocument doc = NLPService.createPersonNERFromTokens(String.join(" ", t));

                result.getOutput().add(
                        documentModifier.modify(doc, new ConcatenateModifier())
                );
            }
        } catch (ModifyException e) {
            return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
