package com.monal.OnlineBiddingSystem.BiddingSystem.Dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.monal.OnlineBiddingSystem.BiddingSystem.Model.Auction;

public interface AuctionDao extends JpaRepository<Auction, Integer>{

}
