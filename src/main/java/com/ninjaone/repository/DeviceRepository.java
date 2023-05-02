package com.ninjaone.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ninjaone.entity.Customer;
import com.ninjaone.entity.Device;

/**
 * Repository for handling {@link Device}.
 * 
 * @author Tiago Melo (tiagoharris@gmail.com)
 *
 */
@Repository
public interface DeviceRepository extends JpaRepository<Device, Integer> {
	
	/**
	 * Find device by id and {@link Customer} id.
	 * 
	 * @param deviceId
	 * @param customerId
	 * @return {@link Device}
	 */
	public Device findByIdAndCustomerId(Integer deviceId, Integer customerId);
	
	/**
	 * Get all devices from {@link Customer}.
	 * 
	 * @param customerId
	 * @return a list of {@link Device}
	 */
	public List<Device> findAllByCustomerId(Integer customerId);
}
