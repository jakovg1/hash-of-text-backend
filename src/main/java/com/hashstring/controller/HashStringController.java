package com.hashstring.controller;

import com.hashstring.exception.NoSuchHashAlgorithmException;
import com.hashstring.model.request.HashAlgorithmRequest;
import com.hashstring.model.request.HashOfStringRequest;
import com.hashstring.model.response.HashAlgorithmResponse;
import com.hashstring.model.response.HashOfStringResponse;
import com.hashstring.model.response.SupportedAlgorithmsResponse;
import com.hashstring.service.HashStringService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hash-string/")
@Tag(name = "Hash API", description = "API for generating hashes and managing hash algorithms")
public class HashStringController {

    @Autowired
    private HashStringService hashStringService;

    @PostMapping("/hash")
    @Operation(summary = "Generate hash of input string", description = "Generates a hash of the input string using the currently selected algorithm.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully generated hash"),
            @ApiResponse(responseCode = "400", description = "Invalid input or empty string")
    })
    public ResponseEntity<HashOfStringResponse> getHashOfString(@RequestBody @Valid HashOfStringRequest request) {
        HashOfStringResponse response = this.hashStringService.getHashOfString(request.getInputString());
        return ResponseEntity.ok(response);
    }

    @PostMapping("/algorithm")
    @Operation(summary = "Set hash algorithm", description = "Sets the hash algorithm to be used for future hash generation. Supported algorithms include MD5, SHA-1, SHA-256, etc.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Algorithm set successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid or unsupported algorithm")
    })
    public ResponseEntity<HashAlgorithmResponse> setHashAlgorithm(@RequestBody @Valid HashAlgorithmRequest request) {
        String algorithm = request.getAlgorithm();
        try {
            this.hashStringService.setHashAlgorithm(algorithm);
            return ResponseEntity.ok().build();
        } catch (NoSuchHashAlgorithmException e) {
            HashAlgorithmResponse response = new HashAlgorithmResponse(algorithm);
            response.setMessage("Invalid or unsupported algorithm: " + algorithm);
            return ResponseEntity.badRequest().body(response);
        }
    }

    @GetMapping("/algorithm")
    @Operation(summary = "Get hash algorithm", description = "Retrieves the name of the currently selected hash algorithm.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved current algorithm")
    })
    public ResponseEntity<HashAlgorithmResponse> getHashAlgorithm() {
        String hashAlgorithm = this.hashStringService.getHashAlgorithm();
        HashAlgorithmResponse response = new HashAlgorithmResponse(hashAlgorithm);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/algorithms")
    @Operation(summary = "Get supported hash algorithms", description = "Lists all supported hash algorithms that can be used for hash generation.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved supported algorithms")
    })
    public ResponseEntity<SupportedAlgorithmsResponse> getSupportedHashAlgorithms() {
        List<String> supportedAlgorithms = this.hashStringService.getSupportedAlgorithms();
        SupportedAlgorithmsResponse response = SupportedAlgorithmsResponse.fromStrings(supportedAlgorithms);
        return ResponseEntity.ok(response);
    }
}
