package main.java.restAPI;

import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicLong;

import main.java.exceptions.UserNotFoundException;

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
    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();
    private Configuration configuration = new Configuration();

//    @RequestMapping("/users")
//    public Greeting greeting(@RequestParam(value="name", defaultValue="World") String name) {
//        return new Greeting(counter.incrementAndGet(),
//                            String.format(template, name));
//    }
   // @RequestMapping("/users")
    @RequestMapping(method = RequestMethod.GET)
    public ArrayList<User> main() {
    	ArrayList<User> users = new ArrayList<User>();
    	JSONArray usersJson = this.getUsersJson();
    	for(int i = 0; i < usersJson.size(); i++) {
    		JSONObject jsonUser = (JSONObject) usersJson.get(i);
    		User user = new User(jsonUser);
    		users.add(user);
    	}
    	return users;
    }
    
    @RequestMapping(value = "/count")
    public int getUsersCount() {
    	return this.getUsersJson().size();
    }
    
    
    @RequestMapping(value = "/{userId}", method = RequestMethod.GET)
    public User getUser(@PathVariable Long userId, Model model) {
    	if (this.getById(userId) == null) {
    		throw new UserNotFoundException(userId);
    	}
    	return this.getById(userId);
    }
    
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<User> createUser(@RequestBody User user) {
    	int countBefore = this.getUsersCount();
    	JSONArray usersJson = this.getUsersJson();
    	System.out.println("creating user\n" + user.toString());
    	usersJson.add(user.toJsonObject());
    	
    	System.out.println("new user count: " + usersJson.size() + ", was: " + countBefore);
    	return new ResponseEntity<User> (user, HttpStatus.CREATED);
    }
    
    @RequestMapping(value = "/delete/{userId}", method = RequestMethod.DELETE)
    public ResponseEntity<User> deleteUser(@PathVariable Long userId, Model model) {
    	User user = getById(userId);
    	if (user != null) {
        	return new ResponseEntity<User> (user, HttpStatus.ACCEPTED);
    	} else {
    		return new ResponseEntity<User> (user, HttpStatus.NOT_FOUND);
    	}
    	
    }
    
    private HashMap<Long, User> getById() {
    	HashMap<Long, User> users = new HashMap<Long, User>();
    	JSONArray jsonUsers = this.getUsersJson();
    	
    	for (int i = 0; i < jsonUsers.size(); i++) {
    		JSONObject jsonUser = (JSONObject) jsonUsers.get(i);
    		User user = new User(jsonUser);
    		users.put(user.getUserId(),  user);
    	}
    	return users;
    }
    
    private User getById(Long userId) {
    	JSONArray jsonUsers = this.getUsersJson();
    	User user = null;
    	for (int i = 0; i < jsonUsers.size(); i++) {
    		JSONObject jsonUser = (JSONObject) jsonUsers.get(i);
    		if ((Long) jsonUser.get("id") == userId) {
    			user = new User(jsonUser);
    			break;
    		}
    	}
    	
    	return user;
    }
    
    
    private JSONArray getUsersJson() {
    	JSONParser jsonParser = new JSONParser();
    	try {
    		Object obj = jsonParser.parse(new FileReader(configuration.getUserDir() + "/users.json"));
    		JSONObject jsonObject = (JSONObject) obj;
        	return (JSONArray) jsonObject.get("users");
    	} catch (Exception e) {
    		System.out.println(e.getMessage());
    		return null;
    	}
    	
    }
}
