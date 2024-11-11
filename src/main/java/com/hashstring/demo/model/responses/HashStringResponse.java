package com.hashstring.demo.model.responses;

public class HashStringResponse {
    private String inputString;
    private String hashOfString;
    private String hashAlgorithm;

    public HashStringResponse(String inputString, String hashOfString, String hashAlgorithm) {
        this.inputString = inputString;
        this.hashOfString = hashOfString;
        this.hashAlgorithm = hashAlgorithm;
    }

    public String getInputString() {
        return inputString;
    }

    public void setInputString(String inputString) {
        this.inputString = inputString;
    }

    public String getHashOfString() {
        return hashOfString;
    }

    public void setHashOfString(String hashOfString) {
        this.hashOfString = hashOfString;
    }

    public String getHashAlgorithm() {
        return hashAlgorithm;
    }

    public void setHashAlgorithm(String hashAlgorithm) {
        this.hashAlgorithm = hashAlgorithm;
    }
}
