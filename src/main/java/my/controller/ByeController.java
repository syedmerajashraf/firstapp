package my.controller;

import my.service.HelloService;

import org.springframework.stereotype.Controller;

public class ByeController {

	private HelloService helloService;

	public HelloService getHelloService() {
		return helloService;
	}

	public void setHelloService(HelloService helloService) {
		this.helloService = helloService;
	}
	
	
}
