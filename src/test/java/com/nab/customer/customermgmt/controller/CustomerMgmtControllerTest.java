package com.nab.customer.customermgmt.controller;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.core.Is.is;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.refEq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.nab.customer.customermgmt.model.Customer;
import com.nab.customer.customermgmt.service.CustomerService;
import com.nab.customer.customermgmt.util.TestUtil;

public class CustomerMgmtControllerTest {
	
	private MockMvc mockMvc;
	
	@Mock
    private CustomerService customerService;

    @InjectMocks
    private CustomerMgmtController customerMgmtController;

    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(customerMgmtController).build();
    }
    
    @Test
    public void test_list_success() throws Exception {
        List<Customer> customers = Arrays.asList(
                new Customer(new Long(1), "John", "", "Snow", "", "Mr", "23 coll st", "", "melbourne",
                		"vic", "australia", "3000", "male", "married", 50, true),                	    
                new Customer(new Long(2), "Peter", "", "Carig", "", "Mr", "288 coll st", "", "melbourne",
                		"vic", "australia", "3000", "male", "single", 70, true));

        when(customerService.list()).thenReturn(customers);
        mockMvc.perform(get("/customers"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].firstname", is("John")))
                .andExpect(jsonPath("$[0].middlename", is("")))
                .andExpect(jsonPath("$[0].lastname", is("Snow")))
                .andExpect(jsonPath("$[0].initials", is("")))
                .andExpect(jsonPath("$[0].title", is("Mr")))
                .andExpect(jsonPath("$[0].address", is("23 coll st")))
                .andExpect(jsonPath("$[0].suburb", is("")))
                .andExpect(jsonPath("$[0].city", is("melbourne")))
                .andExpect(jsonPath("$[0].state", is("vic")))
                .andExpect(jsonPath("$[0].country", is("australia")))
                .andExpect(jsonPath("$[0].zipcode", is("3000")))
                .andExpect(jsonPath("$[0].sex", is("male")))
                .andExpect(jsonPath("$[0].maritalstatus", is("married")))
                .andExpect(jsonPath("$[0].creditrating", is(50)))
                .andExpect(jsonPath("$[0].isnabcustomer", is(true)))
                .andExpect(jsonPath("$[1].id", is(2)))
                .andExpect(jsonPath("$[1].firstname", is("Peter")));
        verify(customerService, times(1)).list();
        verifyNoMoreInteractions(customerService);
    }
    
    @Test
    public void test_get_success() throws Exception {
        Customer customer = new Customer(new Long(1), "John", "", "Snow", "", "Mr", "23 coll st", "", "melbourne",
        		"vic", "australia", "3000", "male", "married", 50, true);
        when(customerService.get(1)).thenReturn(customer);
        mockMvc.perform(get("/customer/{id}", 1))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))                
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.firstname", is("John")))
                .andExpect(jsonPath("$.middlename", is("")))
                .andExpect(jsonPath("$.lastname", is("Snow")))
                .andExpect(jsonPath("$.initials", is("")))
                .andExpect(jsonPath("$.title", is("Mr")))
                .andExpect(jsonPath("$.address", is("23 coll st")))
                .andExpect(jsonPath("$.suburb", is("")))
                .andExpect(jsonPath("$.city", is("melbourne")))
                .andExpect(jsonPath("$.state", is("vic")))
                .andExpect(jsonPath("$.country", is("australia")))
                .andExpect(jsonPath("$.zipcode", is("3000")))
                .andExpect(jsonPath("$.sex", is("male")))
                .andExpect(jsonPath("$.maritalstatus", is("married")))
                .andExpect(jsonPath("$.creditrating", is(50)))
                .andExpect(jsonPath("$.isnabcustomer", is(true)));
        verify(customerService, times(1)).get(1);
        verifyNoMoreInteractions(customerService);
    }    
    
    @Test
    public void test_get_fail_404_not_found() throws Exception {
        when(customerService.get(1)).thenReturn(null);
        mockMvc.perform(get("/customer/{id}", 1))
                .andExpect(status().isNotFound());
        verify(customerService, times(1)).get(1);
        verifyNoMoreInteractions(customerService);
    }
    
    @Test
    public void test_list_fail_404_not_found() throws Exception {
        when(customerService.list()).thenReturn(null);
        mockMvc.perform(get("/customers"))
                .andExpect(status().isNotFound());
        verify(customerService, times(1)).list();
        verifyNoMoreInteractions(customerService);
    }
    
    @Test
    public void test_save_success() throws Exception {
    	Customer customer = new Customer(new Long(1), "John", "Mid", "Snow", "N", "Mr", "23 coll st", "-", "melbourne",
        		"vic", "australia", "3000", "male", "married", 50, true);
        when(customerService.save(customer)).thenReturn(customer.getId());
        mockMvc.perform(post("/customer")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(TestUtil.asJsonString(customer)))
                .andExpect(status().isCreated())
                .andExpect(header().string("location", containsString("/customer")));
        verify(customerService, times(1)).save(refEq(customer));
        verifyNoMoreInteractions(customerService);
    }
    
    @Test
    public void test_update_success() throws Exception {
    	Customer customer = new Customer(new Long(1), "John", "Mid", "Snow", "N", "Mr", "23 coll st", "-", "melbourne",
        		"vic", "australia", "3000", "male", "married", 50, true);
        doNothing().when(customerService).update(any(Long.class), any(Customer.class));
        mockMvc.perform(put("/customer/{id}", customer.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(TestUtil.asJsonString(customer)))
                .andExpect(status().isOk());
        verify(customerService, times(1)).update(any(Long.class), any(Customer.class));
        verifyNoMoreInteractions(customerService);
    }
    
    @Test
    public void test_delete_success() throws Exception {
    	Customer customer = new Customer(new Long(1), "John", "", "Snow", "", "Mr", "23 coll st", "", "melbourne",
        		"vic", "australia", "3000", "male", "married", 50, true);
    	doNothing().when(customerService).delete(customer.getId());
        mockMvc.perform(delete("/customer/{id}", customer.getId()))
                .andExpect(status().isOk());
        verify(customerService, times(1)).delete(customer.getId());
        verifyNoMoreInteractions(customerService);
    }  
    
    @After
    public void tearDown() {
    	mockMvc = null;
    }
}
