package com.svenruppert.securecoding.inputvalidation.v01.u02;

public class FloatValidator02 {

    public boolean isFloat(String value) {
        try {
            Float.parseFloat(value);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

}
