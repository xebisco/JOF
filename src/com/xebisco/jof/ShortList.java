package com.xebisco.jof;

public class ShortList extends JOFObject {
	public ShortList(String prop) {
		super(prop);
		setValue(JOFUtils.toArrayList(prop, Short.class));
	}
}
