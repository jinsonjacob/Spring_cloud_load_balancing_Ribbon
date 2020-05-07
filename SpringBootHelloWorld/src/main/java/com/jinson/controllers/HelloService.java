package com.jinson.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloService {

	private static Logger log = LoggerFactory.getLogger(HelloService.class);

	@Autowired
	Environment environment;

	@RequestMapping(value = "/greeting")
	public String greet() {
		log.info("Access /greeting");
		return environment.getProperty("server.port");
	}

	@RequestMapping(value = "/")
	public String home() {
		log.info("Access /");
		return "Hi!";
	}


}
