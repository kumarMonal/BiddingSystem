package com.monal.OnlineBiddingSystem.BiddingSystem.Dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.monal.OnlineBiddingSystem.BiddingSystem.Model.UserBid;

public interface UserBidDao extends JpaRepository<UserBid, Integer>{

}
