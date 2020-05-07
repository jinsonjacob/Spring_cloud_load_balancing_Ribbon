package com.jinson.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RibbonClient(name = "hello-service",configuration = HelloServiceConfiguration.class)
public class HelloService {

	private static Logger log = LoggerFactory.getLogger(HelloService.class);

	@Autowired
	Environment environment;

	@RequestMapping(value = "/test")
	public String greet() {
		log.info("Access /greeting");
		return environment.getProperty("server.port");
	}


	@Autowired
	RestTemplate restTemplate;


	@RequestMapping("/hi")
	public String hi(@RequestParam(value = "name", defaultValue = "Rafael") String name) {
		log.info("Request received -----------------  ");
		String greeting = this.restTemplate.getForObject("http://hello-service/greeting", String.class);
		return String.format("%s, %s!", greeting, name);
	}

}
