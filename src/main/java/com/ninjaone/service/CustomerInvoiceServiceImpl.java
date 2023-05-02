package com.ninjaone.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.ninjaone.entity.CustomerInvoice;
import com.ninjaone.exception.ResourceNotFoundException;
import com.ninjaone.repository.CustomerInvoiceRepository;

/**
 * CustomerInvoiceServiceImpl implements {@link CustomerInvoiceService}.
 * 
 * @author Tiago Melo (tiagoharris@gmail.com)
 *
 */
@Service
public class CustomerInvoiceServiceImpl implements CustomerInvoiceService {

	@Autowired
	CustomerInvoiceRepository repository;
	
	@Override
	@Cacheable(value = "rmmCache", key = "'invoice:' + #customerId")
	public CustomerInvoice findByCustomerId(Integer customerId) {
		CustomerInvoice customerInvoice = repository.findByCustomerId(customerId);
	    
	    if (customerInvoice == null) {
	      throw new ResourceNotFoundException(CustomerInvoice.class.getSimpleName(), "id", customerId);
	    }
	    
	    return customerInvoice;
	}

	@Override
	public CustomerInvoice save(CustomerInvoice newCustomerInvoice) {
		return repository.save(newCustomerInvoice);
	}

	@Override
	public void delete(Integer customerId) {
		CustomerInvoice customerInvoice = findByCustomerId(customerId);
	    
	    repository.delete(customerInvoice);
	}
	

}
