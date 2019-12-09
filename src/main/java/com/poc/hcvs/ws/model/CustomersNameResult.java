package com.poc.hcvs.ws.model;

public class CustomersNameResult {
	Long id;
	String customerName;
	
	public CustomersNameResult(Long id, String customerName) {
		super();
		this.id = id;
		this.customerName = customerName;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	
	
}
