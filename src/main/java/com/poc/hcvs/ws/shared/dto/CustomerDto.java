package com.poc.hcvs.ws.shared.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class CustomerDto {

	private long id;
	private String customerName;
	private String tier1Name;
	private String tier2Name;
	private String tier3Name;
	private String tier4Name;
	private String tier5Name;
	private String tier6Name;
	private String logo;
	private String postalCode;
	private boolean isActive;
	
	private int userId;
	
	//private UserDto userDto;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getTier1Name() {
		return tier1Name;
	}
	public void setTier1Name(String tier1Name) {
		this.tier1Name = tier1Name;
	}
	public String getTier2Name() {
		return tier2Name;
	}
	public void setTier2Name(String tier2Name) {
		this.tier2Name = tier2Name;
	}
	public String getTier3Name() {
		return tier3Name;
	}
	public void setTier3Name(String tier3Name) {
		this.tier3Name = tier3Name;
	}
	public String getTier4Name() {
		return tier4Name;
	}
	public void setTier4Name(String tier4Name) {
		this.tier4Name = tier4Name;
	}
	public String getTier5Name() {
		return tier5Name;
	}
	public void setTier5Name(String tier5Name) {
		this.tier5Name = tier5Name;
	}
	public String getTier6Name() {
		return tier6Name;
	}
	public void setTier6Name(String tier6Name) {
		this.tier6Name = tier6Name;
	}
	public String getLogo() {
		return logo;
	}
	public void setLogo(String logo) {
		this.logo = logo;
	}
	public String getPostalCode() {
		return postalCode;
	}
	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}
	public boolean isActive() {
		return isActive;
	}
	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	/*
	 * public UserDto getUserDto() { return userDto; } public void
	 * setUserDto(UserDto userDto) { this.userDto = userDto; }
	 */
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	
	
	
}
