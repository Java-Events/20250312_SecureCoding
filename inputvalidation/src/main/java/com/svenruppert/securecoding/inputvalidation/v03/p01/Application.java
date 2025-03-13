package com.svenruppert.securecoding.inputvalidation.v03.p01;

import com.svenruppert.dependencies.core.logger.HasLogger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Application implements HasLogger {
  private static final Logger LOGGER = LoggerFactory.getLogger(Application.class);

  public static void main(String[] args) {
    int port = RestService.DEFAULT_PORT;
    if (args.length > 0) {
      try {
          port = Integer.parseInt(args[0]);
      } catch (NumberFormatException e) {
        LOGGER.error("Invalid port number {}", args[0]);
      }
    }
    LOGGER.info("Starting rest service with port: {}", port);
    RestService restService = new RestService();
    restService.start(port);
  }
}
