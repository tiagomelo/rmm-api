package com.ninjaone.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ninjaone.entity.DeviceType;

/**
 * Repository for handling {@link DeviceType}.
 * 
 * @author Tiago Melo (tiagoharris@gmail.com)
 *
 */
@Repository
public interface DeviceTypeRepository extends JpaRepository<DeviceType, Integer> {
	
	/**
	 * Get all device types ordered by id.
	 * 
	 * @return a list of {@link DeviceType}
	 */
	public List<DeviceType> findAllByOrderById();

}
