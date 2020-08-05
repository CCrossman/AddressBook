package com.crossman;

import lombok.Value;

import java.util.Base64;

@Value
public class UserAddress {
	// hash of the two data fields
	String id;

	String userEmail;
	String addressId;

	public static UserAddress of(String userEmail, String addressId) {
		String id = Base64.getEncoder().encodeToString((userEmail + "|" + addressId).getBytes());
		return new UserAddress(id, userEmail, addressId);
	}
}
