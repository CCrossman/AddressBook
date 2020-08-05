package com.crossman;

import lombok.Value;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@RedisHash("User")
@Value
public class User {
	@Id String email;
	String firstName, lastName;
}
