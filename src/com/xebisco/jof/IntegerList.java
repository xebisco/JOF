package com.xebisco.jof;

public class IntegerList extends JOFObject {
	public IntegerList(String prop) {
		super(prop);
		setValue(JOFUtils.toArrayList(prop, Integer.class));
	}
}
