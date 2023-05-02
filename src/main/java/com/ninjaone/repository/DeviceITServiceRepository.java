package com.ninjaone.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ninjaone.entity.Device;
import com.ninjaone.entity.DeviceITService;
import com.ninjaone.entity.ITService;

/**
 * Repository for handling {@link ITService}.
 * 
 * @author Tiago Melo (tiagoharris@gmail.com)
 *
 */
@Repository
public interface DeviceITServiceRepository extends JpaRepository<DeviceITService, Integer> {
	
	/**
	 * Deletes IT service by {@link Device} id and {@link ITService} id.
	 * 
	 * @param deviceId
	 * @param serviceId
	 */
	public void deleteByDeviceIdAndServiceId(Integer deviceId, Integer serviceId);
}
