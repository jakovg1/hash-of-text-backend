package com.hashstring.model.responses;

import java.util.List;

public class SupportedAlgorithmsResponse {
    private List<String> supportedAlgorithms;

    public List<String> getSupportedAlgorithms() {
        return supportedAlgorithms;
    }

    public void setSupportedAlgorithms(List<String> supportedAlgorithms) {
        this.supportedAlgorithms = supportedAlgorithms;
    }

    public SupportedAlgorithmsResponse(List<String> supportedAlgorithms) {
        this.supportedAlgorithms =  supportedAlgorithms;
    }
}
