package com.example.sentenser.model;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

public class SplitTextRequest {

    @NotNull
    protected List<List<String>> data;

    public SplitTextRequest(List<List<String>> data) {
        this.data = data;
    }

    public SplitTextRequest() {
        this.data = new ArrayList<>();
    }

    public List<List<String>> getData() {
        return data;
    }

    public void setData(List<List<String>> data) {
        this.data = data;
    }
}
