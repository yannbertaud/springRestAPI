package main.java.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "No such item")
public class ItemNotFoundException extends RuntimeException  {
	public ItemNotFoundException(long id, String itemType) {
		super(itemType + " id: " + id + " not found");
	}
	
	public ItemNotFoundException(String itemDescription) {
		super(itemDescription + " not found");
	}
}
