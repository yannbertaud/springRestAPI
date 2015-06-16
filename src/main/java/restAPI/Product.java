package main.java.restAPI;

public class Product {
	private String description;
	private String name;
	private Long id;
	private float price;
	
	
	public Product(String name, String description, float price, Long id){
		this.name = name;
		this.description = description;
		this.price = price;
		this.id = id;
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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}
	
}
