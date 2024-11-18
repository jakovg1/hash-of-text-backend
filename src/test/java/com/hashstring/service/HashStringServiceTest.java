package com.hashstring.service;

import com.hashstring.exception.NoSuchHashAlgorithmException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class HashStringServiceTest {

    private final HashStringService hashStringService = new HashStringService();

    @Test
    void hashString_defaultHashIsMD5() {
        String hashAlgorithm = this.hashStringService.getHashAlgorithm();
        assertEquals(hashAlgorithm, "MD5");
    }

    @Test
    void hashString_setHashAlgToSha256() {
        this.hashStringService.setHashAlgorithm("SHA-256");
        String hashAlgorithm = this.hashStringService.getHashAlgorithm();
        assertEquals(hashAlgorithm, "SHA-256");
    }

    @Test
    void hashString_setHashAlgToUnknownValue() {
        assertThrows(NoSuchHashAlgorithmException.class,
                () -> this.hashStringService.setHashAlgorithm("Unknown algorithm"), "Expected NoSuchHashAlgorithmException");
    }
}
