package com.crossman;

import lombok.Value;

@Value
public class Address {
	String line1, line2;
	String city;
	String state;
	String zip;
}
