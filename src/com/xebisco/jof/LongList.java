package com.xebisco.jof;

public class LongList extends JOFObject {
	public LongList(String prop) {
		super(prop);
		setValue(JOFUtils.toArrayList(prop, Long.class));
	}
}
