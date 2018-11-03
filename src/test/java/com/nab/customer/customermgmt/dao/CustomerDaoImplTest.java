package com.nab.customer.customermgmt.dao;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.nab.customer.customermgmt.common.InvalidDataException;
import com.nab.customer.customermgmt.common.RecordNotFoundException;
import com.nab.customer.customermgmt.config.TestAppConfig;
import com.nab.customer.customermgmt.model.Customer;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=TestAppConfig.class)
public class CustomerDaoImplTest {	
	
	@Autowired
	private CustomerDao customerDao;	
	Customer customer, customer_updated;
	List<Customer> customerList = new ArrayList<Customer>();
	
	@Before
	public void before() {		
		customer = new Customer(new Long(1), "John", "Mid", "Snow", "N", "Mr", "23 coll st", "-", "melbourne",
				"vic", "australia", "3000", "male", "married", 50, true);
		customer_updated = new Customer(new Long(2), "John", "", "Snow", "", "Mr", "23 coll st", "", "melbourne",
				"vic", "australia", "3000", "male", "married", 50, true);
		customerList.add(customer);
	}
	
	@Test
    @Transactional
    @Rollback(true)
	public void test_save_success() {
		assertEquals(customerDao.save(customer), 3);
	}	
	
	@Test( expected = HibernateException.class )
	public void test_create_null_entity() throws InvalidDataException {		
		customerDao.save(null);
	}
	
	@Test
    @Transactional
    @Rollback(true)
	public void test_get_success() {
		customerDao.save(customer);
		assertEquals(customerDao.get(1), customer);
	}
	
	@Test
    @Transactional
    @Rollback(true)
	public void test_get_null_entity() {
		assertEquals(customerDao.get(1), null);
	}
	
	@Test
    @Transactional
    @Rollback(true)
	public void test_list_success() {
		customerDao.save(customer);
		assertEquals(customerDao.list(), customerList);
	}
	
	@Test
    @Transactional
    @Rollback(true)
	public void test_list_null_entity() {		
		assertEquals(customerDao.list(), new ArrayList<Customer>());
	}
	
	@Test
    @Transactional
    @Rollback(true)
	public void test_update_success() throws RecordNotFoundException {
		Long id = customerDao.save(customer);
		customerDao.update(id, customer_updated);
		assertEquals(customerDao.get(id).toString(), customer_updated.toString());
	}	
	
	@Test( expected = RecordNotFoundException.class )
    @Transactional
    @Rollback(true)
	public void test_update_null_entity() throws RecordNotFoundException {
		customerDao.update(1, null);		
	}
}
