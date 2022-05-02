package com.xebisco.jof;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class JOF {

	private Map<String, Object> objects = new HashMap<>();
	private ArrayList<String> packages = new ArrayList<>();

	public JOF(String[] lines) {
		packages.add("");
		for (String line : lines) {
			String[] pieces = line.split("=");
			if (pieces.length > 1) {
				String[] valueType = pieces[1].split(":");
				String name = pieces[0],
						value = valueType[0].replace(JOFUtils.controlCharsMap.get("string space"), ' '),
						type = valueType[1];
				Object obj = null;

				if (value.startsWith(JOFUtils.controlCharsMap.get("quote").toString())) {
					value = value.substring(1);
				} else {
					throw new JOFException("(" + line + "). '\"' expected.");
				}
				if (value.endsWith(JOFUtils.controlCharsMap.get("quote").toString())) {
					value = value.substring(0, value.length() - 1);
				} else {
					throw new JOFException("(" + line + "). '\"' expected.");
				}

				boolean findedClass = false;
				
				for(String pkg : packages) {
					try {
						Class<?> c = Class.forName(pkg + type);
						findedClass = true;
						try {
							obj = c.getDeclaredConstructor(String.class).newInstance(value);
						} catch (InstantiationException | IllegalAccessException | IllegalArgumentException
								| InvocationTargetException | NoSuchMethodException | SecurityException e) {
							e.printStackTrace();
						}
						if(obj instanceof JOFObject) {
							obj = ((JOFObject) obj).getValue();
						}
						break;
					} catch (ClassNotFoundException e) {
						findedClass = false;
					}
				}
				
				if(!findedClass) {
					throw new JOFException("(" + line + "). Unespected type.");
				}

				objects.put(name, obj);

			} else {
				String[] pcs = line.split(" ");
				if (pcs.length > 1) {
					if (pcs[0].hashCode() == "using".hashCode()) {
						String[] pcs2 = pcs[1].split(",");
						for (String pkg : pcs2) {
							packages.add(pkg + ".");
						}
					} else {
						throw new JOFException("(" + line + "). '" + pcs[0] + "'");
					}
				} else {
					throw new JOFException("(" + line + "). '=' expected.");
				}

			}
		}

	}
	
	public Object get(String name) {
		return objects.get(name);
	}
	
	public <T extends Object> T get(String name, Class<T> type) {
		return type.cast(objects.get(name));
	}
}
