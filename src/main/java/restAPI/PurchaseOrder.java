package main.java.restAPI;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.json.simple.JSONObject;

public class PurchaseOrder {
	private long id;
	private long userId;
	private long productId;
	private String orderDate;
	
	public PurchaseOrder(JSONObject jsonPurchaseOrder) {
		this.id = (long) jsonPurchaseOrder.get("id");
		this.userId = (long) jsonPurchaseOrder.get("userId");
		this.productId = (long) jsonPurchaseOrder.get("productId");
		this.orderDate = (String) jsonPurchaseOrder.get("orderDate") ;
	}
	
	public PurchaseOrder(long userId, long productId) {
		this.userId = userId;
		this.productId = productId;
		
	}

	public long getId() {
		return id;
	}

	public long getUserId() {
		return userId;
	}

	public long getProductId() {
		return productId;
	}
	
	public String getOrderDate() {
		return this.orderDate;
	}
	
}
