package libon.mower.controllers;


import com.fasterxml.jackson.databind.ObjectMapper;
import libon.mower.modals.dtos.FieldDto;
import libon.mower.modals.dtos.InputPayload;
import libon.mower.modals.dtos.OutputPayload;
import libon.mower.services.MowerService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(MowerController.class)
class MowerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private MowerService mowerService;

    @Test
    @DisplayName("process returns 200 with valid payload")
    void process_ShouldReturnOutputPayload() throws Exception {
        InputPayload input = new InputPayload();
        FieldDto fieldDto = new FieldDto();
        fieldDto.setMax_x(1);
        input.setField(fieldDto);

        OutputPayload expectedOutput = new OutputPayload();
        expectedOutput.setMowers(List.of());

        Mockito.when(mowerService.processPaylaod(any(InputPayload.class)))
                .thenReturn(expectedOutput);

        mockMvc.perform(post("/api/v1/mowers/process")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(input)))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(expectedOutput)));

        Mockito.verify(mowerService).processPaylaod(any(InputPayload.class));
    }

}
