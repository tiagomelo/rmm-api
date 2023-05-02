package com.ninjaone.service;

import java.util.List;

import com.ninjaone.entity.Customer;
import com.ninjaone.entity.Device;

/**
 * Service interface to manage {@link Customer} {@link Device}s.
 * 
 * @author Tiago Melo (tiagoharris@gmail.com)
 *
 */
public interface CustomerDeviceService {

	/**
	 * Add a device to customer.
	 * 
	 * @param device
	 * @return {@link Device}
	 */
	public Device addDevice(Device device);
	
	/**
	 * Delete a device from customer.
	 * 
	 * @param customerId
	 * @param deviceId
	 */
	public void deleteDevice(Integer customerId, Integer deviceId);
	
	/**
	 * Add a service to device.
	 * 
	 * @param customerId
	 * @param deviceId
	 * @param serviceId
	 */
	public void addServiceToDevice(Integer customerId, Integer deviceId, Integer serviceId);

	/**
	 * Delete service from device.
	 * 
	 * @param customerId
	 * @param deviceId
	 * @param serviceId
	 */
	public void deleteServiceFromDevice(Integer customerId, Integer deviceId, Integer serviceId);

	/**
	 * Get devices.
	 * 
	 * @param customerId
	 * @return a list of {@link Device}
	 */
	public List<Device> getDevices(Integer customerId);
}
