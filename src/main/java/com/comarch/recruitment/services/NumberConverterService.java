package com.comarch.recruitment.services;

import com.comarch.recruitment.utils.ConversionTypes;

import javax.naming.OperationNotSupportedException;

public interface NumberConverterService {

    String convert(String number, String type) throws OperationNotSupportedException;

}
