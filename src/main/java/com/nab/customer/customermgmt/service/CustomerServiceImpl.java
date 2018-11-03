package com.nab.customer.customermgmt.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nab.customer.customermgmt.common.Messages;
import com.nab.customer.customermgmt.common.RecordNotFoundException;
import com.nab.customer.customermgmt.common.InvalidDataException;
import com.nab.customer.customermgmt.dao.CustomerDao;
import com.nab.customer.customermgmt.model.Customer;
import com.nab.customer.customermgmt.util.CustomerUtil;

@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {
	
	@Autowired
	private CustomerDao customerDao;
	private static final Logger logger = Logger.getLogger(CustomerServiceImpl.class);

	public long save(Customer customer) throws InvalidDataException {
		logger.debug("Saving customer details....");
		if(CustomerUtil.validateName(customer.getFirstname()) && 
				(customer.getMiddlename() == null || CustomerUtil.validateName(customer.getMiddlename())) &&
				 CustomerUtil.validateName(customer.getLastname())) {
			return customerDao.save(customer);			
		} else {
			logger.error("Invalid customer data: " + customer.toString());
			throw new InvalidDataException(Messages.INVALID_DATA_ERROR_MESSAGE);
		}
	}

	public Customer get(long id) {
		logger.debug("Retrieving customer details....");
		return customerDao.get(id);
	}

	public List<Customer> list() {
		logger.debug("Retrieving customer details....");
		return customerDao.list();
	}
	
	@Transactional
	public void update(long id, Customer customer) throws InvalidDataException, RecordNotFoundException {
		logger.debug("Updating customer details....");
		if(CustomerUtil.validateName(customer.getFirstname()) && 
				(customer.getMiddlename() == null || CustomerUtil.validateName(customer.getMiddlename())) &&
				CustomerUtil.validateName(customer.getLastname())) {
			customerDao.update(id, customer);
		} else {
			logger.error("Invalid customer data: " + customer.toString());
			throw new InvalidDataException(Messages.INVALID_DATA_ERROR_MESSAGE);
		}
	}
	
	@Transactional
	public void delete(long id) throws RecordNotFoundException {
		logger.debug("Deleting customer details....");
		customerDao.delete(id);		
	}

}
