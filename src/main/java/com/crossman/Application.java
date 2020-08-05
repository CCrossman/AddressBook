package com.crossman;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	public JedisConnectionFactory jedisConnectionFactory() {
		return new JedisConnectionFactory();
	}

	@Bean
	public RedisTemplate<String, Object> redisTemplate(JedisConnectionFactory jedisConnectionFactory) {
		RedisTemplate<String,Object> template = new RedisTemplate<>();
		template.setConnectionFactory(jedisConnectionFactory);
		return template;
	}
}
