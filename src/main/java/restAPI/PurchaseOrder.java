package main.java.restAPI;

public class PurchaseOrder {
	private int id;
	private int userId;
	private Product product;
	
	public PurchaseOrder(int userId, Product product) {
		this.userId = userId;
		this.product = product;
		
	}

	public int getId() {
		return id;
	}

	public int getUserId() {
		return userId;
	}

	public Product getProduct() {
		return product;
	}
	
	
}
