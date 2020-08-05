package com.crossman;

import lombok.Value;
import org.springframework.data.redis.core.RedisHash;

import java.util.Optional;

@RedisHash("StoredAddress")
@Value
public class StoredAddress {
	// hash of the other data fields
	String id;

	AddressType type;
	String line1, line2;
	String city;
	String state;
	String zip;

	public String getPretty() {
		return line1 + Optional.ofNullable(line2).map(s -> ", " + s).orElse("") + ", " + city + ", " + state + ", " + zip;
	}

	public Address toAddress() {
		return new Address(type, line1, line2, city, state, zip);
	}
}
