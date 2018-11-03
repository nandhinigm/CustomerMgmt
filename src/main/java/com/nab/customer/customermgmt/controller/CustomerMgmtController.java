package com.nab.customer.customermgmt.controller;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.nab.customer.customermgmt.common.ErrorResponse;
import com.nab.customer.customermgmt.common.InvalidDataException;
import com.nab.customer.customermgmt.common.Messages;
import com.nab.customer.customermgmt.common.RecordNotFoundException;
import com.nab.customer.customermgmt.model.Customer;
import com.nab.customer.customermgmt.service.CustomerService;

@RestController
public class CustomerMgmtController {
	
	@Autowired
	private CustomerService customerService;
	
	private static final Logger logger = Logger.getLogger(CustomerMgmtController.class);

	@PostMapping("/customer")
	public ResponseEntity<?> save(@RequestBody Customer customer) throws InvalidDataException, URISyntaxException {
		
		long id = customerService.save(customer);
		logger.debug("Customer(" + customer.getFirstname() + ") has been added with ID:" + id);
		return ResponseEntity.created(new URI("/customer")).body("Customer has been added with ID:" + id);
	}

	@GetMapping("/customer/{id}")
	public ResponseEntity<Customer> get(@PathVariable("id") long id) throws RecordNotFoundException {
		Customer customer = customerService.get(id);
		if(customer == null)
			throw new RecordNotFoundException(Messages.RECORD_NOT_FOUND_MESSAGE);
		return ResponseEntity.ok().body(customer);
	}

	@GetMapping("/customers")
	public ResponseEntity<List<Customer>> list() throws RecordNotFoundException {

		List<Customer> customers = customerService.list();
		if(customers == null)
			throw new RecordNotFoundException();
		return ResponseEntity.ok().body(customers);
	}

	@PutMapping("/customer/{id}")
	public ResponseEntity<?> update(@PathVariable("id") long id, @RequestBody Customer customer) throws InvalidDataException, RecordNotFoundException {
		customerService.update(id, customer);
		logger.debug("Customer(" + customer.getFirstname() + ") details have been updated");
		return ResponseEntity.ok().body("Customer record has been updated.");
	}

	@DeleteMapping("/customer/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") long id) throws RecordNotFoundException {
		customerService.delete(id);
		logger.debug("Customer(" + id + ") record have been deleted");
		return ResponseEntity.ok().body("Customer record has been deleted");
	}

	@ExceptionHandler(InvalidDataException.class)
	public ResponseEntity<ErrorResponse> exceptionHandler(InvalidDataException ex) {
		ErrorResponse error = new ErrorResponse();
		error.setErrorCode(HttpStatus.BAD_REQUEST.value());
		error.setMessage(ex.getMessage());
		return new ResponseEntity<ErrorResponse>(error, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(RecordNotFoundException.class)
	public ResponseEntity<ErrorResponse> exceptionHandler(RecordNotFoundException ex) {
		ErrorResponse error = new ErrorResponse();
		error.setErrorCode(HttpStatus.NOT_FOUND.value());
		error.setMessage(ex.getMessage());
		return new ResponseEntity<ErrorResponse>(error, HttpStatus.NOT_FOUND);
	}	
}
