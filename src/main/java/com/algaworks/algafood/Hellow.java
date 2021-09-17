package com.algaworks.algafood;

import org.springframework.web.bind.annotation.GetMapping;

public class Hellow {

	@GetMapping("/hello")
	public String hello() {
		return "hello";
	}
	
}
