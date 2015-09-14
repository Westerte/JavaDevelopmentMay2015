package edu.nesterenko.touroperator.logic;

import java.util.Locale;

public class LocaleDefiner {
	private LocaleDefiner() {}
	
	public static Locale defineLocal(String language) throws LogicException {
		if(language == null || language.isEmpty()) {
			throw new LogicException("language must be not null");
		}
		Locale locale = new Locale("en", "EN"); 
		if("russian".equals(language)) {
			locale = new Locale("ru", "RU");
		}
		return locale;
	}
}
