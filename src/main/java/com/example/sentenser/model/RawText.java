package com.example.sentenser.model;


import javax.validation.constraints.Size;

public class RawText {
    @Size(min = 3, message = "Minimal length is 3")
    protected String textData = "";

    public RawText(String textData) {
        this.textData = textData;
    }

    public RawText() {}

    public String getTextData() {
        return textData;
    }

    public void setTextData(String textData) {
        this.textData = textData;
    }
}
