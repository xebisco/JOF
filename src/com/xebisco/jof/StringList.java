package com.xebisco.jof;

public class StringList extends JOFObject {
	public StringList(String prop) {
		super(prop);
		setValue(JOFUtils.toArrayList(prop, String.class));
	}
}
