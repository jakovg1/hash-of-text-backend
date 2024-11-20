package com.hashstring.model.request;

import jakarta.validation.constraints.NotBlank;

public class HashOfStringRequest {
    @NotBlank(message = "Input must not be empty")
    private String inputString;

    public HashOfStringRequest(String inputString) {
        this.inputString = inputString;
    }

    public @NotBlank String getInputString() {
        return inputString;
    }

    public void setInputString(@NotBlank String inputString) {
        this.inputString = inputString;
    }
}
