package com.hashstring.demo.model.responses;

public class HashStringResponse {
    private String input;
    private String hashOfInput;
    private String hashAlgorithm;

    public HashStringResponse(String input, String hashOfString, String hashAlgorithm) {
        this.input = input;
        this.hashOfInput = hashOfString;
        this.hashAlgorithm = hashAlgorithm;
    }

    public String getInput() {
        return input;
    }

    public void setInput(String input) {
        this.input = input;
    }

    public String getHashOfInput() {
        return hashOfInput;
    }

    public void setHashOfInput(String hashOfInput) {
        this.hashOfInput = hashOfInput;
    }

    public String getHashAlgorithm() {
        return hashAlgorithm;
    }

    public void setHashAlgorithm(String hashAlgorithm) {
        this.hashAlgorithm = hashAlgorithm;
    }
}
