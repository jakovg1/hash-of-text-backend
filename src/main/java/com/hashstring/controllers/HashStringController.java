package com.hashstring.controllers;

import com.hashstring.exceptions.NoSuchHashAlgorithmException;
import com.hashstring.model.responses.HashStringResponse;
import com.hashstring.model.responses.SupportedAlgorithmsResponse;
import com.hashstring.services.HashStringService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/hash")
@Tag(name = "Hash API", description = "API for generating hashes and managing hash algorithms")
public class HashStringController {

    @Autowired
    private HashStringService hashStringService;

    @PostMapping("")
    @Operation(summary = "Generate hash", description = "Generates a hash of the provided string input using the currently selected algorithm.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully generated hash"),
            @ApiResponse(responseCode = "400", description = "Invalid input or empty string")
    })
    public ResponseEntity<HashStringResponse> getHash(@RequestBody String input) {
        if (input == null || input.isEmpty()) {
            return ResponseEntity.badRequest().body(null);
        }
        HashStringResponse response = this.hashStringService.getHashOfString(input);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/setAlgorithm")
    @Operation(summary = "Set hash algorithm", description = "Sets the hash algorithm to be used for future hash generation. Supported algorithms include MD5, SHA-1, SHA-256, etc.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Algorithm set successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid algorithm or unsupported algorithm specified")
    })
    public ResponseEntity<String> setHashAlgorithm(@RequestBody String algorithm) {
        try {
            this.hashStringService.setHashAlgorithm(algorithm);
            return ResponseEntity.ok("Algorithm set to: " + algorithm);
        } catch (NoSuchHashAlgorithmException e) {
            return ResponseEntity.badRequest().body("Invalid algorithm or algorithm not supported: " + algorithm);
        }
    }

    @GetMapping("/getAlgorithm")
    @Operation(summary = "Get current hash algorithm", description = "Retrieves the name of the currently selected hash algorithm.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved current algorithm")
    })
    public ResponseEntity<String> getHashAlgorithm() {
        String hashAlgorithm = this.hashStringService.getHashAlgorithm();
        return ResponseEntity.ok(hashAlgorithm);
    }

    @GetMapping("/supportedAlgorithms")
    @Operation(summary = "Get supported algorithms", description = "Lists all supported hash algorithms that can be used for hash generation.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved supported algorithms")
    })
    public ResponseEntity<SupportedAlgorithmsResponse> getSupportedHashAlgorithms() {
        SupportedAlgorithmsResponse response = this.hashStringService.getSupportedHashAlgorithms();
        return ResponseEntity.ok(response);
    }
}
