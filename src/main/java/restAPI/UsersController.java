package main.java.restAPI;

import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicLong;

import main.java.exceptions.ItemNotFoundException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.boot.json.JsonParser;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/users")
public class UsersController {
	private DataUtil dataUtil = new DataUtil("users");
	
    @RequestMapping(method = RequestMethod.GET)
    public ArrayList<User> getAllUsers() {
    	ArrayList<User> users = new ArrayList<User>();
    	JSONArray usersJson = dataUtil.getItems();
    	for(int i = 0; i < usersJson.size(); i++) {
    		JSONObject jsonUser = (JSONObject) usersJson.get(i);
    		User user = new User(jsonUser);
    		users.add(user);
    	}
    	return users;
    }
    
    @RequestMapping(value = "/count")
    public int getUsersCount() {
    	return dataUtil.getItems().size();
    }
    
    @RequestMapping(value = "/find")
    public ArrayList<User> find(@RequestParam(value="firstName", required = false) String firstName, @RequestParam(value="lastName", required = false) String lastName) {
    	ArrayList<User> users = new ArrayList<User>();
    	JSONArray usersJson = dataUtil.getItems();
    	for(int i = 0; i < usersJson.size(); i++) {
    		User user = new User((JSONObject) usersJson.get(i));
    		if (user.getFirstName().equals(firstName) && user.getLastName().equals(lastName) ) {
    			users.add(user);
    		} else if (firstName == null && lastName == null) {
    			users.add(user);
    		} else if (firstName != null && lastName == null && user.getFirstName().equals(firstName)) {
    			users.add(user);
    		} else if (firstName == null && lastName != null && user.getLastName().equals(lastName)) {
    			users.add(user);
    		}
    	}
    	if (users.size() == 0) {
    		throw new ItemNotFoundException("could not find user " + firstName + " " + lastName);
    	}
    	return users;
    }
       
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public User getUser(@PathVariable long id, Model model) {
    	User user = this.getById(id);
    	if (user == null) {
    		throw new ItemNotFoundException(id, "user");
    	}
    	return this.getById(id);
    }
    
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<User> createUser(@RequestBody User item) {
    	ResponseEntity<User> responseEntity = new ResponseEntity<User>(HttpStatus.CREATED);
    	
    	int countBefore = this.getUsersCount();
    	JSONArray itemsJson = dataUtil.getItems();
    	boolean isNew = true;
    	
    	for (int i = 0; i < itemsJson.size(); i++) {
    		User user = new User((JSONObject)itemsJson.get(i));
    		if (user.getFirstName().equals(item.getFirstName()) && user.getLastName().equals(item.getLastName()) && user.getEmail().equals(item.getEmail())) {
    			isNew = false;
    			responseEntity.status(HttpStatus.CONFLICT);
    			break;
    		}
    	}
    	if (isNew) {
	    	System.out.println("creating user\n" + item.toString());
	    	item.setId(countBefore);
	    	itemsJson.add(item.toJsonObject());
	    	dataUtil.save();
	    	System.out.println("new user count: " + itemsJson.size() + ", was: " + countBefore);
	    	responseEntity.status(HttpStatus.ACCEPTED);
    	}
    	return responseEntity;
    }
    
    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity<User> editUser(@RequestBody User item) {
    	int countBefore = this.getUsersCount();
    	JSONArray itemsJson = dataUtil.getItems();
    	System.out.println("editing user\n" + item.toString());
    	itemsJson.add(item.toJsonObject());
    	
    	System.out.println("new user count: " + itemsJson.size() + ", was: " + countBefore);
    	return new ResponseEntity<User> (item, HttpStatus.ACCEPTED);
    }
    
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<User> deleteUser(@PathVariable long id, Model model) {
    	User item = getById(id);
    	if (item != null) {
        	return new ResponseEntity<User> (item, HttpStatus.ACCEPTED);
    	} else {
    		return new ResponseEntity<User> (item, HttpStatus.NOT_FOUND);
    	}
    }
    
    private HashMap<Long, User> getById() {
    	HashMap<Long, User> users = new HashMap<Long, User>();
    	JSONArray jsonUsers = dataUtil.getItems();
    	
    	for (int i = 0; i < jsonUsers.size(); i++) {
    		JSONObject jsonUser = (JSONObject) jsonUsers.get(i);
    		User user = new User(jsonUser);
    		users.put(user.getId(),  user);
    	}
    	return users;
    }
    
    private User getById(long id) {
    	JSONArray jsonUsers = dataUtil.getItems();
    	User user = null;
    	for (int i = 0; i < jsonUsers.size(); i++) {
    		JSONObject jsonUser = (JSONObject) jsonUsers.get(i);
    		if ((long) jsonUser.get("id") == id) {
    			user = new User(jsonUser);
    			break;
    		}
    	}
    	
    	return user;
    }
    
    

}
