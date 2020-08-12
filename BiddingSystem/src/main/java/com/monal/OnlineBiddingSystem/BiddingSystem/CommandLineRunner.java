package com.monal.OnlineBiddingSystem.BiddingSystem;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.monal.OnlineBiddingSystem.BiddingSystem.Dao.AuctionDao;
import com.monal.OnlineBiddingSystem.BiddingSystem.Dao.ItemDao;
import com.monal.OnlineBiddingSystem.BiddingSystem.Dao.UserBidDao;
import com.monal.OnlineBiddingSystem.BiddingSystem.Dao.UserDao;
import com.monal.OnlineBiddingSystem.BiddingSystem.Model.Auction;
import com.monal.OnlineBiddingSystem.BiddingSystem.Model.Item;
import com.monal.OnlineBiddingSystem.BiddingSystem.Model.User;
import com.monal.OnlineBiddingSystem.BiddingSystem.Model.UserBid;

@Component
public class CommandLineRunner implements org.springframework.boot.CommandLineRunner{

	@Autowired
	UserDao userDao;
	
	@Autowired
	ItemDao itemDao;
	
	@Autowired
	AuctionDao auctionDao;
	
	@Autowired
	UserBidDao userBidDao;
	
	@Override
	public void run(String... args) throws Exception {
       User u1=new User("Monal",24);
       User u2=new User("Raj",28);
       User u3=new User("Kumar",35);
       User u4=new User("Raunik",40);
       userDao.save(u1);
       userDao.save(u2);
       userDao.save(u3);
       userDao.save(u4);
       Item i1=new Item("Mobile","Phone");
       Item i2=new Item("TV","Television");
       Item i3=new Item("Book","Study");
       Item i4=new Item("Pen","Writing");
       itemDao.save(i1);
       itemDao.save(i2);
       itemDao.save(i3);
       itemDao.save(i4);
       UserBid ub1=new UserBid(u1.getUserId(),100,i1.getItemId(),"REJECT");
       UserBid ub2=new UserBid(u1.getUserId(),300,i3.getItemId(),"REJECT");
       UserBid ub3=new UserBid(u2.getUserId(),100,i1.getItemId(),"REJECT");
       UserBid ub4=new UserBid(u3.getUserId(),200,i2.getItemId(),"REJECT");
       UserBid ub5=new UserBid(u2.getUserId(),400,i4.getItemId(),"REJECT");
       UserBid ub6=new UserBid(u4.getUserId(),100,i1.getItemId(),"REJECT");
       userBidDao.save(ub1);
       userBidDao.save(ub2);
       userBidDao.save(ub3);
       userBidDao.save(ub4);
       userBidDao.save(ub5);
       userBidDao.save(ub6);
       Auction a1=new Auction(i1.getItemId(),100,10,Arrays.asList(ub1,ub3,ub6),"RUNNING");
       Auction a2=new Auction(i2.getItemId(),200,20,Arrays.asList(ub4),"RUNNING");
       Auction a3=new Auction(i3.getItemId(),300,30,Arrays.asList(ub2),"RUNNING");
       Auction a4=new Auction(i4.getItemId(),400,40,Arrays.asList(ub5),"OVER");
       auctionDao.save(a1);
       auctionDao.save(a2);
       auctionDao.save(a3);
       auctionDao.save(a4);
	}

}
