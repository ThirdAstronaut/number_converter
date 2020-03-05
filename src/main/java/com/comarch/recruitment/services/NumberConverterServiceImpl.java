package com.comarch.recruitment.services;

import com.comarch.recruitment.utils.ConversionTypes;
import org.springframework.stereotype.Service;

import javax.naming.OperationNotSupportedException;

@Service
public class NumberConverterServiceImpl implements NumberConverterService {

    @Override
    public String convert(String number, String type) throws OperationNotSupportedException {
        if (type.equals(ConversionTypes.HEX.toString()))
            return convertToHex(number);
        else if (type.equals(ConversionTypes.ROMAN.toString()))
            return convertToRomanNumerals(number);
        else throw new OperationNotSupportedException("No conversion available");
    }

    private String convertToHex(String number) {
        return String.valueOf(Integer.parseInt(number, 16));
    }

    private String convertToRomanNumerals(String number) {
        return String.valueOf(new char[Integer.parseInt(number)]).replace('\0', 'I')
                .replace("IIIII", "V")
                .replace("IIII", "IV")
                .replace("VV", "X")
                .replace("VIV", "IX")
                .replace("XXXXX", "L")
                .replace("XXXX", "XL")
                .replace("LL", "C")
                .replace("LXL", "XC")
                .replace("CCCCC", "D")
                .replace("CCCC", "CD")
                .replace("DD", "M")
                .replace("DCD", "CM");
    }
}
