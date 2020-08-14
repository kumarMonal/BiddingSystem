package com.monal.OnlineBiddingSystem.BiddingSystem.Controller;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.monal.OnlineBiddingSystem.BiddingSystem.Impl.AuctionServiceImpl;
import com.monal.OnlineBiddingSystem.BiddingSystem.dto.AuctionSupportive;
import com.monal.OnlineBiddingSystem.BiddingSystem.dto.ResponseSupportive;

@RestController
@EnableWebMvc
@RequestMapping("/auction")
public class AuctionController {
   
	private AuctionServiceImpl auctionServiceImpl;
	
    @Autowired
	public AuctionController(AuctionServiceImpl auctionServiceImpl) {
		this.auctionServiceImpl = auctionServiceImpl;
	}

	@GetMapping(value = "")
	public List<ResponseSupportive> getResponse(@RequestParam("status") String status,Pageable pag){
			if(status.equals("RUNNING"))
				return auctionServiceImpl.getAllAuctionsResponse(pag).toList();	
        return new LinkedList<>();
	}
	
	@PostMapping(value = "/{itemId}/bid")
	public ResponseEntity<String> addBid(@RequestBody AuctionSupportive auctionSupportive,@PathVariable int itemId,Pageable pag){
			return auctionServiceImpl.addBid(itemId, auctionSupportive.getBidAmount(), pag);	
	}
	
}
