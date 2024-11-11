package com.hashstring.demo.controllers;

import com.hashstring.demo.model.responses.HashStringResponse;
import com.hashstring.demo.model.responses.SupportedAlgorithmsResponse;
import com.hashstring.demo.services.HashStringService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/hash")
public class HashStringController {

    @Autowired
    private HashStringService hashStringService;

    @PostMapping("/")
    public HashStringResponse getHash(@RequestBody String input) {
        return this.hashStringService.getHashOfString(input);
    }

    @PostMapping("/setAlgorithm")
    private void setHashAlgorithm(@RequestBody String algorithm) {
        this.hashStringService.setHashAlgorithm(algorithm);
    }

    @GetMapping("/getAlgorithm")
    private String getHashAlgorithm(){
        return this.hashStringService.getHashAlgorithm();
    }

    @GetMapping("/supportedAlgorithms")
    private SupportedAlgorithmsResponse getSupportedHashAlgorithms() {
        return this.hashStringService.getSupportedHashAlgorithms();
    }

}


