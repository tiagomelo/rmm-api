package com.ninjaone.service;

import java.util.List;

import com.ninjaone.entity.Device;

/**
 * Service interface to manage {@link Device}.
 * 
 * @author Tiago Melo (tiagoharris@gmail.com)
 *
 */
public interface DeviceService {
	
	/**
	 * Find device by id.
	 * 
	 * @param id
	 * @return {@link Device}
	 */
	Device findById(Integer id);
	
	/**
	 * Get all devices.
	 * 
	 * @return a list of {@link Device}
	 */
	List<Device> findAll();
	
	/**
	 * Create device.
	 * 
	 * @param newDevice
	 * @return {@link Device}
	 */
	Device save(Device newDevice);
	
	/**
	 * Delete device.
	 * 
	 * @param id
	 */
	void delete(Integer id);
}
