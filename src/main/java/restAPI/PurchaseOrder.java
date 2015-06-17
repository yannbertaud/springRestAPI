package main.java.restAPI;

import java.util.Date;

public class PurchaseOrder {
	private int purchaseOrderId;
	private int userId;
	private int productId;
	private Date orderDate;
	
	public PurchaseOrder(int userId, int productId) {
		this.userId = userId;
		this.productId = productId;
		
	}

	public int getId() {
		return purchaseOrderId;
	}

	public int getUserId() {
		return userId;
	}

	public int getProductId() {
		return productId;
	}
	
	
}
