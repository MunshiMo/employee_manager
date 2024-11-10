package tech.getarrays.employeemanager.exception;

public class UserNotFoundException extends RuntimeException{
	public UserNotFoundException(String message) {
		super(message);//call constructor from super class and pass the message
	}

}
