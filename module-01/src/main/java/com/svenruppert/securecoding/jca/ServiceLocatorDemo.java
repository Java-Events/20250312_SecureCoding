package com.svenruppert.securecoding.jca;

import java.util.ServiceLoader;

public class ServiceLocatorDemo {

  interface EncryptionService {
    public String encode(String input);
  }

  public static class MySuperDooperSecureService
      implements EncryptionService {
    @Override
    public String encode(String input) {
      return input.toUpperCase();
    }
  }

  public static void main(String[] args) {
    EncryptionService service = new MySuperDooperSecureService();

    ServiceLoader
        <EncryptionService> serviceLoader = ServiceLoader.load(EncryptionService.class);

    // menta-inf

  }


}
