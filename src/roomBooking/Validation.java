package roomBooking;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validation {
	
	Pattern emailPattern = Pattern.compile("^(.+)@(.+)$");
	Pattern namePattern = Pattern.compile("^[a-zA-Z\\s]+");
	Pattern phonePattern = Pattern.compile("\\d{10}|(?:\\d{3}-){2}\\d{4}|\\(\\d{3}\\)\\d{3}-?\\d{4}");

	
	public boolean validateMenuInput(int choice) {
		boolean check = true;
		if (choice <0 || choice >5) {
			System.out.println("Sorry! wrong input\nPlease try again!");
			check= false;
		}
		return check;
		
	}
	
	public boolean validateEmail(String email) {
		Matcher matcher = emailPattern.matcher(email);
		return matcher.matches();
	}
	
	public boolean validateName(String name)
	{
		Matcher matcher = namePattern.matcher(name);
		return matcher.matches();
	}
	
	public boolean validatePhone(String phone)
	{
		Matcher matcher = phonePattern.matcher(phone);
		return matcher.matches();
	}

}
