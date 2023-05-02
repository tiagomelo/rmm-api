package com.ninjaone.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ninjaone.dto.customer.CustomerDTO;
import com.ninjaone.dto.customer.CustomerDeviceDTO;
import com.ninjaone.dto.customer.CustomerInvoiceDTO;
import com.ninjaone.dto.customer.NewCustomerDTO;
import com.ninjaone.dto.customer.UpdateCustomerDTO;
import com.ninjaone.entity.Customer;
import com.ninjaone.entity.CustomerInvoice;
import com.ninjaone.entity.Device;
import com.ninjaone.service.CustomerDeviceService;
import com.ninjaone.service.CustomerInvoiceService;
import com.ninjaone.service.CustomerService;

import io.micrometer.common.util.StringUtils;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

/**
 * Restful controller responsible for managing customers.
 * 
 * @author Tiago Melo (tiagoharris@gmail.com)
 *
 */
@RestController
@RequestMapping("/api")
@Tag(name = "customer")
public class CustomerController {

	@Autowired
	ModelMapper modelMapper;

	@Autowired
	CustomerService service;

	@Autowired
	CustomerDeviceService deviceService;
	
	@Autowired
	CustomerInvoiceService customerInvoiceService;

	private static final Logger LOGGER = LoggerFactory.getLogger(CustomerController.class);

	/**
	 * Get a customer by its id.
	 * 
	 * @param customerId
	 * @return {@link CustomerDTO}
	 */
	@GetMapping("/customer/{id}")
	public CustomerDTO getCustomerById(@PathVariable(value = "id", required = true) Integer customerId) {
		LOGGER.info(String.format("getCustomerById() called with id [%s]", customerId));

		return convertToDTO(service.findById(customerId));
	}

	/**
	 * Get all customers.
	 * 
	 * @return list of {@link CustomerDTO}.
	 */
	@GetMapping("/customers")
	public List<CustomerDTO> getAllCustomers() {
		List<Customer> customers = service.findAll();

		LOGGER.info(String.format("getAllCustomers() returned %s records", customers.size()));

		return customers.stream().map(customer -> convertToDTO(customer)).collect(Collectors.toList());
	}

	/**
	 * Get customer's invoice.
	 * 
	 * @param customerId
	 * @return {@link CustomerInvoiceDTO}
	 */
	@GetMapping("/customer/{id}/invoice")
	public CustomerInvoiceDTO getCustomerInvoice(@PathVariable(value = "id", required = true) Integer customerId) {
		LOGGER.info(String.format("getCustomerById() called with id [%s]", customerId));

		return convertCustomerInvoiceToDTO(customerInvoiceService.findByCustomerId(customerId));
	}
	
	/**
	 * Creates a customer.
	 * 
	 * @param customerDTO
	 * @return {@link CustomerDTO}
	 */
	@PostMapping("/customer")
	public CustomerDTO createCustomer(@Valid @RequestBody NewCustomerDTO customerDTO) {
		Customer customer = convertToEntity(customerDTO);

		LOGGER.info("createCustomer() called");

		return convertToDTO(service.save(customer));
	}

	/**
	 * Updates a customer.
	 * 
	 * @param customerId
	 * @param customerDTO
	 * @return {@link CustomerDTO} 
	 */
	@PutMapping("/customer/{id}")
	public CustomerDTO updateCustomer(@PathVariable(value = "id", required = true) Integer customerId,
			@Valid @RequestBody UpdateCustomerDTO customerDTO) {
		customerDTO.setId(customerId);
		Customer customer = convertToEntity(customerDTO);

		LOGGER.info(String.format("updateCustomer() called with id [%s]", customerId));

		return convertToDTO(service.save(customer));
	}
	
	/**
	 * Get all devices from the customer.
	 * 
	 * @param customerId
	 * @return list of {@link CustomerDeviceDTO}
	 */
	@GetMapping("/customer/{id}/devices")
	public List<CustomerDeviceDTO> getDevices(@PathVariable(value = "id", required = true) Integer customerId) {
		LOGGER.info(String.format("getDevices() called with customer id [%s]", customerId));

		List<Device> devices = deviceService.getDevices(customerId);
		
		return devices.stream().map(device -> convertToDeviceDTO(device)).collect(Collectors.toList());
	}

	/**
	 * Add device to customer.
	 * 
	 * @param customerId
	 * @param customerDeviceDTO
	 * @return {@link CustomerDeviceDTO}
	 */
	@PostMapping("/customer/{id}/device")
	public CustomerDeviceDTO addDevice(@PathVariable(value = "id", required = true) Integer customerId,
			@Valid @RequestBody CustomerDeviceDTO customerDeviceDTO) {
		customerDeviceDTO.setCustomerId(customerId);
		Device device = convertToDeviceEntity(customerDeviceDTO);

		LOGGER.info(String.format("addDevice() called with id [%s]", customerId));

		return convertToDeviceDTO(deviceService.addDevice(device));
	}
	
	/**
	 * Add a service to a device from customer.
	 * 
	 * @param customerId
	 * @param deviceId
	 * @param serviceId
	 * @return
	 */
	@PostMapping("/customer/{id}/device/{deviceId}/service/{serviceId}")
	public ResponseEntity<?> addServiceToDevice(@PathVariable(value = "id") Integer customerId, 
			@PathVariable(value = "deviceId") Integer deviceId,
			@PathVariable(value = "serviceId") Integer serviceId) {
		
		deviceService.addServiceToDevice(customerId, deviceId, serviceId);
		
		LOGGER.info(String.format("addServiceToDevice() called with customer id [%s], device id [%s] and service id [%s]", customerId, deviceId, serviceId));
		
		return ResponseEntity.ok().build();
	}

	/**
	 * Delete a service from a device from customer.
	 * 
	 * @param customerId
	 * @param deviceId
	 * @param serviceId
	 * @return
	 */
	@DeleteMapping("/customer/{id}/device/{deviceId}/service/{serviceId}")
	public ResponseEntity<?> deleteServiceFromDevice(@PathVariable(value = "id") Integer customerId, 
			@PathVariable(value = "deviceId") Integer deviceId,
			@PathVariable(value = "serviceId") Integer serviceId) {
		
		deviceService.deleteServiceFromDevice(customerId, deviceId, serviceId);
		
		LOGGER.info(String.format("deleteServiceFromDevice() called with customer id [%s], device id [%s] and service id [%s]", customerId, deviceId, serviceId));
		
		return ResponseEntity.ok().build();
	}
	
	/**
	 * Delete a device from customer.
	 * 
	 * @param customerId
	 * @param deviceId
	 * @return
	 */
	@DeleteMapping("/customer/{id}/device/{deviceId}")
	public ResponseEntity<?> deleteDevice(@PathVariable(value = "id") Integer customerId, @PathVariable(value = "deviceId") Integer deviceId) {
		deviceService.deleteDevice(customerId, deviceId);

		LOGGER.info(String.format("deleteDevice() called with customer id [%s] and device id [%s]", customerId, deviceId));

		return ResponseEntity.ok().build();
	}
	
	/**
	 * Delete a customer.
	 * 
	 * @param customerId
	 * @return
	 */
	@DeleteMapping("/customer/{id}")
	public ResponseEntity<?> deleteCustomer(@PathVariable(value = "id") Integer customerId) {
		service.delete(customerId);

		LOGGER.info(String.format("deleteCustomer() called with id [%s]", customerId));

		return ResponseEntity.ok().build();
	}

	private CustomerDTO convertToDTO(Customer customer) {
		return modelMapper.map(customer, NewCustomerDTO.class);
	}

	private CustomerDeviceDTO convertToDeviceDTO(Device device) {
		return modelMapper.map(device, CustomerDeviceDTO.class);
	}
	
	private CustomerInvoiceDTO convertCustomerInvoiceToDTO(CustomerInvoice customerInvoice) {
		return modelMapper.map(customerInvoice, CustomerInvoiceDTO.class);
	}

	private Device convertToDeviceEntity(CustomerDeviceDTO customerDeviceDTO) {
		return modelMapper.map(customerDeviceDTO, Device.class);
	}

	private Customer convertToEntity(CustomerDTO customerDTO) {
		Customer customer = null;
		String name = "";
		String email = "";

		if (customerDTO.getId() != null) {
			customer = service.findById(customerDTO.getId());
			name = customer.getName();
			email = customer.getEmail();
		}

		customer = modelMapper.map(customerDTO, Customer.class);
		customer.setName(name);
		customer.setEmail(email);

		if (StringUtils.isNotEmpty(customerDTO.getName())) {
			customer.setName(customerDTO.getName());
		}

		if (StringUtils.isNotEmpty(customerDTO.getEmail())) {
			customer.setEmail(customerDTO.getEmail());
		}

		return customer;
	}
}
