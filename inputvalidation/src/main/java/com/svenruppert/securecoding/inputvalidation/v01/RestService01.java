package com.svenruppert.securecoding.inputvalidation.v01;

import com.svenruppert.dependencies.core.logger.HasLogger;
import io.javalin.Javalin;

public class RestService01 implements HasLogger {

	public static final int DEFAULT_PORT = 7070;
	private final Javalin service;
	private final int port;


	public RestService01() {
		this.port = DEFAULT_PORT;
		service = initService();
	}

	public RestService01(int port) {
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
					if (valueA.isEmpty()) {
						ctx.status(400).result("Missing valueA");
					}
					if (valueB.isEmpty()) {
						ctx.status(400).result("Missing valueB");
					}
					//werte keine Zahlen
					//Division durch 0
					try {
						float result = new DivideService().divide(Float.parseFloat(valueA), Float.parseFloat(valueB));
						ctx.result(Float.toString(result));
					} catch (NumberFormatException e) {
						ctx.status(400).result("Not a valid number");
					} catch (IllegalArgumentException e) {
						ctx.status(400).result(e.getMessage());
					}
				})
				.get("/upper/{value}/{name}", ctx -> {
					String value = ctx.pathParam("value");
					String name = ctx.pathParam("name");
					ctx.result(new UpperCaseService().toUpperCase(value + "-" + name));
				});
	}

}
