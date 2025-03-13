package com.svenruppert.securecoding.inputvalidation.v03.p05;

public class Application {

	private static final int DEFAULT_PORT = 8080;

	public static void main(String[] args) {

		System.out.println("Starte Server auf port "+ DEFAULT_PORT);
		RestService service = new RestService();
		service.startService(DEFAULT_PORT);
	}
}
