package com.ninjaone.service;

import java.util.List;

import com.ninjaone.entity.Platform;

/**
 * Service interface to manage {@link Platform}.
 * 
 * @author Tiago Melo (tiagoharris@gmail.com)
 *
 */
public interface PlatformService {
	
	/**
	 * Get platform by id.
	 * 
	 * @param id
	 * @return {@link Platform}
	 */
	Platform findById(Integer id);
	
	/**
	 * Get all platforms.
	 * 
	 * @return a list of {@link Platform}
	 */
	List<Platform> findAll();
	
	/**
	 * Create platform
	 * 
	 * @param newPlatform
	 * @return {@link Platform}
	 */
	Platform save(Platform newPlatform);
	
	/**
	 * Delete platform.
	 * 
	 * @param id
	 */
	void delete(Integer id);
}
