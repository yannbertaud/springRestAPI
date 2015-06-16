package main.java.restAPI;


import org.json.simple.JSONArray;
import org.json.simple.JSONObject;


public class User {
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	private long userId;
	public User() {
		
	}
	
	public User(String userName) {
		
	}
	
	public User(long userId) {
		
	}
	
	public User(JSONObject jsonUser) {
		this.firstName = (String) jsonUser.get("firstName");
		this.lastName = (String) jsonUser.get("lastName");
		this.userId = (long) jsonUser.get("id");
	}
	
	public boolean create(String firstName, String lastName, long userId) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.userId = userId;
		return true;
	}
	
	public boolean create(String firstName, String lastName, String email, String password) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		return true;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}
	
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("firstName: "  + this.firstName);
		sb.append("\nlastName: " + this.lastName);
		sb.append("\nemail: " + this.email);
		sb.append("\npassword: " + this.password);
		sb.append("\nuserId: " + this.userId);
		return sb.toString();
	}
	
	public JSONObject toJsonObject() {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("userId", this.userId);
		jsonObject.put("firstName", this.firstName);
		jsonObject.put("lastName", this.lastName);
		jsonObject.put("email", this.email);
		jsonObject.put("password", this.password);
		return jsonObject;
	}
}
