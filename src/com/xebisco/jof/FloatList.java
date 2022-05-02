package com.xebisco.jof;

import java.util.ArrayList;
import java.util.List;

public class FloatList extends JOFObject {
	public FloatList(String prop) {
		super(prop);
		List<Float> list = new ArrayList<>();
		prop = prop.replace("\\'", JOFUtils.controlCharsMap.get("reading quote").toString());
		prop = prop.replace("''", JOFUtils.controlCharsMap.get("quote").toString()).replace(JOFUtils.controlCharsMap.get("reading quote"), '\'');
		prop = prop.substring(1, prop.length() - 1);
		String[] objs = prop.split(JOFUtils.controlCharsMap.get("quote").toString());
		for(String o : objs) {
			if(o.hashCode() != "".hashCode())
			list.add(Float.valueOf(o));
		}
		setValue(list);
	}
}
