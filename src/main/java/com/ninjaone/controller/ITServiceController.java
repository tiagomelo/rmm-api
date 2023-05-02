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

import com.ninjaone.dto.itservice.ITServiceDTO;
import com.ninjaone.entity.ITService;
import com.ninjaone.service.ITServiceService;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

/**
 * Restful controller responsible for mananing IT services.
 * 
 * @author Tiago Melo (tiagoharris@gmail.com)
 *
 */
@RestController
@RequestMapping("/api")
@Tag(name = "service")
public class ITServiceController {

	@Autowired
	ModelMapper modelMapper;

	@Autowired
	ITServiceService service;

	private static final Logger LOGGER = LoggerFactory.getLogger(ITServiceController.class);

	/**
	 * Get IT service by id.
	 * 
	 * @param serviceId
	 * @return {@link ITServiceDTO}
	 */
	@GetMapping("/service/{id}")
	public ITServiceDTO getServiceById(@PathVariable(value = "id", required = true) Integer serviceId) {
		LOGGER.info(String.format("getServiceById() called with id [%s]", serviceId));

		return convertToDTO(service.findById(serviceId));
	}

	/**
	 * Get all IT services.
	 * 
	 * @return list of {@link ITServiceDTO}
	 */
	@GetMapping("/services")
	public List<ITServiceDTO> getAllServices() {
		List<ITService> services = service.findAll();

		LOGGER.info(String.format("getAllServices() returned %s records", services.size()));

		return services.stream().map(service -> convertToDTO(service)).collect(Collectors.toList());
	}

	/**
	 * Create IT service.
	 * 
	 * @param serviceDTO
	 * @return {@link ITServiceDTO}
	 */
	@PostMapping("/service")
	public ITServiceDTO createService(@Valid @RequestBody ITServiceDTO serviceDTO) {
		ITService itService = convertToEntity(serviceDTO);

		LOGGER.info("createService() called");

		return convertToDTO(service.save(itService));
	}

	/**
	 * Update IT service.
	 * 
	 * @param serviceId
	 * @param serviceDTO
	 * @return {@link ITServiceDTO}
	 */
	@PutMapping("/service/{id}")
	public ITServiceDTO updateService(@PathVariable(value = "id", required = true) Integer serviceId,
			@Valid @RequestBody ITServiceDTO serviceDTO) {
		serviceDTO.setId(serviceId);
		ITService itService = convertToEntity(serviceDTO);

		LOGGER.info(String.format("updateService() called with id [%s]", serviceId));

		return convertToDTO(service.save(itService));
	}

	/**
	 * Delete IT service.
	 * 
	 * @param serviceId
	 * @return
	 */
	@DeleteMapping("/service/{id}")
	public ResponseEntity<?> deleteService(@PathVariable(value = "id") Integer serviceId) {
		service.delete(serviceId);

		LOGGER.info(String.format("deleteService() called with id [%s]", serviceId));

		return ResponseEntity.ok().build();
	}

	private ITServiceDTO convertToDTO(ITService service) {
		return modelMapper.map(service, ITServiceDTO.class);
	}

	private ITService convertToEntity(ITServiceDTO serviceDTO) {
		ITService itService = null;

		if (serviceDTO.getId() != null) {
			itService = service.findById(serviceDTO.getId());
		}

		itService = modelMapper.map(serviceDTO, ITService.class);

		return itService;
	}
}
