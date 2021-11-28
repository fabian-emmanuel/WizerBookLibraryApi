package com.codewithfibbee.wizerbooklibraryapi.utility;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Converter {

    public static String asJsonString(final Object obj) {
        try {
            ObjectMapper myObjectMapper = new ObjectMapper();
            myObjectMapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
            return myObjectMapper.writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
