package org.example.serviceshuffle;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.serviceshuffle.dto.ShuffleRequest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.example.serviceshuffle.utils.ConstantValues.INVALID_NUMBER_MESSAGE;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class ShuffleServiceIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void successShuffleEndpoint() throws Exception {
        int number = 1;
        ShuffleRequest request = new ShuffleRequest(number);
        mockMvc.perform(post("/shuffle")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(number))
                .andExpect(jsonPath("$").isArray());
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 1001})
    void valueIsOutOfRange(int number) throws Exception {
        ShuffleRequest request = new ShuffleRequest(number);
        String requestStringValue = new ObjectMapper().writeValueAsString(request);
        sendRequestAndVerifyInvalidNumberMessage(requestStringValue);
    }

    @Test
    void missedNumberValue() throws Exception {
        sendRequestAndVerifyInvalidNumberMessage("{}");
    }

    @Test
    void nullInsteadOfNumber() throws Exception {
        sendRequestAndVerifyInvalidNumberMessage("{\"number\":null}");
    }

    @Test
    void stringInsteadOfNumber() throws Exception {
        sendRequestAndVerifyInvalidNumberMessage("{\"number\":\"abs\"}");
    }

    private void sendRequestAndVerifyInvalidNumberMessage(String number) throws Exception {
        mockMvc.perform(post("/shuffle")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(number))
                .andExpect(status().isBadRequest())
                .andExpect(content().string(INVALID_NUMBER_MESSAGE));
    }
}