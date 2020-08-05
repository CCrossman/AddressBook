package com.crossman;

import lombok.Value;

import java.util.Base64;

@Value
public class Address {
	AddressType type;
	String line1, line2;
	String city;
	String state;
	String zip;

	public StoredAddress toStoredAddress() {
		final String id = Base64.getEncoder().encodeToString((_type() + "|" + line1 + "|" + line2 + "|" + city + "|" + state + "|" + zip).getBytes());
		return new StoredAddress(id, type, line1, line2, city, state, zip);
	}

	private String _type() {
		return type == null ? null : type.name();
	}
}
