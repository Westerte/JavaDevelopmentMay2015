package edu.nesterenko.touroperator.validation;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;

import edu.nesterenko.touroperator.resource.ConfigurationManager;
import edu.nesterenko.touroperator.resource.RegexManager;

public class Validator {
	
	private Validator() {}
	
	public static void checkLogin(String login) throws ValidationException {
		if(login == null || login.isEmpty() 
				|| !Pattern.matches(RegexManager.getProperty("pattern.login"), login)) {
			throw new ValidationException("login no much pattern");
		}
	}
	
	public static void checkPassword(String password) throws ValidationException {
		if(password == null || password.isEmpty()
				|| !Pattern.matches(RegexManager.getProperty("pattern.password"), password)) {
			throw new ValidationException("password no much pattern");
		}
	}
	public static String checkOnlyLatters(String value) throws ValidationException {
		if(value == null || value.isEmpty() || !Pattern.matches(RegexManager.getProperty("pattern.onlylitter"), value)) {
			throw new ValidationException("is not only latters");
		} 
		return value;
	}
	
	public static double checkDouble(String doubleValue) throws ValidationException {
	   if(doubleValue == null || doubleValue.isEmpty()) {
		   throw new ValidationException("doubleValue is empty");
	   }
	   try {
		   if(doubleValue != null && !doubleValue.isEmpty()) {
			   return Double.parseDouble(doubleValue);
		   } else {
			   return 0;
		   }
	   } catch(NumberFormatException e) {
		   throw new ValidationException(e);
	   }
	}
	
	public static Date checkDate(String dateValue) throws ValidationException {
		if(dateValue == null || dateValue.isEmpty() || !Pattern.matches(RegexManager.getProperty("pattern.date"), dateValue)) {
			throw new ValidationException("bad date");
		}
		try {
			return new SimpleDateFormat(ConfigurationManager.getProperty("date.format")).parse(dateValue);
		} catch (ParseException e) {
			throw new ValidationException(e);
		} 	
	}

	public static int checkInt(String integerValue) throws ValidationException {
		if(integerValue == null || integerValue.isEmpty()) {
			   throw new ValidationException("integerValue is empty");
		   }
		try {
			return Integer.parseInt(integerValue);
		} catch(NumberFormatException e) {
			throw new ValidationException(e);
		}
	}

	public static String checkPassportNumber(String passportNumber) throws ValidationException {
		if(passportNumber == null || passportNumber.isEmpty() || 
			!Pattern.matches(RegexManager.getProperty("pattern.passportNumber"), passportNumber)) {
			throw new ValidationException("bad passportNumber");
		}
		return passportNumber;
	}	
	
	public static String checkCid(String cid) throws ValidationException {
		if(cid == null || cid.isEmpty() || !Pattern.matches(RegexManager.getProperty("pattern.cid"), cid)) {
			throw new ValidationException("bad cid");
		}
		return cid;
	}

	public static String checkPhoneHome(String phoneHome) throws ValidationException {
		if(phoneHome == null || phoneHome.isEmpty()
			|| !Pattern.matches(RegexManager.getProperty("pattern.home"), phoneHome)) {
			throw new ValidationException("bad home phone");
		}
		return phoneHome;
	}
	
	public static String checkPhoneMobile(String phoneMobile) throws ValidationException {
		if(phoneMobile == null || phoneMobile.isEmpty() || 
			!Pattern.matches(RegexManager.getProperty("pattern.mobile"), phoneMobile)) {
			throw new ValidationException("bad mobile phone");
		}
		return phoneMobile;
	}
	
	public static String checkEmail(String email) throws ValidationException {
		if(email == null || email.isEmpty() || 
			!Pattern.matches(RegexManager.getProperty("pattern.email"), email)) {
			throw new ValidationException("bad email");
		} 
		return email;
	}
	
	public static void checkEmpty(String value) throws ValidationException {
		if(value == null || value.isEmpty()) {
			throw new ValidationException("empty value");
		}
	}
}