package com.svenruppert.securecoding.inputvalidation.v03.p02;

public class Application {
    public static void main(String[] args) {
        RestService restService = new RestService();
        restService.startServer(8080);
    }
}