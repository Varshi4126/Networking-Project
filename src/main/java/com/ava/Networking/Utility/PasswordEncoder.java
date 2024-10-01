package com.ava.Networking.Utility;

import java.util.regex.Pattern;


public class PasswordEncoder {
	private static final String PASSWORD_REGEX = 
	        "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$";

	    public static boolean isValid(String password) {
	        Pattern pattern = Pattern.compile(PASSWORD_REGEX);
	        return pattern.matcher(password).matches();
	    }

		public String encode(String password) {
			// TODO Auto-generated method stub
			
			return null;
		}
}
