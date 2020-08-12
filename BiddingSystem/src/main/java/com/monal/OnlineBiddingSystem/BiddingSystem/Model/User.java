package com.monal.OnlineBiddingSystem.BiddingSystem.Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class User {

	@Id
	@GeneratedValue
	int userId;
	String name;
	int age;
	
	public User() {
	}

	public User(String name, int age) {
		super();
		this.name = name;
		this.age = age;
	}

	public int getUserId() {
		return userId;
	}
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}
	
	
}
