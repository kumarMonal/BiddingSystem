package com.monal.OnlineBiddingSystem.BiddingSystem.Dao;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.monal.OnlineBiddingSystem.BiddingSystem.Model.User;

public interface UserDao extends PagingAndSortingRepository<User, Integer>{

}
