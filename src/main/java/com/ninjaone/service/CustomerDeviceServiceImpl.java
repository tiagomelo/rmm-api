package com.ninjaone.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ninjaone.entity.Device;
import com.ninjaone.repository.CustomerDeviceRepository;

/**
 * CustomerDeviceServiceImpl implements {@link CustomerDeviceService}.
 * 
 * @author Tiago Melo (tiagoharris@gmail.com)
 *
 */
@Service
public class CustomerDeviceServiceImpl implements CustomerDeviceService {

	@Autowired
	CustomerDeviceRepository repository;
	
	@Override
	public Device addDevice(Device device) {
		return repository.addDevice(device);
	}

	@Override
	public void deleteDevice(Integer customerId, Integer deviceId) {
		repository.deleteDevice(customerId, deviceId);
	}

	@Override
	public void addServiceToDevice(Integer customerId, Integer deviceId, Integer serviceId) {
		repository.addServiceToDevice(customerId, deviceId, serviceId);
	}

	@Override
	public void deleteServiceFromDevice(Integer customerId, Integer deviceId, Integer serviceId) {
		repository.deleteServiceFromDevice(customerId, deviceId, serviceId);
	}

	@Override
	public List<Device> getDevices(Integer customerId) {
		return repository.getDevices(customerId);
	}

}
