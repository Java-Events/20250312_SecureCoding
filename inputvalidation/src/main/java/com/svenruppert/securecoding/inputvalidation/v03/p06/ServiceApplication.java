package com.svenruppert.securecoding.inputvalidation.v03.p06;

import com.sun.net.httpserver.HttpServer;
import com.svenruppert.securecoding.inputvalidation.v03.p06.services.DemoService;

import java.io.IOException;
import java.net.InetSocketAddress;

public class ServiceApplication {

  public static void main(String[] args) throws IOException {
    DemoService restService = new DemoService();
    restService.startService(7070);
    HttpServer server = HttpServer.create(new InetSocketAddress(7071), 0);
    server.createContext("/upload", new RestService.FileUploadHandler());
    server.setExecutor(null);
    server.start();
    System.out.println("Server läuft");
  }
}
