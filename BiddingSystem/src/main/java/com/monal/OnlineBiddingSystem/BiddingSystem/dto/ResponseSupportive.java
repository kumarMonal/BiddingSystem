package com.monal.OnlineBiddingSystem.BiddingSystem.dto;

public class ResponseSupportive {
      
	private int itemId;
	private int highestRate;
	private int stepRate;
	
	public ResponseSupportive() {
	}

	public ResponseSupportive(int itemId, int highestRate, int stepRate) {
		this.itemId = itemId;
		this.highestRate = highestRate;
		this.stepRate = stepRate;
	}

	public int getItemId() {
		return itemId;
	}

	public void setItemId(int itemId) {
		this.itemId = itemId;
	}

	public int getHighestRate() {
		return highestRate;
	}

	public void setHighestRate(int highestRate) {
		this.highestRate = highestRate;
	}

	public int getStepRate() {
		return stepRate;
	}

	public void setStepRate(int stepRate) {
		this.stepRate = stepRate;
	}
	
}
