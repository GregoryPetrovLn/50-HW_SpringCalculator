package telran.spring.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static telran.spring.api.ApiConstants.*;

import telran.spring.service.interfaces.Calculator;

import java.io.Serializable;

import static telran.spring.service.interfaces.Calculator.*;


@RestController
public class CalculatorController {
    @Autowired //request for getting reference to bean of class implementing Calculator (DI)
    Calculator calculator;


    @GetMapping(value = CALCULATOR_ADD)
    ResponseEntity<?> add(@RequestParam(name = FIRST_PARAM, required = true) int op1,
                          @RequestParam(name = SECOND_PARAM, defaultValue = "0") int op2) {
        return getResponse(op1, op2, ADD);
    }


    @GetMapping(value = CALCULATOR_SUB)
    ResponseEntity<?> subtract(@RequestParam(name = FIRST_PARAM, required = true) int op1,
                               @RequestParam(name = SECOND_PARAM, defaultValue = "0") int op2) {
        return getResponse(op1, op2, SUB);
    }


    @GetMapping(value = CALCULATOR_MUL)
    ResponseEntity<?> multiply(@RequestParam(name = FIRST_PARAM, required = true) int op1,
                               @RequestParam(name = SECOND_PARAM, defaultValue = "1") int op2) {
        return getResponse(op1, op2, MUL);
    }


    @GetMapping(value = CALCULATOR_DIV)
    ResponseEntity<?> divide(@RequestParam(name = FIRST_PARAM, required = true) int op1,
                             @RequestParam(name = SECOND_PARAM, defaultValue = "1") int op2) {
        return getResponse(op1, op2, DIV);
    }


    /**
     * @param op1
     * @param op2
     * @param operation
     * @return
     */
    private ResponseEntity<?> getResponse(int op1, int op2, String operation) {
        try {
            return ResponseEntity.ok(calculator.calculate(op1, op2, operation));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
