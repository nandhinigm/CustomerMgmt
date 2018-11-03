package com.nab.customer.customermgmt.dao;

import java.util.List;

import com.nab.customer.customermgmt.common.RecordNotFoundException;
import com.nab.customer.customermgmt.model.Customer;

public interface CustomerDao {
	
	long save(Customer customer);
	Customer get(long id);
	List<Customer> list();
	void update(long id, Customer customer) throws RecordNotFoundException;
	void delete(long id) throws RecordNotFoundException;
}
