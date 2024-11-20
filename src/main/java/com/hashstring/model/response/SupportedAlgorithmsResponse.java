package com.hashstring.model.response;

import com.hashstring.entity.HashAlgorithm;

import java.util.ArrayList;
import java.util.List;

public class SupportedAlgorithmsResponse {
    private List<String> supportedAlgorithms;

    public List<String> getSupportedAlgorithms() {
        return supportedAlgorithms;
    }

    public void setSupportedAlgorithms(List<String> supportedAlgorithms) {
        this.supportedAlgorithms = supportedAlgorithms;
    }

    public static SupportedAlgorithmsResponse fromStrings(List<String> supportedAlgorithms) {
        return new SupportedAlgorithmsResponse(supportedAlgorithms);
    }

    public static SupportedAlgorithmsResponse fromHashAlgorithms(List<HashAlgorithm> supportedAlgorithms) {
        List<String> supportedAlgorithmsList = new ArrayList<>();
        for (HashAlgorithm hashAlgorithm : supportedAlgorithms) {
            supportedAlgorithmsList.add(hashAlgorithm.getName());
        }
        return new SupportedAlgorithmsResponse(supportedAlgorithmsList);
    }

    public SupportedAlgorithmsResponse(List<String> supportedAlgorithms) {
        this.supportedAlgorithms =  supportedAlgorithms;
    }
}
