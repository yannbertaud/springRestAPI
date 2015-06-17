package main.java.restAPI;

import java.io.FileReader;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class DataUtil {
	private String userDir = System.getProperty("user.dir");
	
	public JSONArray getUsersJson() {
		JSONObject jsonObject = getJSONFile("users.json");
		return (JSONArray) jsonObject.get("users");
	}
	
	public JSONArray getProductsJson() {
		JSONObject jsonObject = getJSONFile("products.json");
		return (JSONArray) jsonObject.get("products");
	}
	
	private JSONObject getJSONFile(String filename) {
		JSONParser jsonParser = new JSONParser();
		try {
    		Object obj = jsonParser.parse(new FileReader(userDir + "/data/" + filename));
    		JSONObject jsonObject = (JSONObject) obj;
    		return jsonObject;
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
	}
}
