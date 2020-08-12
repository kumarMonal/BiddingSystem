package com.monal.OnlineBiddingSystem.BiddingSystem.Dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.monal.OnlineBiddingSystem.BiddingSystem.Model.Item;

public interface ItemDao extends JpaRepository<Item, Integer>{
 
}
