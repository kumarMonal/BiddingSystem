package com.monal.OnlineBiddingSystem.BiddingSystem.Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Item {

	@Id
	@GeneratedValue
	private int itemId;
	private String name;
	private String desc;
	
	public Item() {
	}

	public Item(String name, String desc) {
		this.name = name;
		this.desc = desc;
	}

	public int getItemId() {
		return itemId;
	}
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}
	
	
}
