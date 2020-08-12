package com.monal.OnlineBiddingSystem.BiddingSystem.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.monal.OnlineBiddingSystem.BiddingSystem.Impl.AuctionServiceImpl;
import com.monal.OnlineBiddingSystem.BiddingSystem.dto.AuctionSupportive;
import com.monal.OnlineBiddingSystem.BiddingSystem.dto.ResponseSupportive;

@RestController
@RequestMapping("/auction")
public class AuctionController {
   
	@Autowired
	AuctionServiceImpl auctionServiceImpl;
	

	@GetMapping(value = "")
	List<ResponseSupportive> getResponse(@RequestParam("status") String status) throws Exception{
			if(status.equals("RUNNING"))
				return auctionServiceImpl.getAllAuctionsResponse();	
        return null;
	}
	
	@PostMapping(value = "/{itemId}/bid")
	public ResponseEntity<String> addBid(@RequestBody AuctionSupportive auctionSupportive,@PathVariable int itemId) throws Exception{
			return auctionServiceImpl.addBid(itemId, auctionSupportive.getBidAmount());	
	}
	
}
