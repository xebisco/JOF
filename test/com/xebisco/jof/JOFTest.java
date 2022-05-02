package com.xebisco.jof;

import java.util.ArrayList;

import com.xebisco.jof.JOFUtils.JOFReader;

public class JOFTest {
	
	public static void main(String[] args) {
		JOFReader reader = new JOFReader();
		JOF jof = new JOF(reader.read(JOF.class.getResourceAsStream("test.jof")));
		System.out.println(jof.get("strings", ArrayList.class));
	}

}
