package com.app.highload;

import com.app.highload.message.model.Message;
import com.app.highload.redis.RedisPublisher;
import com.app.highload.redis.RedisSubscriber;
import com.app.highload.tick.model.Tick;
import com.app.highload.tick.repository.TickRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RestServiceController {

	//private double highload = 0;
	//public Tick tick() { return new Tick(1,highload++); }

	@Autowired
	private RedisTemplate<String,Object> redisTemplate;
	@Autowired
	private TickRepository repository;
	private Tick tick;

	@Autowired
	private RedisPublisher messagePublisher;

	@GetMapping("/")
	public double tickTack() {
		if (tick == null) {
			 tick = new Tick(96,0);
		}

		repository.increment(tick,1);
		return repository.score(tick);
	}

	@PostMapping("/send")
	public void postMessage(@RequestBody Message message) {
		messagePublisher.publish(message.toString());

	}

	@GetMapping("/readAll")
	public List<String> getMessages(){
		return RedisSubscriber.messageList;
	}

}