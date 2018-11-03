package com.nab.customer.customermgmt.service;

import java.util.List;

import com.nab.customer.customermgmt.common.InvalidDataException;
import com.nab.customer.customermgmt.common.RecordNotFoundException;
import com.nab.customer.customermgmt.model.Customer;

public interface CustomerService {

	long save(Customer customer) throws InvalidDataException;
	Customer get(long id);
	List<Customer> list();
	void update(long id, Customer customer) throws InvalidDataException, RecordNotFoundException;
	void delete(long id) throws RecordNotFoundException;
}
