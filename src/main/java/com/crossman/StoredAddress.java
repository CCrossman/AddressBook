package com.crossman;

import lombok.Value;
import org.springframework.data.redis.core.RedisHash;

@RedisHash("StoredAddress")
@Value
public class StoredAddress {
	// hash of the other data fields
	String id;

	String line1, line2;
	String city;
	String state;
	String zip;

	public Address toAddress() {
		return new Address(line1, line2, city, state, zip);
	}
}
