package com.monal.OnlineBiddingSystem.BiddingSystem.dto;

public class AuctionSupportive {

	private int bidAmount;
    
	public AuctionSupportive() {
		super();
	}
    
	public AuctionSupportive(int bidAmount) {
		super();
		this.bidAmount = bidAmount;
	}

	public int getBidAmount() {
		return bidAmount;
	}

	public void setBidAmount(int bidAmount) {
		this.bidAmount = bidAmount;
	}
	
}
