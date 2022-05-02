package com.xebisco.jof;

public class Char extends JOFObject {
	public Char(String prop) {
		super(prop);
		setValue(prop.charAt(0));
	}
}
