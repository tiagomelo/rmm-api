package com.ninjaone.service;

import java.util.List;

import com.ninjaone.entity.Customer;

/**
 * Service interface to manage {@link Customer}.
 * 
 * @author Tiago Melo (tiagoharris@gmail.com)
 *
 */
public interface CustomerService {
	
	/**
	 * Find customer by id.
	 * 
	 * @param id
	 * @return {@link Customer}
	 */
	Customer findById(Integer id);
	
	/**
	 * Get all customers.
	 * 
	 * @return a list of {@link Customer}
	 */
	List<Customer> findAll();
	
	/**
	 * Creates a customer.
	 * 
	 * @param newCustomer
	 * @return {@link Customer}
	 */
	Customer save(Customer newCustomer);
	
	/**
	 * Deletes a customer.
	 * 
	 * @param id
	 */
	void delete(Integer id);
}
