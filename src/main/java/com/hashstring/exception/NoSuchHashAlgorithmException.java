package com.hashstring.exception;

public class NoSuchHashAlgorithmException extends RuntimeException{
    public NoSuchHashAlgorithmException(String algorithm) {
        super("No such hashing algorithm: " + algorithm);
    }
}
