package com.crossman;

import lombok.Value;

@Value
public class UserAddress {
	String email;
	AddressType type;
	String line1, line2;
	String city;
	String state;
	String zip;

	public Address toAddress() {
		return new Address(type, line1, line2, city, state, zip);
	}
}
