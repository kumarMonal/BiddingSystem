package com.monal.OnlineBiddingSystem.BiddingSystem.Dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.monal.OnlineBiddingSystem.BiddingSystem.Model.User;

public interface UserDao extends JpaRepository<User, Integer>{

}
