package com.ninjaone.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ninjaone.entity.DeviceType;
import com.ninjaone.exception.ResourceNotFoundException;
import com.ninjaone.repository.DeviceTypeRepository;

/**
 * DeviceTypeServiceImpl implements {@link DeviceTypeService}.
 * 
 * @author Tiago Melo (tiagoharris@gmail.com)
 *
 */
@Service
public class DeviceTypeServiceImpl implements DeviceTypeService {

	@Autowired
	DeviceTypeRepository repository;
	
	@Override
	public DeviceType findById(Integer id) {
		DeviceType deviceType = repository.findById(id).orElse(null);
	    
	    if (deviceType == null) {
	      throw new ResourceNotFoundException(DeviceType.class.getSimpleName(), "id", id);
	    }
	    
	    return deviceType;
	}

	@Override
	public List<DeviceType> findAll() {
		return repository.findAllByOrderById();
	}

	@Override
	public DeviceType save(DeviceType newDeviceType) {
		return repository.save(newDeviceType);
	}

	@Override
	public void delete(Integer id) {
		DeviceType deviceType = findById(id);
	    
	    repository.delete(deviceType);
	}

}
