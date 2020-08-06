package com.crossman;

import lombok.Value;

import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

@Value
public class User {
	String email;
	String firstName, lastName;
	Set<Address> addresses;

	public StoredUser toStoredUser() {
		return new StoredUser(email, firstName, lastName, addresses == null ? Collections.emptySet() : addresses.stream().map(Address::toStoredAddress).collect(Collectors.toSet()));
	}
}
