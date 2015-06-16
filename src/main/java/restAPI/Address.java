package main.java.restAPI;

public class Address {

	private long addressId;
	private String streetAddress1;
	private String streetAddress2;
	private String city;
	private String state;
	private int zipcode;
	private String country;
	private boolean isBillingAddress;
	
	public Address(long addressId) {
		this.addressId = addressId;
	}
	
	public long getAddressId() {
		return this.addressId;
	}
	
	public String getStreetAddress1() {
		return streetAddress1;
	}

	public void setStreetAddress1(String streetAddress1) {
		this.streetAddress1 = streetAddress1;
	}

	public String getStreetAddress2() {
		return streetAddress2;
	}

	public void setStreetAddress2(String streetAddress2) {
		this.streetAddress2 = streetAddress2;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public int getZipcode() {
		return zipcode;
	}

	public void setZipcode(int zipcode) {
		this.zipcode = zipcode;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public boolean isBillingAddress() {
		return isBillingAddress;
	}

	public void setBillingAddress(boolean isBillingAddress) {
		this.isBillingAddress = isBillingAddress;
	}

}
