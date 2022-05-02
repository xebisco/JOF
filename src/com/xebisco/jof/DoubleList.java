package com.xebisco.jof;

public class DoubleList extends JOFObject {
	public DoubleList(String prop) {
		super(prop);
		setValue(JOFUtils.toArrayList(prop, Double.class));
	}
}
