package main.java.restAPI;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;

public class DataUtil {
	private String userDir = System.getProperty("user.dir");
	private String category;
	private JSONObject jsonObject;
	private JSONArray items;
	public DataUtil(String category) {
		this.category = category;
		jsonObject = getJSONFile(category + ".json");
		items = (JSONArray) jsonObject.get(category);
	}
	
	public JSONArray getItems() {
		return this.items;
	}
	
	public JSONObject getById(long id) {
		JSONObject jsonObject = null;
		for(int i = 0; i < this.items.size(); i++) {
			if ((long) ((JSONObject) items.get(i)).get("id") == id) {
				jsonObject = (JSONObject) items.get(i);
			}
		}
		return jsonObject;
	}
	
	public void save() {
		String filename = userDir + "/data/" + category + "2.json";
		System.out.println("writing to " + filename);
		FileWriter file;
		try {
			file = new FileWriter(filename);
			file.write(jsonObject.toString());
			file.flush();
			file.close();
			System.out.println("wrote file to: " + filename);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		
	}
	
	private JSONObject getJSONFile(String filename) {
		JSONParser jsonParser = new JSONParser();
		try {
    		Object obj = jsonParser.parse(new FileReader(userDir + "/data/" + filename));
    		JSONObject jsonObject = (JSONObject) obj;
    		return jsonObject;
			
		} catch (Exception e) {
			System.out.println("error getting json file: "  + filename + "\n" + e.getMessage());
			return null;
		}
	}
}
