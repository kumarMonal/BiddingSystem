package com.monal.OnlineBiddingSystem.BiddingSystem.Impl;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

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

@Service
public class AuctionServiceImpl implements AuctionService{
	
	private ItemDao itemDao;
	private UserDao userDao;
	private UserBidDao userBidDao;
	private AuctionDao auctionDao;
	
	@Autowired
	public AuctionServiceImpl(ItemDao itemDao,UserDao userDao,UserBidDao userBidDao,AuctionDao auctionDao) {
		this.itemDao=itemDao;
		this.userDao=userDao;
		this.userBidDao=userBidDao;
		this.auctionDao=auctionDao;
	}

	@SuppressWarnings({ "finally" })
	@Override
	public Page<ResponseSupportive> getAllAuctionsResponse(Pageable pag) {
		Logger logg=LoggerFactory.getLogger(AuctionServiceImpl.class);
		LinkedList<ResponseSupportive> ans=new LinkedList<>();
		Page<ResponseSupportive> myPage=new PageImpl<>(ans,pag,ans.size());
		try {
			HashMap<Integer,Auction> hm=getRunningAuction(pag);
			List<UserBid> ll=getAllUserBids(pag).getContent();
			HashMap<Integer,Integer> h=new HashMap<>();
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
	        	 if(h.containsKey(it)) {
	        	   ans.add(new ResponseSupportive(it,h.get(it),hm.get(it).getStepRate()));
	        	   myPage=new PageImpl<>(ans,pag,ans.size());}
	         }	
		}
		catch(Exception e) {
			logg.info("Service"+e);
		}
		finally {
			return myPage;
		}
	}

	
	@Override
	public Page<Auction> getAllAuctions(Pageable pag) {
		return auctionDao.findAll(pag);
	}

	@Override
	public Page<UserBid> getAllUserBids(Pageable pag) {
		return userBidDao.findAll(pag);
	}

	@Override
	public Page<User> getAllUsers(Pageable pag) {
		return userDao.findAll(pag);
	}

	@Override
	public Page<Item> getAllItems(Pageable pag) {
		return  itemDao.findAll(pag);
	}

	@Override
	public HashMap<Integer,Auction> getRunningAuction(Pageable pag) {
		List<Auction> ll=getAllAuctions(pag).getContent();
		Map<Integer, Auction> hm=ll.stream().filter(x->x.getStatus().equals("RUNNING")).collect(Collectors.toMap(x->x.getItem(), x->x));
		return (new HashMap<Integer,Auction>(hm));
	}


	@Override
	public ResponseEntity<String> addBid(int itemId, int bidAmount,Pageable pag) {
		org.slf4j.Logger logg=LoggerFactory.getLogger(AuctionServiceImpl.class);
		int uid=getLoggedUserId(getLoogedUser(),pag);
		int temp=-1;
		int tempStepRate=-1;
		try {
			for(ResponseSupportive u: getAllAuctionsResponse(pag)) {
				if(u.getItemId()==itemId){
					temp=u.getHighestRate();
					tempStepRate=u.getStepRate();
					break;
				}
			}	
		if(temp==-1&&tempStepRate==-1)
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		String st="REJECT";
		HashMap<Integer,Auction> hm=getRunningAuction(pag);
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
		logg.info("Please check your Bidding"+e);
		return new ResponseEntity<>("Bidding is not good2",HttpStatus.NOT_FOUND);
	  }
	}

	@Override
	public String getLoogedUser() {
	Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	if (principal instanceof UserDetails) {
         return ((UserDetails)principal).getUsername();
}
		return "";
	}

	@Override
	public int getLoggedUserId(String name,Pageable pag) {
		List<User> ll=getAllUsers(pag).getContent();
		int uid=-1;
		for(User u: ll) {
			if(u.getName().equals(name)) {
				uid=u.getUserId();
			}
		}
		return uid;
	}

}
