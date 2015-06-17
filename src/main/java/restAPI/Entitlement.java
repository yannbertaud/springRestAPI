package main.java.restAPI;

public class Entitlement {
	private long entitlementId;
	private long userId;
	private long productId;
	private boolean active;
	
	public Entitlement(long entitlementId, long userId, long productId) {
		this.entitlementId = entitlementId;
		this.userId = userId;
		this.productId = productId;
		this.active = true;
	}
	
	public long getEntitlementId() {
		return entitlementId;
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
