package com.nab.customer.customermgmt.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.nab.customer.customermgmt.common.Messages;
import com.nab.customer.customermgmt.common.RecordNotFoundException;
import com.nab.customer.customermgmt.model.Customer;

@Component
public class CustomerDaoImpl implements CustomerDao {

	@Autowired
	private SessionFactory sessionFactory;
	private static final Logger logger = Logger.getLogger(CustomerDaoImpl.class);

	public long save(Customer customer) {

		getSession().save(customer);
		logger.debug("Customer record with details " + customer.toString() + " has been created");
		return customer.getId();
	}

	public Customer get(long id) {
		return getSession().get(Customer.class, id);
	}

	public List<Customer> list() {
		return getSession().createCriteria(Customer.class).list();
	}

	public void update(long id, Customer customer) throws RecordNotFoundException {
		Session session = getSession();
		Customer cust = session.byId(Customer.class).load(id);
		if(cust != null) {
			if(customer.getCity() != null)
				cust.setCity(customer.getCity());
			if(customer.getAddress() != null)
				cust.setAddress(customer.getAddress());
			if(customer.getCountry() != null)
				cust.setCountry(customer.getCountry());
			if(customer.getCreditrating() >= 0)
				cust.setCreditrating(customer.getCreditrating());
			if(customer.getCity() != null)
				cust.setFirstname(customer.getFirstname());
			if(customer.getCity() != null)
				cust.setInitials(customer.getInitials());		
			if(customer.getCity() != null)
				cust.setLastname(customer.getLastname());
			if(customer.getCity() != null)
				cust.setMaritalstatus(customer.getMaritalstatus());
			if(customer.getCity() != null)
				cust.setMiddlename(customer.getMiddlename());
			if(customer.getCity() != null)
				cust.setSex(customer.getSex());
			if(customer.getCity() != null)
				cust.setState(customer.getState());
			if(customer.getCity() != null)
				cust.setSuburb(customer.getSuburb());
			if(customer.getCity() != null)
				cust.setTitle(customer.getTitle());
			if(customer.getCity() != null)
				cust.setZipcode(customer.getZipcode());
			logger.debug("Customer record with details " + cust.toString() + " has been updated");
		} else {
			throw new RecordNotFoundException(Messages.RECORD_NOT_FOUND_MESSAGE);
		}
		session.flush();

	}

	public void delete(long id) throws RecordNotFoundException {
		Session session = getSession();
		Customer customer = session.byId(Customer.class).load(id);		
		if(customer != null) {
			session.delete(customer);
			logger.debug("Customer record with id " + id + " has been deleted");
		} else {
			throw new RecordNotFoundException(Messages.RECORD_NOT_FOUND_MESSAGE);
		}
	}	

	public Session getSession() {
		return sessionFactory.getCurrentSession();		
	}
}
