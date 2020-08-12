package com.monal.OnlineBiddingSystem.BiddingSystem.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;

import com.monal.OnlineBiddingSystem.BiddingSystem.Model.Auction;
import com.monal.OnlineBiddingSystem.BiddingSystem.Model.Item;
import com.monal.OnlineBiddingSystem.BiddingSystem.Model.User;
import com.monal.OnlineBiddingSystem.BiddingSystem.Model.UserBid;
import com.monal.OnlineBiddingSystem.BiddingSystem.dto.AuctionSupportive;
import com.monal.OnlineBiddingSystem.BiddingSystem.dto.ResponseSupportive;

public interface AuctionService {
        
	List<ResponseSupportive> getAllAuctionsResponse();
	List<Auction> getAllAuctions();
	List<UserBid> getAllUserBids();
	List<User> getAllUsers();
	List<Item> getAllItems();
	HashMap<Integer,Auction> getRunningAuction();
	ResponseEntity<String> addBid(int itemId,int bidAmount);
	String getLoogedUser();
	int getLoggedUserId(String name);
}
