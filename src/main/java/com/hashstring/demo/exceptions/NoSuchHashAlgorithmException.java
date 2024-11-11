package com.hashstring.demo.exceptions;

public class NoSuchHashAlgorithmException extends RuntimeException{
    public NoSuchHashAlgorithmException(String algorithm) {
        super("No such hashing algorithm: " + algorithm);
    }
}
