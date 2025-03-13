package com.svenruppert.securecoding.inputvalidation.v03;

import com.sun.net.httpserver.HttpServer;
import com.svenruppert.dependencies.core.logger.HasLogger;

import java.io.IOException;
import java.net.InetSocketAddress;

public class Application implements HasLogger {

  protected static final String PATH_UPLOAD = "/upload";

  private Application() {
  }

  public static void main(String[] args) throws IOException {
    Application application = new Application();
    application.initServices();
  }

  private void initServices() throws IOException {
    int port = 8080;
    HttpServer server = HttpServer.create(new InetSocketAddress(port), 0);
    server.createContext(PATH_UPLOAD, new FileUploadHandler());
    server.setExecutor(null);
    server.start();
    logger().info("Server läuft auf http://localhost:" + port);
  }
}
