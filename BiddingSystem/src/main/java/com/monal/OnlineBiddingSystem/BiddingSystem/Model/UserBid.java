package com.monal.OnlineBiddingSystem.BiddingSystem.Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class UserBid {
   
	@Id
	@GeneratedValue
	private int bidId;
	private int user;
	private int item;
	private int bidAmount;
	private String cond;
	
	public UserBid() {
	}
	public UserBid(int user, int bidAmount, int item, String cond) {
		this.user = user;
		this.bidAmount = bidAmount;
		this.item = item;
		this.cond = cond;
	}
	public int getBidId() {
		return bidId;
	}
	public int getUser() {
		return user;
	}
	public void setUser(int user) {
		this.user = user;
	}
	public int getBidAmount() {
		return bidAmount;
	}
	public void setBidAmount(int bidAmount) {
		this.bidAmount = bidAmount;
	}
	public int getItem() {
		return item;
	}
	public void setItem(int item) {
		this.item = item;
	}
	public String getCond() {
		return cond;
	}
	public void setCOnd(String cond) {
		this.cond = cond;
	}
	
	
}
