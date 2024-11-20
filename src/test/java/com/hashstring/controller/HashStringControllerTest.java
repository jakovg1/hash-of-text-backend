package com.hashstring.controller;


import com.hashstring.model.request.HashOfStringRequest;
import com.hashstring.model.response.HashOfStringResponse;
import com.hashstring.model.response.SupportedAlgorithmsResponse;
import com.hashstring.service.HashStringService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(HashStringController.class)
public class HashStringControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private HashStringService hashStringService;

    /*@InjectMocks
    private HashStringController hashStringController;

    public HashStringControllerTest() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(hashStringController)
                .build();
    }*/

    @Test
    public void testGetHash_OfString_ValidInput() throws Exception {
        String input = "inputString";
        String hashedString = "60ff169ad58411fd2225ed8018b24018 ";
        String hashAlgorithm = "MD5";
        HashOfStringResponse hashOfStringResponse = new HashOfStringResponse(input, hashedString, hashAlgorithm);
        HashOfStringRequest request = new HashOfStringRequest(input );

        when(hashStringService.getHashOfString(input)).thenReturn(hashOfStringResponse);

        mockMvc.perform(post("/hash-string/hash")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(input))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.inputString").value(input))
                .andExpect(jsonPath("$.hashedString").value(hashedString))
                .andExpect(jsonPath("$.hashAlgorithm").value(hashAlgorithm));
    }

    @Test
    public void testGetHash_OfString_EmptyInput() throws Exception {
        mockMvc.perform(post("/hash-string/hash")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(""))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testSetHashAlgorithm_ValidAlgorithm() throws Exception {
        String algorithm = "SHA-256";

        mockMvc.perform(post("/hash-string/algorithm")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(algorithm))
                .andExpect(status().isOk());
        verify(hashStringService, times(1)).setHashAlgorithm(algorithm);
    }

    @Test
    public void testSetHashAlgorithm_InvalidAlgorithm() throws Exception {
        String algorithm = "INVALID";

        mockMvc.perform(post("/hash-string/algorithm")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(algorithm))
                .andExpect(status().isBadRequest());
        verify(hashStringService, times(1)).setHashAlgorithm(algorithm);
    }

    @Test
    public void testGetHashOfStringAlgorithm() throws Exception {
        String algorithm = "MD5";
        when(hashStringService.getHashAlgorithm()).thenReturn(algorithm);

        mockMvc.perform(get("/hash-string/algorithm"))
                .andExpect(status().isOk());
        verify(hashStringService, times(1)).setHashAlgorithm(algorithm);
    }

    @Test
    public void testGetSupportedAlgorithms() throws Exception {

        List<String> supportedAlgorithms = List.of("MD5", "SHA-1", "SHA-256", "SHA-384", "SHA-512", "RIPEMD160");

        when(hashStringService.getSupportedAlgorithms()).thenReturn(supportedAlgorithms);

        mockMvc.perform(get("/hash/algorithms"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.algorithms[0]").value("MD5"))
                .andExpect(jsonPath("$.algorithms[1]").value("SHA-1"))
                .andExpect(jsonPath("$.algorithms[2]").value("SHA-256"))
                .andExpect(jsonPath("$.algorithms[3]").value("SHA-384"))
                .andExpect(jsonPath("$.algorithms[4]").value("SHA-512"))
                .andExpect(jsonPath("$.algorithms[5]").value("RIPEMD160"));

    }/**/
}
