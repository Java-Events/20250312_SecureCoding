package com.svenruppert.securecoding.inputvalidation.v01.u02;


public class Application02 {

  private Application02() {
  }

  public static void main(String[] args) {
    RestService02 restService02 = new RestService02();
    restService02.startService(7070);
  }


}
