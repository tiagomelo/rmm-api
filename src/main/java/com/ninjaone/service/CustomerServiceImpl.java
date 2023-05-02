package com.ninjaone.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ninjaone.entity.Customer;
import com.ninjaone.exception.ResourceNotFoundException;
import com.ninjaone.repository.CustomerRepository;

/**
 * CustomerServiceImpl implements {@link CustomerService}.
 * 
 * @author Tiago Melo (tiagoharris@gmail.com)
 *
 */
@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	CustomerRepository repository;
	
	@Override
	public Customer findById(Integer id) {
		Customer platform = repository.findById(id).orElse(null);
	    
	    if (platform == null) {
	      throw new ResourceNotFoundException(Customer.class.getSimpleName(), "id", id);
	    }
	    
	    return platform;
	}

	@Override
	public List<Customer> findAll() {
		return repository.findAllByOrderById();
	}

	@Override
	public Customer save(Customer newCustomer) {
		return repository.save(newCustomer);
	}

	@Override
	public void delete(Integer id) {
		Customer platform = findById(id);
	    
	    repository.delete(platform);
	}

}
