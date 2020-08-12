package com.monal.OnlineBiddingSystem.BiddingSystem.dto;

public class ResponseSupportive {
      
	int itemId;
	int highestRate;
	int StepRate;
	
	public ResponseSupportive() {
	}

	public ResponseSupportive(int itemId, int highestRate, int stepRate) {
		super();
		this.itemId = itemId;
		this.highestRate = highestRate;
		StepRate = stepRate;
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
		return StepRate;
	}

	public void setStepRate(int stepRate) {
		StepRate = stepRate;
	}
	
}
