package com.example.sentenser.model;

import java.util.ArrayList;
import java.util.List;

public class Text {
    protected List<String> data;

    public Text() {
        this.data = new ArrayList<>();
    }

    public Text(List<String> data) {
        this.data = data;
    }

    public List<String> getData() {
        return data;
    }

    public void setData(List<String> data) {
        this.data = data;
    }
}
