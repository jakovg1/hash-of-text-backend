package com.hashstring.demo.controllers;

import com.hashstring.demo.exceptions.NoSuchHashAlgorithmException;
import com.hashstring.demo.model.responses.HashStringResponse;
import com.hashstring.demo.model.responses.SupportedAlgorithmsResponse;
import com.hashstring.demo.services.HashStringService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/hash")
public class HashStringController {

    @Autowired
    private HashStringService hashStringService;

    @PostMapping("/")
    public ResponseEntity<HashStringResponse> getHash(@RequestBody String input) {
        if (input == null || input.isEmpty()) {
            return ResponseEntity.badRequest().body(null);
        }
        HashStringResponse response =  this.hashStringService.getHashOfString(input);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/setAlgorithm")
    private ResponseEntity<String> setHashAlgorithm(@RequestBody String algorithm) {
        try {
            this.hashStringService.setHashAlgorithm(algorithm);
            return ResponseEntity.ok("Algorithm set to: " + algorithm);
        } catch (NoSuchHashAlgorithmException e) {
            return ResponseEntity.badRequest().body("Invalid algorithm or algorithm not supported: " + algorithm);
        }
    }

    @GetMapping("/getAlgorithm")
    private ResponseEntity<String> getHashAlgorithm(){
        String hashAlgorithm = this.hashStringService.getHashAlgorithm();
        return ResponseEntity.ok(hashAlgorithm);
    }

    @GetMapping("/supportedAlgorithms")
    private ResponseEntity<SupportedAlgorithmsResponse> getSupportedHashAlgorithms() {
        SupportedAlgorithmsResponse response = this.hashStringService.getSupportedHashAlgorithms();
        return ResponseEntity.ok(response);
    }

}


