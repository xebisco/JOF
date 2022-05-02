package com.xebisco.jof;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class JOFUtils {

	static Map<String, Character> controlCharsMap = new HashMap<>();

	static {
		controlCharsMap.put("quote", '\6');
		controlCharsMap.put("reading quote", '\7');
		controlCharsMap.put("string space", '\5');
		controlCharsMap.put("line split", '\1');
	}

	public static class JOFReader {
		public String[] read(InputStream inputStream) {
			StringBuilder builder = new StringBuilder();
			boolean lastIsSpace = false, inQuotes = false, removeSpaces = false;
			try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
				String line;
				while ((line = reader.readLine()) != null) {
					StringBuilder lineOut = new StringBuilder();
					line.trim();
					line = line.replace("\\\"", controlCharsMap.get("reading quote").toString()).replace("\t", "");
					for (char c : line.toCharArray()) {
						if (c == '\"') {
							c = '\6';
							inQuotes = !inQuotes;
						}
						if (inQuotes) {
							if (c == ' ') {
								c = controlCharsMap.get("string space");
							}
						} else {
							if (c == '=' || c == ',' || c == ':') {
								removeSpaces = true;
							}
							if (c == ' ') {
								if (lastIsSpace) {
									c = '\0';
								}
								lastIsSpace = true;
							} else {
								lastIsSpace = false;
							}
						}

						if (c == '\7') {
							c = '\"';
						}

						if (c != '\0') {
							if (removeSpaces) {
								if (lineOut.toString().endsWith(" "))
									lineOut.setLength(lineOut.length() - 1);
								lastIsSpace = true;
							}
							lineOut.append(c);
						}
					}
					if (lineOut.toString().hashCode() != "".hashCode()) {
						builder.append(lineOut.toString());
						if(!inQuotes)
							builder.append(controlCharsMap.get("line split"));
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			return builder.toString().split(controlCharsMap.get("line split").toString());
		}
	}
}
