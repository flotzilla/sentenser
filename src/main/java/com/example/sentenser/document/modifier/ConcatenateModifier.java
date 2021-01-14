package com.example.sentenser.document.modifier;

import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.pipeline.CoreDocument;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class ConcatenateModifier implements DataModifier {

    @Override
    public List<String> modify(CoreDocument coreDocument){
        List<String> result = new ArrayList<>();

        // get index tokens
        List<Integer> mentionTokens = coreDocument.entityMentions()
                .stream().map(e -> (e.tokens()
                        .stream().map(CoreLabel::index).mapToInt(Integer.class::cast).boxed())
                        .collect(Collectors.toList()))
                .flatMap(List::stream).collect(Collectors.toList());

        // concat items
        List<CoreLabel> tokens = coreDocument.tokens();
        for (int i = 0; i < tokens.size(); ) {
            int lastIndex = this.getMaxIndexFromCurrent(i, mentionTokens);
            if (lastIndex != i) {
                result.add(tokens.subList(i, lastIndex).stream()
                        .map(CoreLabel::word).collect(Collectors.joining("_")));
                i = lastIndex;
                continue;
            }

            result.add(tokens.get(i).word());
            i++;
        }

        return result;
    }


    private int getMaxIndexFromCurrent(int i, List<Integer> tokens) {
        if (i == tokens.size()) {
            return i;
        }

        if (tokens.contains(i + 1)) {
            return getMaxIndexFromCurrent(i + 1, tokens);
        }

        return i;
    }
}
