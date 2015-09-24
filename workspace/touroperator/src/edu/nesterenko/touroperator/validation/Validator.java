package edu.nesterenko.touroperator.validation;

import java.util.regex.Pattern;

import edu.nesterenko.touroperator.resource.RegexManager;

public class Validator {
	
	private Validator() {}
	
	public static boolean checkLogin(String login) {
		return Pattern.matches(RegexManager.getProperty("pattern.login"), login);
	}
	
	public static boolean checkPassword(String password) {
		return Pattern.matches(RegexManager.getProperty("pattern.password"), password);
	}
	
	public static boolean checkOnlyLatters(String value) {
		return Pattern.matches(RegexManager.getProperty("pattern.onlylitter"), value);
	}
	
	public static boolean checkEmail(String email) {
		return Pattern.matches(RegexManager.getProperty("pattern.email"), email);
	}
}
