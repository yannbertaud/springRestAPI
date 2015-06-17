package main.java.restAPI;

import org.json.simple.JSONObject;

public class Entitlement {
	private long id;
	private long userId;
	private long productId;
	private boolean active;
	
	public Entitlement(long entitlementId, long userId, long productId) {
		this.id = entitlementId;
		this.userId = userId;
		this.productId = productId;
		this.active = true;
	}
	
	public Entitlement(JSONObject jsonObject) {
		this.id = (long) jsonObject.get("id");
		this.userId = (long) jsonObject.get("userId");
		this.productId = (long) jsonObject.get("productId");
		this.active = (boolean) jsonObject.get("active");
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
	
	public void setActive(boolean activeFlag) {
		this.active = activeFlag;
	}
	
	public boolean isActive() {
		return active;
	}

}
