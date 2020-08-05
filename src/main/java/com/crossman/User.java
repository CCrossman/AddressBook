package com.crossman;

import lombok.Value;

import java.util.Set;
import java.util.stream.Collectors;

@Value
public class User {
	String email;
	String firstName, lastName;
	Set<Address> addresses;

	public StoredUser toStoredUser() {
		return new StoredUser(email, firstName, lastName, addresses.stream().map(Address::toStoredAddress).collect(Collectors.toSet()));
	}
}
