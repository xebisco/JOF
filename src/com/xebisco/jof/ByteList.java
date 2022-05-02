package com.xebisco.jof;

public class ByteList extends JOFObject {
	public ByteList(String prop) {
		super(prop);
		setValue(JOFUtils.toArrayList(prop, Byte.class));
	}
}
