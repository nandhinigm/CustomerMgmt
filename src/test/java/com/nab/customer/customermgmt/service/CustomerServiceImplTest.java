package com.nab.customer.customermgmt.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.nab.customer.customermgmt.common.InvalidDataException;
import com.nab.customer.customermgmt.common.RecordNotFoundException;
import com.nab.customer.customermgmt.config.TestAppConfig;
import com.nab.customer.customermgmt.dao.CustomerDao;
import com.nab.customer.customermgmt.model.Customer;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=TestAppConfig.class)
public class CustomerServiceImplTest {

	@Autowired
	private CustomerDao customerDao;
	
	@Autowired
	private SessionFactory sessionFactory;
	
	//SessionFactoryImplementor implementor;
	
    @Mock
    Session session;
	
	private CustomerService service;
	Customer customer;
	Customer invalid_customer;

	@Before
	public void before() {

		service = new CustomerServiceImpl();
		
		/*sessionFactory = mock(SessionFactory.class, Mockito.withSettings().extraInterfaces(SessionFactoryImplementor.class));
		implementor = (SessionFactoryImplementor) sessionFactory;        
        when(implementor.getSqlFunctionRegistry()).thenReturn(new SQLFunctionRegistry(new MySQL5Dialect(), new HashMap<String, SQLFunction>()));*/	    
	    
		customer = new Customer(new Long(1), "John", "Mid", "Snow", "N", "Mr", "23 coll st", "-", "melbourne",
				"vic", "australia", "3000", "male", "married", 50, true);
		invalid_customer = new Customer(new Long(1), null, "Mid", "Snow", "N", "Mr", "23 coll st", "-", "melbourne",
				"vic", "australia", "3000", "male", "married", 50, true);
	}		

	@Test( expected = NullPointerException.class )
	public void whenCreateIsTriggeredForNullEntity_thenException() throws InvalidDataException {		
		this.service.save(null);
	}	

	@Test( expected = InvalidDataException.class )
	public void whenCreateIsTriggeredForInvalidEntity_thenException() throws InvalidDataException {	
		this.service.save(invalid_customer);		
	}
	
	@Test( expected = NullPointerException.class )
	public void whenUpdateIsTriggeredForNullEntity_thenException() throws InvalidDataException, RecordNotFoundException {		
		this.service.update(1, null);
	}	

	@Test( expected = InvalidDataException.class )
	public void whenUpdateIsTriggeredForInvalidEntity_thenException() throws InvalidDataException, RecordNotFoundException {	
		this.service.update(1, invalid_customer);		
	}
	
	/*
	@Test
	public void whenCreateIsTriggered_thenNoException() throws InvalidDataException {
		when(sessionFactory.getCurrentSession()).thenReturn(session);
		when(customerDao.save(any(Customer.class))).thenReturn(customer.getId());
		this.service.save(customer);
	}
	
	@Test
	public void test_read_success() {
		when(customerDao.get(1)).thenReturn(customer);
		this.service.get(1);
	}
	
	@Test
	public void test_list_success() {
		when(customerDao.get(1)).thenReturn(customer);
		this.service.get(1);
	} 
	
	@Test
	public void test_delete_success() {
		when(customerDao.get(1)).thenReturn(customer);
		this.service.get(1);
	} 	
	
	@Test
	public void test_read_success() throws InvalidDataException {
		when(customerDao.save(customer)).thenReturn(new Long(1));
		when(customerDao.save(customer)).thenReturn(new Long(1));
		when(customerDao.get(1)).thenReturn(customer);
		Long id = this.service.save(customer);
		assertEquals(this.service.get(id), customer);
	} 
	*/
}
