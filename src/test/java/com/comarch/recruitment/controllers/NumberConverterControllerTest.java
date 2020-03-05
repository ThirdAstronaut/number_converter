package com.comarch.recruitment.controllers;

import com.comarch.recruitment.utils.ConversionTypes;
import com.comarch.recruitment.utils.Mappings;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.web.bind.annotation.Mapping;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class NumberConverterControllerTest {

    @Autowired
    private MockMvc mockMvc;
    private String num = "256";
    private int hexNum = Integer.parseInt(num, 16);
    private final String numberToRomanNumeral = "CCLVI";

    @Test
    void convertMappingIsAvailable() throws Exception {
        mockMvc.perform(post(Mappings.CONVERT_PATH).contentType("application/json").param("number", num).param("type", ConversionTypes.HEX.toString())).andExpect(status().isOk());
    }

    @Test
    void convertToHexReturnsCorrectValue() throws Exception {
        mockMvc.perform(post(Mappings.CONVERT_PATH).contentType("application/json").param("number", num).param("type", ConversionTypes.HEX.toString())).andExpect(content().string(String.valueOf(hexNum)));
    }

    @Test
    void convertToRomanNumeralsReturnsCorrectValue() throws Exception {
        mockMvc.perform(post(Mappings.CONVERT_PATH).contentType("application/json").param("number", num).param("type", ConversionTypes.ROMAN.toString())).andExpect(content().string(numberToRomanNumeral));
    }

    @Test
    void convertToHexReturnsIncorrectValue() throws Exception {
        String incorrectValue = "11";
        MvcResult mvcResult = mockMvc.perform(post(Mappings.CONVERT_PATH).contentType("application/json").param("number", num).param("type", ConversionTypes.HEX.toString())).andReturn();
        String result = mvcResult.getResponse().getContentAsString();
        assertNotEquals(incorrectValue, mvcResult.getResponse().getContentAsString());
        assertEquals(String.valueOf(hexNum), result);
    }

    @Test
    void convertToRomanNumeralsReturnsIncorrectValue() throws Exception {
        String incorrectNumberToRomanNumeral = "CCLVIII";
        MvcResult mvcResult = mockMvc.perform(post(Mappings.CONVERT_PATH).contentType("application/json").param("number", num).param("type", ConversionTypes.ROMAN.toString())).andReturn();
        String result = mvcResult.getResponse().getContentAsString();
        assertNotEquals(incorrectNumberToRomanNumeral, result);
        assertEquals(numberToRomanNumeral, result);
    }

    @Test
    void shouldReturnNullWhenWNegativeNumbr() throws Exception {
        String negativeNum = "-1212";
        MvcResult mvcResult = mockMvc.perform(post(Mappings.CONVERT_PATH).contentType("application/json").param("number", negativeNum).param("type", ConversionTypes.ROMAN.toString())).andReturn();
        String result = mvcResult.getResponse().getContentAsString();
        assertEquals("", result);
    }

}

