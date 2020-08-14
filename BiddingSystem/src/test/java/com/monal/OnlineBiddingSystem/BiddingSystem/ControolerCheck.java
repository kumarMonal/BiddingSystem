package com.monal.OnlineBiddingSystem.BiddingSystem;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.LinkedList;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
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
import com.monal.OnlineBiddingSystem.BiddingSystem.dto.AuctionSupportive;
import com.monal.OnlineBiddingSystem.BiddingSystem.dto.ResponseSupportive;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ControolerCheck {


	@InjectMocks
	AuctionController auc;
	
    @Mock
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
    void checkPagingGETApiWithSecurity() throws Exception {
    	mvc.perform(get("/auction?status=RUNNING&page=0&size=1").contentType(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isOk()); 
   }
    
    @WithMockUser("Monal")
    @Test
    void checkPostApiWithSecurity201() throws Exception {
    	AuctionSupportive a1=new AuctionSupportive(count);
    	String input=om.writeValueAsString(a1);
    	MvcResult mr=mvc.perform(post("/auction/5/bid").content(input).contentType(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isCreated()).andReturn();
    	assertEquals(201, mr.getResponse().getStatus());
    }
 
    @WithMockUser("Monal")
    @Test
    void checkPostApiWithSecurity406() throws Exception {
    	AuctionSupportive a1=new AuctionSupportive(count);
    	String input=om.writeValueAsString(a1);
    	MvcResult mr=mvc.perform(post("/auction/5/bid").content(input).contentType(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isNotAcceptable()).andReturn();
    	assertEquals(406, mr.getResponse().getStatus());
    }
    
    @WithMockUser("Monal")
    @Test
    void checkPostApiWithSecurity404() throws Exception {
    	AuctionSupportive a1=new AuctionSupportive(count);
    	String input=om.writeValueAsString(a1);
    	MvcResult mr=mvc.perform(post("/auction/1/bid").content(input).contentType(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isNotFound()).andReturn();
    	assertEquals(404, mr.getResponse().getStatus());
    }
    
    @Test
    void checkResponse() throws Exception {
        Pageable pag = PageRequest.of(0, 1);
        LinkedList<ResponseSupportive> ans=new LinkedList<>();
        ans.add(new ResponseSupportive(10,20,100));
        Page<ResponseSupportive> p=new PageImpl<ResponseSupportive>(ans);
        when(aImpl.getAllAuctionsResponse(pag)).thenReturn(p);
    	assertTrue(auc.getResponse("RUNNING", pag).size()==1);
   }
    
    @Test
    void checkPlaceBid() throws Exception {
        Pageable pag = PageRequest.of(0, 1);
		AuctionSupportive a1=new AuctionSupportive(300);
    	when(auc.addBid(a1, 1, pag)).thenReturn(new ResponseEntity<String>(HttpStatus.CREATED));
    	assertEquals(new ResponseEntity<String>(HttpStatus.CREATED),auc.addBid(a1, 1, pag));
   }

}
