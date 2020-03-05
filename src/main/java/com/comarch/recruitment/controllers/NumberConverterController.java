package com.comarch.recruitment.controllers;

import com.comarch.recruitment.services.NumberConverterService;
import com.comarch.recruitment.utils.ConversionTypes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.naming.OperationNotSupportedException;

import static com.comarch.recruitment.utils.Mappings.CONVERT_PATH;

@RestController
public class NumberConverterController {

    private final NumberConverterService numberConverterService;

    @Autowired
    public NumberConverterController(NumberConverterService numberConverterService) {
        this.numberConverterService = numberConverterService;
    }

    @PostMapping(value = CONVERT_PATH)
    public String convert(@RequestParam(name = "number") String number, @RequestParam(name = "type") String type) throws OperationNotSupportedException, NumberFormatException {
        int numToInt = Integer.parseInt(number);
        if( numToInt > 0)
        return numberConverterService.convert(number, type);
        return null;
    }

}
