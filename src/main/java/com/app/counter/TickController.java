package com.app.counter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class TickController {

	//private double counter = 0;
	@Autowired
	private RedisTemplate<String,Object> redisTemplate;
	@Autowired
	private TickRepository repository;
	private Tick tick;

	@GetMapping
	public double tickTack() {
		if (tick == null) {
			 tick = new Tick(96,0);
		}

		repository.increment(tick,1);
		return repository.score(tick);
	}
	//public Tick tick() { return new Tick(1,counter++); }

}