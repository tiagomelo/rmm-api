package com.ninjaone.service;

import java.util.List;

import com.ninjaone.entity.ITServicePrice;

/**
 * Service interface to manage {@link ITServicePrice}.
 * 
 * @author Tiago Melo (tiagoharris@gmail.com)
 *
 */
public interface ITServicePriceService {
	
	/**
	 * Find IT service price by id.
	 * 
	 * @param id
	 * @return {@link ITServicePrice}
	 */
	ITServicePrice findById(Integer id);
	
	/**
	 * Get all IT service prices.
	 * 
	 * @return
	 */
	List<ITServicePrice> findAll();
	
	/**
	 * Create IT service price.
	 * 
	 * @param newITServicePrice
	 * @return {@link ITServicePrice} 
	 */
	ITServicePrice save(ITServicePrice newITServicePrice);
	
	/**
	 * Delete IT service price.
	 * 
	 * @param id
	 */
	void delete(Integer id);
}
