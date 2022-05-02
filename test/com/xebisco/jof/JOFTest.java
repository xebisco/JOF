package com.xebisco.jof;

import com.xebisco.jof.JOFUtils.JOFReader;

public class JOFTest {
	
	public static void main(String[] args) {
		JOFReader reader = new JOFReader();
		JOF jof = new JOF(reader.read(JOF.class.getResourceAsStream("test.jof")));
	}

}
