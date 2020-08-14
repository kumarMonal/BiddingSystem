package com.monal.OnlineBiddingSystem.BiddingSystem.Service;

import java.util.HashMap;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import com.monal.OnlineBiddingSystem.BiddingSystem.Model.Auction;
import com.monal.OnlineBiddingSystem.BiddingSystem.Model.Item;
import com.monal.OnlineBiddingSystem.BiddingSystem.Model.User;
import com.monal.OnlineBiddingSystem.BiddingSystem.Model.UserBid;
import com.monal.OnlineBiddingSystem.BiddingSystem.dto.ResponseSupportive;

public interface AuctionService {
        
	Page<ResponseSupportive> getAllAuctionsResponse(Pageable pag);
	Page<Auction> getAllAuctions(Pageable pag);
	Page<UserBid> getAllUserBids(Pageable pag);
	Page<User> getAllUsers(Pageable pag);
	Page<Item> getAllItems(Pageable pag);
	HashMap<Integer,Auction> getRunningAuction(Pageable pag);
	ResponseEntity<String> addBid(int itemId,int bidAmount,Pageable pag);
	String getLoogedUser();
	int getLoggedUserId(String name,Pageable pag);
}
