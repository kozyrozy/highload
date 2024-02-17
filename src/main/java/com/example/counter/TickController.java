package com.example.counter;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TickController {

	private int counter = 0;

	@GetMapping("/")
	public Tick tick() { return new Tick(counter++); }
}