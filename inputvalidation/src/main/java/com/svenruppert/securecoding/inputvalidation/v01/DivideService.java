package com.svenruppert.securecoding.inputvalidation.v01;

import java.util.Optional;

public class DivideService {

  public float divide(float a, float b) {
    if(b == 0) throw new IllegalArgumentException("Div durch null geht nicht");
    return a / b;
  }

  public Optional<Float> divideNull(float a, float b) {
    if(b == 0) return  Optional.empty();
    return Optional.of(a / b);
  }

}
