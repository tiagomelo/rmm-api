package com.ninjaone.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ninjaone.entity.Customer;

/**
 * Repository for handling {@link Customer}.
 * 
 * @author Tiago Melo (tiagoharris@gmail.com)
 *
 */
@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {
	
	/**
	 * Gets all customers ordering by id.
	 * 
	 * @return a list of {@link Customer}
	 */
	public List<Customer> findAllByOrderById();
	
}
