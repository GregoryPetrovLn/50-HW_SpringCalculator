package telran.spring;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import telran.spring.controllers.CalculatorController;
import telran.spring.service.interfaces.Calculator;

import static telran.spring.api.ApiConstants.*;

@SpringBootTest
@AutoConfigureMockMvc
        //automatic Mock configuration
class CalculatorApplicationTests {
    @Autowired
    CalculatorController controller;

    @Autowired
    Calculator calculator;

    @Autowired
    MockMvc mock;

    @Test
    void contextLoads() {
        assertNotNull(controller);
        assertNotNull(calculator);
    }


    //======================Normal tests ======================

    @Test
    void addNormal() throws Exception {
        String expected = "40";
        MockHttpServletResponse response = mock.perform(MockMvcRequestBuilders.get(
                String.format("%s?%s=%d&%s=%d",
                        CALCULATOR_ADD, FIRST_PARAM, 20, SECOND_PARAM, 20))).andReturn().getResponse();

        assertEquals(HttpStatus.OK, HttpStatus.valueOf(response.getStatus()));
        assertEquals(expected, response.getContentAsString());
    }

    @Test
    void subNormal() throws Exception {
        String expected = "10";
        MockHttpServletResponse response = mock.perform(MockMvcRequestBuilders.get(
                String.format("%s?%s=%d&%s=%d",
                        CALCULATOR_SUB, FIRST_PARAM, 20, SECOND_PARAM, 10))).andReturn().getResponse();

        assertEquals(HttpStatus.OK, HttpStatus.valueOf(response.getStatus()));
        assertEquals(expected, response.getContentAsString());
    }

    @Test
    void multipleNormal() throws Exception {
        String expected = "40";
        MockHttpServletResponse response = mock.perform(MockMvcRequestBuilders.get(
                String.format("%s?%s=%d&%s=%d",
                        CALCULATOR_MUL, FIRST_PARAM, 20, SECOND_PARAM, 2))).andReturn().getResponse();

        assertEquals(HttpStatus.OK, HttpStatus.valueOf(response.getStatus()));
        assertEquals(expected, response.getContentAsString());
    }

    @Test
    void divideNormal() throws Exception {
        String expected = "10";
        MockHttpServletResponse response = mock.perform(MockMvcRequestBuilders.get(
                String.format("%s?%s=%d&%s=%d",
                        CALCULATOR_DIV, FIRST_PARAM, 20, SECOND_PARAM, 2))).andReturn().getResponse();

        assertEquals(HttpStatus.OK, HttpStatus.valueOf(response.getStatus()));
        assertEquals(expected, response.getContentAsString());
    }


    //======================Error tests ======================


    @Test
    void divideError() throws Exception {
        MockHttpServletResponse response = mock.perform(MockMvcRequestBuilders.get(
                String.format("%s?%s=%d&%s=%d",
                        CALCULATOR_DIV, FIRST_PARAM, 20, SECOND_PARAM, 0))).andReturn().getResponse();

        assertEquals(HttpStatus.BAD_REQUEST, HttpStatus.valueOf(response.getStatus()));
    }

    //======================Missed first parameters tests ======================


    @Test
    void addMissedFirstParameters() throws Exception {
        MockHttpServletResponse response = mock.perform(MockMvcRequestBuilders.get(
                String.format("%s?%s=&%s=%d",
                        CALCULATOR_ADD, FIRST_PARAM,  SECOND_PARAM, 20))).andReturn().getResponse();

        assertEquals(HttpStatus.BAD_REQUEST, HttpStatus.valueOf(response.getStatus()));
    }

    @Test
    void subtractMissedFirstParameters() throws Exception {
        MockHttpServletResponse response = mock.perform(MockMvcRequestBuilders.get(
                String.format("%s?%s=&%s=%d",
                        CALCULATOR_SUB, FIRST_PARAM, SECOND_PARAM, 20))).andReturn().getResponse();

        assertEquals(HttpStatus.BAD_REQUEST, HttpStatus.valueOf(response.getStatus()));
    }

    @Test
    void multiplyMissedFirstParameters() throws Exception {
        MockHttpServletResponse response = mock.perform(MockMvcRequestBuilders.get(
                String.format("%s?%s=&%s=%d",
                        CALCULATOR_MUL, FIRST_PARAM, SECOND_PARAM, 20))).andReturn().getResponse();

        assertEquals(HttpStatus.BAD_REQUEST, HttpStatus.valueOf(response.getStatus()));
    }

    @Test
    void divideMissedFirstParameters() throws Exception {
        MockHttpServletResponse response = mock.perform(MockMvcRequestBuilders.get(
                String.format("%s?%s=&%s=%d",
                        CALCULATOR_DIV, FIRST_PARAM, SECOND_PARAM, 20))).andReturn().getResponse();

        assertEquals(HttpStatus.BAD_REQUEST, HttpStatus.valueOf(response.getStatus()));
    }



    //======================Missed second parameters tests ======================

    @Test
    void addMissedSecondParameters() throws Exception {
        String expected = "20";
        MockHttpServletResponse response = mock.perform(MockMvcRequestBuilders.get(
                String.format("%s?%s=%d&%s",
                        CALCULATOR_ADD, FIRST_PARAM, 20, SECOND_PARAM))).andReturn().getResponse();

        assertEquals(expected, response.getContentAsString());

    }

    @Test
    void subtractMissedSecondParameters() throws Exception {
        String expected = "20";
        MockHttpServletResponse response = mock.perform(MockMvcRequestBuilders.get(
                String.format("%s?%s=%d&%s",
                        CALCULATOR_SUB, FIRST_PARAM, 20, SECOND_PARAM))).andReturn().getResponse();


        assertEquals(expected, response.getContentAsString());
    }

    @Test
    void multiplyMissedSecondParameters() throws Exception {
        String expected = "20";
        MockHttpServletResponse response = mock.perform(MockMvcRequestBuilders.get(
                String.format("%s?%s=%d&%s",
                        CALCULATOR_MUL, FIRST_PARAM, 20, SECOND_PARAM))).andReturn().getResponse();


        assertEquals(expected, response.getContentAsString());
    }

    @Test
    void divideMissedSecondParameters() throws Exception {
        String expected = "20";
        MockHttpServletResponse response = mock.perform(MockMvcRequestBuilders.get(
                String.format("%s?%s=%d&%s",
                        CALCULATOR_DIV, FIRST_PARAM, 20, SECOND_PARAM))).andReturn().getResponse();


        assertEquals(expected, response.getContentAsString());
    }

}
