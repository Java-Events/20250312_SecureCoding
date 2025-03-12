package com.svenruppert.securecoding.inputvalidation.v01;

public class DivideService {

  public float divide(float a, float b) {
    if(b == 0) throw new IllegalArgumentException("alles doof");
    return a / b;
  }

}
