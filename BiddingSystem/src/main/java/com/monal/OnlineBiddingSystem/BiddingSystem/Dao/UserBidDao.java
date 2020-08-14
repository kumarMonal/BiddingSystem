package com.monal.OnlineBiddingSystem.BiddingSystem.Dao;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.monal.OnlineBiddingSystem.BiddingSystem.Model.UserBid;

public interface UserBidDao extends PagingAndSortingRepository<UserBid, Integer>{

}
