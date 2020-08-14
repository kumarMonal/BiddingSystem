package com.monal.OnlineBiddingSystem.BiddingSystem.Model;

import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


@Entity
public class Auction {
    
	@Id
	@GeneratedValue
	private int auctionId;
	private int item;
	private int baseRate;
	private int stepRate;
	@ElementCollection
	private List<UserBid> userBid;
	private String status;
	
	public Auction() {
	}
	public Auction(int item, int baseRate, int stepRate, List<UserBid> userBid, String status) {
		this.item = item;
		this.baseRate = baseRate;
		this.stepRate = stepRate;
		this.userBid = userBid;
		this.status = status;
	}
	public int getAuctionId() {
		return auctionId;
	}
	public int getItem() {
		return item;
	}
	public void setItem(int item) {
		this.item = item;
	}
	public int getBaseRate() {
		return baseRate;
	}
	public void setBaseRate(int baseRate) {
		this.baseRate = baseRate;
	}
	public int getStepRate() {
		return stepRate;
	}
	public void setStepRate(int stepRate) {
		this.stepRate = stepRate;
	}
	public List<UserBid> getUserBid() {
		return userBid;
	}
	public void setUserBid(List<UserBid> userBid) {
		this.userBid = userBid;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
}
