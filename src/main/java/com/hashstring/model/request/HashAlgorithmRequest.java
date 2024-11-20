package com.hashstring.model.request;

import jakarta.validation.constraints.NotBlank;

public class HashAlgorithmRequest {
    @NotBlank(message = "Algorithm must not be empty")
    private String algorithm;

    public HashAlgorithmRequest() {}

    public HashAlgorithmRequest(String algorithm) {
        this.algorithm = algorithm;
    }

    public @NotBlank String getAlgorithm() {
        return algorithm;
    }

    public void setAlgorithm(@NotBlank String algorithm) {
        this.algorithm = algorithm;
    }
}
