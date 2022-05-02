package com.xebisco.jof;

public abstract class JOFObject {
	
	private Object value;
	
	public JOFObject(String prop) {
	}
	
	public Object getValue() {
		return value;
	}
	
	public void setValue(Object value) {
		this.value = value;
	}
}
