package com.hashstring.service;

import com.hashstring.entity.HashAlgorithm;
import com.hashstring.entity.HashLog;
import com.hashstring.exception.NoSuchHashAlgorithmException;
import com.hashstring.model.response.HashOfStringResponse;
import com.hashstring.model.response.SupportedAlgorithmsResponse;
import com.hashstring.repository.HashAlgorithmRepository;
import com.hashstring.repository.HashLogRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.security.MessageDigest;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

@Service
public class HashStringService {
    private List<String> supportedHashAlgorithms;
    private String hashAlgorithm;

    @Autowired
    private HashAlgorithmRepository hashAlgorithmRepository;

    @Autowired
    private HashLogRepository hashLogRepository;

    @PostConstruct
    private void loadSupportedHashAlgorithms() {
        this.supportedHashAlgorithms = new ArrayList<>();
        List<HashAlgorithm> supportedHashAlgorithmEntities = hashAlgorithmRepository.findAll();
        for (HashAlgorithm hashAlgorithmEntity : supportedHashAlgorithmEntities) {
            this.supportedHashAlgorithms.add(hashAlgorithmEntity.getName());
        }
        this.hashAlgorithm = this.supportedHashAlgorithms.getFirst();
    }

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

    public List<String> getSupportedAlgorithms(){
        return this.supportedHashAlgorithms;
    }

    public HashOfStringResponse getHashOfString(String inputString){
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

        } catch (NoSuchAlgorithmException e) { throw new NoSuchHashAlgorithmException(hashAlgorithm);}
        return new HashOfStringResponse(inputString, hashedString, hashAlgorithm);
    }


}
