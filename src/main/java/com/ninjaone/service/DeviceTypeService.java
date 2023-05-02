package com.ninjaone.service;

import java.util.List;

import com.ninjaone.entity.DeviceType;

/**
 * Service interface to manage {@link DeviceType}.
 * 
 * @author Tiago Melo (tiagoharris@gmail.com)
 *
 */
public interface DeviceTypeService {
	
	/**
	 * Find device type by id.
	 * 
	 * @param id
	 * @return {@link DeviceType}
	 */
	DeviceType findById(Integer id);
	
	/**
	 * Get device types.
	 * 
	 * @return a list of {@link DeviceType}
	 */
	List<DeviceType> findAll();
	
	/**
	 * Create device type.
	 * 
	 * @param newDeviceType
	 * @return {@link DeviceType}
	 */
	DeviceType save(DeviceType newDeviceType);
	
	/**
	 * Delete device type.
	 * 
	 * @param id
	 */
	void delete(Integer id);
}
