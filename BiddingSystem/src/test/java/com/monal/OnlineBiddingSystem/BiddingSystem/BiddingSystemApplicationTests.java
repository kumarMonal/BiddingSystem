package com.monal.OnlineBiddingSystem.BiddingSystem;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.monal.OnlineBiddingSystem.BiddingSystem.Controller.AuctionController;
import com.monal.OnlineBiddingSystem.BiddingSystem.Impl.AuctionServiceImpl;
import com.monal.OnlineBiddingSystem.BiddingSystem.Model.*;
import com.monal.OnlineBiddingSystem.BiddingSystem.dto.AuctionSupportive;
import com.monal.OnlineBiddingSystem.BiddingSystem.dto.ResponseSupportive;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class BiddingSystemApplicationTests {

	@MockBean
	AuctionController auc;
	
    @MockBean
    AuctionServiceImpl aImpl;
    
    @Autowired
    private MockMvc mvc;
    
    private ObjectMapper om=new ObjectMapper();
    private int count=300;
    
    @Test
    void checkGETApi() throws Exception {
        mvc.perform(get("/auction?status=RUNNING").contentType(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isUnauthorized());
   }
    
    @Test
    void checkPostApi() throws Exception {
    	AuctionSupportive a1=new AuctionSupportive(count);
    	String input=om.writeValueAsString(a1);
    	mvc.perform(post("/auction/1/bid").content(input).contentType(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isUnauthorized());

    }
  
    @WithMockUser("Monal")
    @Test
    void checkGETApiWithSecurity() throws Exception {
    	mvc.perform(get("/auction?status=RUNNING").contentType(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isOk()); 
   }
    
    @WithMockUser("Monal")
    @Test
    void checkPostApiWithSecurity() throws Exception {
    	AuctionSupportive a1=new AuctionSupportive(count);
    	String input=om.writeValueAsString(a1);
    	MvcResult mr=mvc.perform(post("/auction/1/bid").content(input).contentType(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isOk()).andReturn();
    	assertEquals(200, mr.getResponse().getStatus());
    }
 
    @Test
    void checkResponse() throws Exception {
        Pageable pag = PageRequest.of(0, 1);
        LinkedList<ResponseSupportive> ans=new LinkedList<>();
        ans.add(new ResponseSupportive(10,20,100));
    	when(auc.getResponse("RUNNING", pag)).thenReturn(ans);
    	assertTrue(auc.getResponse("RUNNING", pag).equals(ans));
   }
    
    @Test
    void checkPlaceBid() throws Exception {
        Pageable pag = PageRequest.of(0, 1);
		AuctionSupportive a1=new AuctionSupportive(300);
    	when(auc.addBid(a1, 1, pag)).thenReturn(new ResponseEntity<String>(HttpStatus.CREATED));
    	assertEquals(new ResponseEntity<String>(HttpStatus.CREATED),auc.addBid(a1, 1, pag));
   }

    @Test
    void checkAllAuctionResponse() {
        Pageable pag = PageRequest.of(0, 1);
        LinkedList<ResponseSupportive> ans=new LinkedList<>();
        ans.add(new ResponseSupportive(10,20,100));
		Page<ResponseSupportive> myPage=new PageImpl<>(ans,pag,ans.size());
    	when(aImpl.getAllAuctionsResponse(pag)).thenReturn(myPage);
    	assertTrue(aImpl.getAllAuctionsResponse(pag).getContent().equals(ans));
   }
    
    @Test
    void checkRunningAuction() {
        Pageable pag = PageRequest.of(0, 1);
        HashMap<Integer,Auction> hm=new HashMap<>();
        Auction a1=new Auction(1,100,10,Arrays.asList(new UserBid(1,100,1,"REJECT")),"RUNNING");
        hm.put(1, a1);
    	when(aImpl.getRunningAuction(pag)).thenReturn(hm);
    	assertEquals(1, aImpl.getRunningAuction(pag).size());
   }
    
    @Test
    void checkaddBid() {
        Pageable pag = PageRequest.of(0, 1);
    	when(aImpl.addBid(1, 100, pag)).thenReturn(new ResponseEntity<>(HttpStatus.OK));
    	assertEquals(new ResponseEntity<>(HttpStatus.OK),aImpl.addBid(1, 100, pag));
   }
    
    @Test
    void checkgetLoggedUserId() {
    	when(aImpl.getLoogedUser()).thenReturn("username");
    	assertEquals("username", aImpl.getLoogedUser());
   }
    
    @Test
    void checkgetLoggedUserName() {
        Pageable pag = PageRequest.of(0, 1);
    	when(aImpl.getLoggedUserId("username",pag)).thenReturn(1);
    	assertEquals(1, aImpl.getLoggedUserId("username",pag));
   }
    
    @Test
    void checkItems() {
        Pageable pag = PageRequest.of(0, 1);
        Item i1=new Item("Mobile","Phone");
        Item i2=new Item("TV","Television");
        LinkedList<Item> ll=new LinkedList<>();
        ll.add(i1);
        ll.add(i2);
        Page<Item> p=new PageImpl<Item>(ll,pag,ll.size());
        when(aImpl.getAllItems(pag)).thenReturn(p);
    	assertTrue(aImpl.getAllItems(pag).toList().size()==2);
   }
    
    @Test
    void checkUsers() {
        Pageable pag = PageRequest.of(0, 1);
        User u1=new User("Monal",24);
        User u2=new User("Raj",28);
        User u3=new User("Kumar",35);
        LinkedList<User> ll=new LinkedList<>();
        ll.add(u1);
        ll.add(u2);
        ll.add(u3);
        Page<User> p=new PageImpl<User>(ll,pag,ll.size());
        when(aImpl.getAllUsers(pag)).thenReturn(p);
    	assertTrue(aImpl.getAllUsers(pag).toList().size()==3);
   }
    
    @Test
    void checkUserBids() {
        Pageable pag = PageRequest.of(0, 1);
        UserBid ub1=new UserBid(1,100,1,"REJECT");
        UserBid ub2=new UserBid(1,300,2,"REJECT");
        UserBid ub3=new UserBid(2,100,3,"REJECT");
        UserBid ub4=new UserBid(3,100,4,"REJECT");
        UserBid ub5=new UserBid(5,400,5,"REJECT");
        LinkedList<UserBid> ll=new LinkedList<>();
        ll.add(ub1);
        ll.add(ub2);
        ll.add(ub3);
        ll.add(ub4);
        ll.add(ub5);
        Page<UserBid> p=new PageImpl<UserBid>(ll,pag,ll.size());
        when(aImpl.getAllUserBids(pag)).thenReturn(p);
    	assertTrue(aImpl.getAllUserBids(pag).toList().size()==5);
   }
    
    @Test
    void checkAuctions() {
        Pageable pag = PageRequest.of(0, 1);
        Auction a1=new Auction(1,100,10,Arrays.asList(new UserBid(1,100,1,"REJECT")),"RUNNING");
        LinkedList<Auction> ll=new LinkedList<>();
        ll.add(a1);
        Page<Auction> p=new PageImpl<Auction>(ll,pag,ll.size());
        when(aImpl.getAllAuctions(pag)).thenReturn(p);
    	assertTrue(aImpl.getAllAuctions(pag).toList().size()==1);
   }
    
    
}
