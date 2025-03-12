package com.svenruppert.securecoding.inputvalidation.v01;

import com.svenruppert.dependencies.core.logger.HasLogger;

import io.javalin.Javalin;

public class RestService04
implements HasLogger {

  public static final int DEFAULT_PORT = 7070;
  private final Javalin service;
  private final int port;


  public RestService04() {
    this.port = DEFAULT_PORT;
    service = initService();
  }

  public RestService04(int port) {
    this.port = port;
    service = initService();
  }

  public Javalin getService() {
    return service;
  }

  public Javalin startService() {
    return service.start(port);
  }

  public Javalin startService(int port) {
    return service.start(port);
  }

  private Javalin initService() {
    logger().info("Initializing REST service");
    return Javalin.create(/*config*/)
        .get("/", ctx -> ctx.result("Hello World"))
        .get("/divide/{valueA}/{valueB}", ctx -> {
          String valueA = ctx.pathParam("valueA");
          String valueB = ctx.pathParam("valueB");
  
          //werte können null sein, bzw fehlen
          if(valueA.isEmpty()) {
            ctx.result("Dividend hat keinen Wert");
            return;
          }
          if(valueB.isEmpty()) {
            ctx.result("Divisor hat keinen Wert");
            return;
          }
          //werte keine Zahlen
          try {
             Float.parseFloat(valueA);
             Float.parseFloat(valueB); 
          } catch (NumberFormatException ex) {
            ctx.result("einer der Parameter ist keine Zahl");
            return;
          }

          //Division durch 0
          Float fA = Float.valueOf(valueA);
          Float fB = Float.valueOf(valueB);
          DivideService divService = new DivideService();
          String result;
          try{
            float floatResult = divService.divide(fA, fB);
            result = String.valueOf(floatResult);
          }catch(IllegalArgumentException ex){
            result = "Div durch 0 geht nicht";
          }
          ctx.result(result);
        })
        .get("/upper/{value}/{name}", ctx -> {
          String value = ctx.pathParam("value");
          String name = ctx.pathParam("name");
          ctx.result(new UpperCaseService().toUpperCase(value + "-" + name));
        });
  }
}