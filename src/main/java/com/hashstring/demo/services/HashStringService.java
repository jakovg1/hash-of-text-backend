package com.hashstring.demo.services;

import com.hashstring.demo.exceptions.NoSuchHashAlgorithmException;
import com.hashstring.demo.model.responses.HashStringResponse;
import com.hashstring.demo.model.responses.SupportedAlgorithmsResponse;
import org.springframework.stereotype.Service;
import java.security.MessageDigest;

import java.security.NoSuchAlgorithmException;
import java.util.List;

@Service
public class HashStringService {
    private final List<String> supportedHashAlgorithms = List.of("MD5", "SHA-1", "SHA-256", "SHA-384", "SHA-512", "RIPEMD160");
    private String hashAlgorithm = "MD5";

    public void setHashAlgorithm(String algorithm) {
        if(!this.supportedHashAlgorithms.contains(algorithm)){
            throw new NoSuchHashAlgorithmException(algorithm);
        }
        this.hashAlgorithm = algorithm;
    }

    public String getHashAlgorithm() {
        return this.hashAlgorithm;
    }

    public SupportedAlgorithmsResponse getSupportedHashAlgorithms(){
        return new SupportedAlgorithmsResponse(this.supportedHashAlgorithms);
    }

    public HashStringResponse getHashOfString(String inputString){
        String hashedString;
        try {
            MessageDigest md = MessageDigest.getInstance(this.hashAlgorithm);
            byte[] hashInBytes = md.digest(inputString.getBytes());
            StringBuilder sb = new StringBuilder();

            for(byte b : hashInBytes){
                sb.append((String.format("%02x", b)));
            }
            hashedString = sb.toString();
        } catch (NoSuchAlgorithmException e) { throw new NoSuchHashAlgorithmException(this.hashAlgorithm);}
        return new HashStringResponse(inputString, hashedString, this.hashAlgorithm);
    }


}
