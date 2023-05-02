package com.ninjaone.service;

import com.ninjaone.entity.Customer;
import com.ninjaone.entity.CustomerInvoice;

/**
 * Service interface to manage {@link CustomerInvoice}.
 * 
 * @author Tiago Melo (tiagoharris@gmail.com)
 *
 */
public interface CustomerInvoiceService {
	
	/**
	 * Get invoice by {@link Customer} id.
	 * 
	 * @param customerId
	 * @return {@link CustomerInvoice}
	 */
	CustomerInvoice findByCustomerId(Integer customerId);
	
	/**
	 * Create invoice.
	 * 
	 * @param newCustomerInvoice
	 * @return {@link CustomerInvoice}
	 */
	CustomerInvoice save(CustomerInvoice newCustomerInvoice);
	
	/**
	 * Delete invoice.
	 * 
	 * @param newCustomerInvoice
	 */
	void delete(Integer id);
}
