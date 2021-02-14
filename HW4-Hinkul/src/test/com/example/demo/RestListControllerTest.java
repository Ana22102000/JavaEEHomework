package com.example.demo;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONArray;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.junit.jupiter.params.provider.Arguments;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@WebMvcTest
class RestListControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void shouldAddBook() throws Exception{
        final String jsonRequest = objectMapper.writeValueAsString(new BookModel("title", "isbn", "surname", "name"));
        final String jsonResponse = new String(RestListController.class.getResourceAsStream("/test/expectedJson1").readAllBytes());

        mockMvc.perform(
                MockMvcRequestBuilders.post("/form")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonRequest)
        )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(jsonResponse));

    }

    @Test
    void shouldFindAllBooks_afterAddBook() throws Exception{

        final String jsonRequest = objectMapper.writeValueAsString(new BookModel("o", "isbn", "surname", "name"));

        mockMvc.perform(
                MockMvcRequestBuilders.post("/form")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequest)
        );

        final String title = "o";
        final String jsonResponse = new String(RestListController.class.getResourceAsStream("/test/expectedJson3").readAllBytes());

        mockMvc.perform(
                MockMvcRequestBuilders.get("/get_books?title="+title))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(jsonResponse));

    }

    @ParameterizedTest
    @MethodSource("titleSearchProvider")
    void shouldFindBooks(final String title, final String filename) throws Exception{
        final String jsonResponse = new String(RestListController.class.getResourceAsStream("/test/"+filename).readAllBytes());

        mockMvc.perform(
                MockMvcRequestBuilders.get("/get_books?title="+title))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(jsonResponse));

    }

    private static Stream<Arguments> titleSearchProvider(){
        return Stream.of(
                Arguments.of("wekwjhekwhkejhwjkehkw", "emptyJson"),
                Arguments.of("o", "expectedJson2"),
                Arguments.of("he", "expectedJson4")

        );
    }


}