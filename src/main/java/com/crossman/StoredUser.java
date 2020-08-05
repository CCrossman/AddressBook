package com.crossman;

import lombok.Value;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.util.Set;
import java.util.stream.Collectors;

@RedisHash("StoredUser")
@Value
public class StoredUser {
	@Id String email;
	String firstName, lastName;
	Set<StoredAddress> addresses;

	public User toUser() {
		return new User(email, firstName, lastName, addresses.stream().map(StoredAddress::toAddress).collect(Collectors.toSet()));
	}
}
