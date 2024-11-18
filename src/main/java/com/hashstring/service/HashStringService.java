package com.hashstring.service;

import com.hashstring.entity.HashLog;
import com.hashstring.exception.NoSuchHashAlgorithmException;
import com.hashstring.model.response.HashStringResponse;
import com.hashstring.model.response.SupportedAlgorithmsResponse;
import com.hashstring.repository.HashAlgorithmRepository;
import com.hashstring.repository.HashLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.security.MessageDigest;

import java.security.NoSuchAlgorithmException;
import java.util.List;

@Service
public class HashStringService {
    private final List<String> supportedHashAlgorithms = List.of("MD5", "SHA-1", "SHA-256", "SHA-384", "SHA-512", "RIPEMD160");
    private String hashAlgorithm = "MD5";

    @Autowired
    private HashAlgorithmRepository hashAlgorithmRepository;

    @Autowired
    private HashLogRepository hashLogRepository;

    public void setHashAlgorithm(String algorithm) {
        algorithm = algorithm.trim();
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

            //persist hash in log
            HashLog hashLog = new HashLog();
            hashLog.setInputString(inputString);
            hashLog.setHashedValue(hashedString);
            hashLog.setAlgorithmUsed(hashAlgorithm);
            hashLogRepository.save(hashLog);

        } catch (NoSuchAlgorithmException e) { throw new NoSuchHashAlgorithmException(this.hashAlgorithm);}
        return new HashStringResponse(inputString, hashedString, this.hashAlgorithm);
    }


}
