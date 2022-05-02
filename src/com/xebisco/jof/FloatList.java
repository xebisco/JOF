package com.xebisco.jof;

public class FloatList extends JOFObject {
	public FloatList(String prop) {
		super(prop);
		setValue(JOFUtils.toArrayList(prop, Float.class));
	}
}
