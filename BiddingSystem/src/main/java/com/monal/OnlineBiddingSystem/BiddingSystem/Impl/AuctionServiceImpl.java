package com.monal.OnlineBiddingSystem.BiddingSystem.Impl;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.monal.OnlineBiddingSystem.BiddingSystem.Dao.AuctionDao;
import com.monal.OnlineBiddingSystem.BiddingSystem.Dao.ItemDao;
import com.monal.OnlineBiddingSystem.BiddingSystem.Dao.UserBidDao;
import com.monal.OnlineBiddingSystem.BiddingSystem.Dao.UserDao;
import com.monal.OnlineBiddingSystem.BiddingSystem.Model.Auction;
import com.monal.OnlineBiddingSystem.BiddingSystem.Model.Item;
import com.monal.OnlineBiddingSystem.BiddingSystem.Model.User;
import com.monal.OnlineBiddingSystem.BiddingSystem.Model.UserBid;
import com.monal.OnlineBiddingSystem.BiddingSystem.Service.AuctionService;
import com.monal.OnlineBiddingSystem.BiddingSystem.dto.ResponseSupportive;

@Component
public class AuctionServiceImpl implements AuctionService{

	@Autowired
	ItemDao itemDao;
	
	@Autowired
	UserDao userDao;
	
	@Autowired
	UserBidDao userBidDao;
	
	@Autowired
	AuctionDao auctionDao;
	
	@Autowired
	EntityManager em;
	
	@SuppressWarnings({ "unchecked", "finally" })
	@Override
	public List<ResponseSupportive> getAllAuctionsResponse() {
		List<ResponseSupportive> ans=new LinkedList<ResponseSupportive>();
		try {
			HashMap<Integer,Auction> hm=getRunningAuction();
			List<UserBid> ll=getAllUserBids();
			HashMap<Integer,Integer> h=new HashMap<Integer,Integer>();
			for(UserBid ub: ll) {
				int tempId=ub.getItem();
				if(h.containsKey(tempId)) {
					if(h.get(tempId)<ub.getBidAmount()) {
						h.replace(tempId, ub.getBidAmount());
					}
				}
				else {
					h.put(tempId, ub.getBidAmount());
				}
			}
	         for(Integer it: hm.keySet()) {
	        	 if(h.containsKey(it))
	        	 ans.add(new ResponseSupportive(it,h.get(it),hm.get(it).getStepRate()));
	         }	
		}
		catch(Exception e) {
			System.out.println(e);
		}
		finally {
			return ans;
		}
	}

	@Override
	public List<Auction> getAllAuctions() {
		return auctionDao.findAll();
	}

	@Override
	public List<UserBid> getAllUserBids() {
		return userBidDao.findAll();
	}

	@Override
	public List<User> getAllUsers() {
		return userDao.findAll();
	}

	@Override
	public List<Item> getAllItems() {
		return itemDao.findAll();
	}

	@Override
	public HashMap<Integer,Auction> getRunningAuction() {
		List<Auction> ll=getAllAuctions();
		Map<Integer, Auction> hm=ll.stream().filter(x->x.getStatus().equals("RUNNING")).collect(Collectors.toMap(x->x.getItem(), x->x));
		return (new HashMap<Integer,Auction>(hm));
	}


	@Override
	public ResponseEntity<String> addBid(int itemId, int bidAmount) {
		int uid=getLoggedUserId(getLoogedUser());
		int temp=-1;
		int tempStepRate=-1;
		try {
			for(ResponseSupportive u: getAllAuctionsResponse()) {
				if(u.getItemId()==itemId){
					temp=u.getHighestRate();
					tempStepRate=u.getStepRate();
					break;
				}
			}	
		if(temp==-1&&tempStepRate==-1)
			return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
		String st="REJECT";
		HashMap<Integer,Auction> hm=getRunningAuction();
		int tempBaseRate=hm.get(itemId).getBaseRate();
		if(tempStepRate+temp<=bidAmount&&tempBaseRate<=bidAmount) {
			st="ACCEPT";
		userBidDao.save(new UserBid(uid,bidAmount,itemId,st));
		return new ResponseEntity<>(HttpStatus.CREATED);
		}
		userBidDao.save(new UserBid(uid,bidAmount,itemId,st));
		return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
		}
	catch(Exception e) {
		System.out.println("Please check your Bidding"+e);
		return new ResponseEntity<String>("Bidding is not good2",HttpStatus.NOT_FOUND);
	  }
	}

	@Override
	public String getLoogedUser() {
	Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	if (principal instanceof UserDetails) {
	  String username = ((UserDetails)principal).getUsername();
	  return username;
}
		return "";
	}

	@Override
	public int getLoggedUserId(String name) {
		List<User> ll=getAllUsers();
		int uid=-1;
		for(User u: ll) {
			if(u.getName().equals(name)) {
				uid=u.getUserId();
			}
		}
		return uid;
	}

}
