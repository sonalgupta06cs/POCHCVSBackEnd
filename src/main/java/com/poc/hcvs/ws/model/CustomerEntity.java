/**
 * 
 */
package com.poc.hcvs.ws.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * @author 143703
 *
 */
@Entity(name="Customers")
public class CustomerEntity implements Serializable {
	

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;

	@Column(nullable = false, length = 50)
	private String customerName;

	@Column(nullable = false, length = 50)
	private String tier1Name;

	@Column(length = 50)
	private String tier2Name;

	@Column(length = 50)
	private String tier3Name;
	
	@Column(length = 50)
	private String tier4Name;
	
	@Column(length = 50)
	private String tier5Name;
	
	@Column(length = 50)
	private String tier6Name;
	
	@Column(length = 50)
	private String logo;

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

    
	
}
