package com.poc.hcvs.ui.entity.request;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author SonaSach
 * 
 *  This class should have the matching fields coming from HTTP request
 *  json payload.
 *
 */
public class CustomerDetailsRequestModel {

	private int id;
	private String customerName;
	private String tier1Name;
	private String tier2Name;
	private String tier3Name;
	private String tier4Name;
	private String tier5Name;
	private String tier6Name;
	private MultipartFile logo;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
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
	public MultipartFile getLogo() {
		return logo;
	}
	public void setLogo(MultipartFile logo) {
		this.logo = logo;
	}
    
}
