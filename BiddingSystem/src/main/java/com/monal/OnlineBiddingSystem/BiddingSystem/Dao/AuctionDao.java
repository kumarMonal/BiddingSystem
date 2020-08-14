package com.monal.OnlineBiddingSystem.BiddingSystem.Dao;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.monal.OnlineBiddingSystem.BiddingSystem.Model.Auction;

public interface AuctionDao extends PagingAndSortingRepository<Auction, Integer>{
   
}
