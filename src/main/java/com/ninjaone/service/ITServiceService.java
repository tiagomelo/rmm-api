package com.ninjaone.service;

import java.util.List;

import com.ninjaone.entity.ITService;

/**
 * Service interface to manage {@link ITService}.
 * 
 * @author Tiago Melo (tiagoharris@gmail.com)
 *
 */
public interface ITServiceService {
	
	/**
	 * Get IT service by id.
	 * 
	 * @param id
	 * @return {@link ITService}
	 */
	ITService findById(Integer id);
	
	/**
	 * Get all IT services.
	 * 
	 * @return a list of {@link ITService}
	 */
	List<ITService> findAll();
	
	/**
	 * Create IT service.
	 * 
	 * @param newITService
	 * @return {@link ITService}
	 */
	ITService save(ITService newITService);
	
	/**
	 * Delete IT service.
	 * 
	 * @param id
	 */
	void delete(Integer id);
}
