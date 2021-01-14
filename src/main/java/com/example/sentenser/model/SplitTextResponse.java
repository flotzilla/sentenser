package com.example.sentenser.model;

import java.util.ArrayList;
import java.util.List;

public class SplitTextResponse {
    protected List<List<String>> output;

    public SplitTextResponse(List<List<String>> data) {
        this.output = data;
    }

    public SplitTextResponse() {
        this.output = new ArrayList<>();
    }

    public List<List<String>> getOutput() {
        return output;
    }

    public void setOutput(List<List<String>> output) {
        this.output = output;
    }
}
