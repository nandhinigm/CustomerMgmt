package com.nab.customer.customermgmt.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CustomerUtil {	

	private static final String NAME_PATTERN = "^[a-zA-Z\\s]{0,15}$";	 

	/**
	 * Validate name with regular expression
	 * @param name customer name for validation
	 * @return true valid name, false invalid name
	 */
	public static boolean validateName(final String name){

		if(name != null) {
			Pattern pattern = Pattern.compile(NAME_PATTERN);
			Matcher matcher = pattern.matcher(name);
			return matcher.matches();
		}
		return false;
	}	
}
