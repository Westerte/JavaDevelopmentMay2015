package edu.vadim.test.run;

import java.util.regex.Pattern;

public class Runner {
	
	public static void main(String[] args) {	
		String patternString = "(?:0[1-9]|1\\d|2(?:\\d(?=\\.\\d\\d\\.(?:(?:[02468][048]|[13579][26])00|\\d\\d(?:[2468][48]|[13579][26]|0[48])))|[0-8])(?=\\.02)|2(?:\\d|[0-8])(?!\\.02)|3(?:0|[01](?=\\.(?:0[13578]|1[02])))(?!\\.02))\\.(?:0[1-9]|1[0-2])\\.\\d{4}";
		System.out.println(Pattern.matches(patternString, "01.04.1995"));
		double dbl = new Double("1e6");
		System.out.println(dbl);
	}
}