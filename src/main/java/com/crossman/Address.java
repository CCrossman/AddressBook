package com.crossman;

import lombok.Value;

import java.util.Base64;

@Value
public class Address {
	String line1, line2;
	String city;
	String state;
	String zip;

	public StoredAddress toStoredAddress() {
		final String id = Base64.getEncoder().encodeToString((line1 + "|" + line2 + "|" + city + "|" + state + "|" + zip).getBytes());
		return new StoredAddress(id, line1, line2, city, state, zip);
	}
}
