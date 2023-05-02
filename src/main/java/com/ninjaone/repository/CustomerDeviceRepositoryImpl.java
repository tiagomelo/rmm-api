package com.ninjaone.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Service;

import com.ninjaone.entity.Device;
import com.ninjaone.entity.DeviceITService;
import com.ninjaone.entity.ITService;
import com.ninjaone.entity.ITServicePrice;
import com.ninjaone.exception.ResourceNotFoundException;
import com.ninjaone.it.service.ITServiceType;

import jakarta.transaction.Transactional;

/**
 * CustomerDeviceRepositoryImpl implements {@link CustomerDeviceRepository}.
 * 
 * @author Tiago Melo (tiagoharris@gmail.com)
 */
@Service
public class CustomerDeviceRepositoryImpl implements CustomerDeviceRepository {

	@Autowired
	private CacheManager cacheManager;

	@Autowired
	private DeviceRepository deviceRepository;

	@Autowired
	private ITServiceRepository itServiceRepository;

	@Autowired
	private ITServicePriceRepository itServicePriceRepository;

	@Autowired
	private CustomerInvoiceRepository customerInvoiceRepository;

	@Autowired
	private DeviceITServiceRepository deviceITServiceRepository;

	public void evictCacheEntry(Integer customerId) {
		Cache cache = cacheManager.getCache("rmmCache");
		cache.evict("invoice:" + customerId);
	}

	@Override
	@Transactional
	public Device addDevice(Device device) {
		ITServicePrice deviceOwnershipPrice = itServicePriceRepository
				.findByServiceId(ITServiceType.DEVICE_OWNERSHIP.getTypeId());
		if (deviceOwnershipPrice != null) {
			ITService deviceOwnershipService = deviceOwnershipPrice.getItService();
			Integer customerId = device.getCustomer().getId();
			BigDecimal price = deviceOwnershipPrice.getPrice();
			customerInvoiceRepository.updateCustomerInvoiceTotalValue(customerId, price);
			deviceRepository.save(device);
			DeviceITService deviceITService = new DeviceITService();
			deviceITService.setDevice(device);
			deviceITService.setItService(deviceOwnershipService);
			deviceITServiceRepository.save(deviceITService);
			evictCacheEntry(customerId);
			return device;
		}
		return null;
	}

	@Override
	@Transactional
	public void deleteDevice(Integer customerId, Integer deviceId) {
		Device device = deviceRepository.findByIdAndCustomerId(deviceId, customerId);
		if (device == null) {
			throw new ResourceNotFoundException(Device.class.getSimpleName(), "id", deviceId);
		}
		Integer deviceOwnershipServiceId = ITServiceType.DEVICE_OWNERSHIP.getTypeId();
		ITServicePrice deviceOwnershipPrice = itServicePriceRepository.findByServiceId(deviceOwnershipServiceId);
		BigDecimal price = deviceOwnershipPrice.getPrice();

		customerInvoiceRepository.updateCustomerInvoiceTotalValue(customerId, price.negate());
		deviceITServiceRepository.deleteByDeviceIdAndServiceId(device.getId(), deviceOwnershipServiceId);
		deviceRepository.delete(device);

		evictCacheEntry(customerId);
	}

	@Override
	@Transactional
	public void addServiceToDevice(Integer customerId, Integer deviceId, Integer serviceId) {
		Device device = deviceRepository.findByIdAndCustomerId(deviceId, customerId);
		if (device == null) {
			throw new ResourceNotFoundException(Device.class.getSimpleName(), "id", deviceId);
		}
		Integer platformId = device.getDeviceType().getPlatform().getId();
		ITService itService = itServiceRepository.findById(serviceId).orElse(null);
		if (itService == null) {
			throw new ResourceNotFoundException(ITService.class.getSimpleName(), "serviceId", serviceId);
		}
		ITServicePrice servicePrice = itServicePriceRepository.findFirstByServiceIdAndPlatformId(serviceId, platformId)
				.orElse(null);
		if (servicePrice == null) {
			throw new ResourceNotFoundException(ITServicePrice.class.getSimpleName(), "serviceId", "platformId",  serviceId, platformId);
		}
		BigDecimal price = servicePrice.getPrice();
		customerInvoiceRepository.updateCustomerInvoiceTotalValue(customerId, price);
		DeviceITService deviceITService = new DeviceITService();
		deviceITService.setDevice(device);
		deviceITService.setItService(itService);
		deviceITServiceRepository.save(deviceITService);

		evictCacheEntry(customerId);
	}

	@Override
	@Transactional
	public void deleteServiceFromDevice(Integer customerId, Integer deviceId, Integer serviceId) {
		Device device = deviceRepository.findByIdAndCustomerId(deviceId, customerId);
		if (device == null) {
			throw new ResourceNotFoundException(Device.class.getSimpleName(), "id", deviceId);
		}
		Integer platformId = device.getDeviceType().getPlatform().getId();
		ITService itService = itServiceRepository.findById(serviceId).orElse(null);
		if (itService == null) {
			throw new ResourceNotFoundException(ITService.class.getSimpleName(), "serviceId", serviceId);
		}
		ITServicePrice servicePrice = itServicePriceRepository.findFirstByServiceIdAndPlatformId(serviceId, platformId)
				.orElse(null);
		if (servicePrice == null) {
			throw new ResourceNotFoundException(ITServicePrice.class.getSimpleName(), "serviceId", "platformId",  serviceId, platformId);
		}
		BigDecimal price = servicePrice.getPrice();
		customerInvoiceRepository.updateCustomerInvoiceTotalValue(customerId, price.negate());
		deviceITServiceRepository.deleteByDeviceIdAndServiceId(device.getId(), itService.getId());

		evictCacheEntry(customerId);
	}

	@Override
	public List<Device> getDevices(Integer customerId) {
		return deviceRepository.findAllByCustomerId(customerId);
	}

}
