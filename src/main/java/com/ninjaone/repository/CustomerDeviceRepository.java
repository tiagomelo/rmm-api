package com.ninjaone.repository;

import java.util.List;

import com.ninjaone.entity.Customer;
import com.ninjaone.entity.Device;
import com.ninjaone.entity.ITService;

/**
 * Repository for handling {@link Customer} devices.
 * 
 * @author Tiago Melo (tiagoharris@gmail.com)
 *
 */
public interface CustomerDeviceRepository {

	/**
	 * Adds a device.
	 * 
	 * @param device
	 * @return {@link Device}
	 */
	public Device addDevice(Device device);
	
	/**
	 * Deletes a device.
	 * 
	 * @param customerId
	 * @param deviceId
	 */
	public void deleteDevice(Integer customerId, Integer deviceId);

	/**
	 * Adds a {@link ITService} to a device.
	 * 
	 * @param customerId
	 * @param deviceId
	 * @param serviceId
	 */
	public void addServiceToDevice(Integer customerId, Integer deviceId, Integer serviceId);

	/**
	 * Deletes a {@link ITService} from a device.
	 * 
	 * @param customerId
	 * @param deviceId
	 * @param serviceId
	 */
	public void deleteServiceFromDevice(Integer customerId, Integer deviceId, Integer serviceId);

	/**
	 * Get all devices.
	 * 
	 * @param customerId
	 * @return a list of {@link Device}
	 */
	public List<Device> getDevices(Integer customerId);
}
