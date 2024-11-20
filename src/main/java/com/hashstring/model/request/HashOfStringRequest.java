package com.hashstring.model.request;

import jakarta.validation.constraints.NotBlank;

public class HashOfStringRequest {
    @NotBlank(message = "Input must not be empty")
    private String input;

    public HashOfStringRequest(String inputString) {
        this.input = inputString;
    }

    public @NotBlank String getInput() {
        return input;
    }

    public void setInput(@NotBlank String input) {
        this.input = input;
    }
}
