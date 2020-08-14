package com.monal.OnlineBiddingSystem.BiddingSystem.Dao;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.monal.OnlineBiddingSystem.BiddingSystem.Model.Item;

public interface ItemDao extends PagingAndSortingRepository<Item, Integer>{
 
}
