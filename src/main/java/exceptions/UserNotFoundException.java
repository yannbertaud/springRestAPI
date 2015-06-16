package main.java.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "No such user")
public class UserNotFoundException extends RuntimeException  {
	public UserNotFoundException(long userId) {
		super("User id: " + userId + " not found");
	}
}
