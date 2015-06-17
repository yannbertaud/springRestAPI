package main.java.restAPI;

import org.json.simple.JSONObject;

public class Product {
	private String description;
	private String name;
	private long id;
	private float price;
	
	
	public Product(String name, String description, float price, long id){
		this.name = name;
		this.description = description;
		this.price = price;
		this.id = id;
	}

	public Product(JSONObject jsonProduct) {
		this.name = (String) jsonProduct.get("name");
		this.description = (String) jsonProduct.get("description");
		this.price = (long) jsonProduct.get("price");
		this.id = (long) jsonProduct.get("id");
	}
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}
	
	public JSONObject toJsonObject() {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("id", this.id);
		jsonObject.put("name", this.name);
		jsonObject.put("description", description);
		jsonObject.put("price", price);
		return jsonObject;
	}
	
}
