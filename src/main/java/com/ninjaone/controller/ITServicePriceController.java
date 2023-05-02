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

import com.ninjaone.dto.itserviceprice.ITServicePriceDTO;
import com.ninjaone.dto.itserviceprice.NewITServicePriceDTO;
import com.ninjaone.dto.itserviceprice.UpdateITServicePriceDTO;
import com.ninjaone.entity.ITService;
import com.ninjaone.entity.ITServicePrice;
import com.ninjaone.entity.Platform;
import com.ninjaone.service.ITServicePriceService;
import com.ninjaone.service.ITServiceService;
import com.ninjaone.service.PlatformService;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

/**
 * Restful controller responsible for mananing IT service prices.
 * 
 * @author Tiago Melo (tiagoharris@gmail.com)
 */
@RestController
@RequestMapping("/api")
@Tag(name = "servicePrice")
public class ITServicePriceController {

	@Autowired
	ModelMapper modelMapper;

	@Autowired
	ITServicePriceService itServicePriceService;
	
	@Autowired
	PlatformService platformService;
	
	@Autowired
	ITServiceService itServiceService;

	private static final Logger LOGGER = LoggerFactory.getLogger(ITServicePriceController.class);

	/**
	 * Get IT service price by id.
	 * 
	 * @param servicePriceId
	 * @return {@link ITServicePriceDTO}
	 */
	@GetMapping("/servicePrice/{id}")
	public ITServicePriceDTO getServicePriceById(@PathVariable(value = "id", required = true) Integer servicePriceId) {
		LOGGER.info(String.format("getServicePriceById() called with id [%s]", servicePriceId));

		return convertToDTO(itServicePriceService.findById(servicePriceId));
	}

	/**
	 * Get all IT service prices.
	 * 
	 * @return list of {@link ITServicePriceDTO}
	 */
	@GetMapping("/servicePrices")
	public List<ITServicePriceDTO> getAllServicePrices() {
		List<ITServicePrice> servicePrices = itServicePriceService.findAll();

		LOGGER.info(String.format("getAllServicePrices() returned %s records", servicePrices.size()));

		return servicePrices.stream().map(servicePrice -> convertToDTO(servicePrice)).collect(Collectors.toList());
	}

	/**
	 * Create IT service price.
	 * 
	 * @param servicePriceDTO
	 * @return {@link ITServicePriceDTO}
	 */
	@PostMapping("/servicePrice")
	public ITServicePriceDTO createServicePrice(@Valid @RequestBody NewITServicePriceDTO servicePriceDTO) {
		ITServicePrice itServicePrice = convertToEntity(servicePriceDTO);

		LOGGER.info("createServicePrice() called");

		return convertToDTO(itServicePriceService.save(itServicePrice));
	}

	/**
	 * Update IT service price.
	 * 
	 * @param servicePriceId
	 * @param servicePriceDTO
	 * @return {@link ITServicePriceDTO}
	 */
	@PutMapping("/servicePrice/{id}")
	public ITServicePriceDTO updateServicePrice(@PathVariable(value = "id", required = true) Integer servicePriceId,
			@Valid @RequestBody UpdateITServicePriceDTO servicePriceDTO) {
		servicePriceDTO.setId(servicePriceId);
		ITServicePrice itServicePrice = convertToEntity(servicePriceDTO);

		LOGGER.info(String.format("updateServicePrice() called with id [%s]", servicePriceId));

		return convertToDTO(itServicePriceService.save(itServicePrice));
	}

	/**
	 * Delete IT service price.
	 * 
	 * @param servicePriceId
	 * @return
	 */
	@DeleteMapping("/servicePrice/{id}")
	public ResponseEntity<?> deleteServicePrice(@PathVariable(value = "id") Integer servicePriceId) {
		itServicePriceService.delete(servicePriceId);

		LOGGER.info(String.format("deleteServicePrice() called with id [%s]", servicePriceId));

		return ResponseEntity.ok().build();
	}

	private ITServicePriceDTO convertToDTO(ITServicePrice servicePrice) {
		return modelMapper.map(servicePrice, NewITServicePriceDTO.class);
	}

	private ITServicePrice convertToEntity(ITServicePriceDTO itServicePriceDTO) {
		ITServicePrice itServicePrice = null;
		ITService itService = null;
		Platform platform = null;

		if (itServicePriceDTO.getId() != null) {
			itServicePrice = itServicePriceService.findById(itServicePriceDTO.getId());
			itService = itServicePrice.getItService();
			platform = itServicePrice.getOsPlatform();
		}

		itServicePrice = modelMapper.map(itServicePriceDTO, ITServicePrice.class);
		
		itServicePrice.setItService(itService);
		itServicePrice.setOsPlatform(platform);
		
		if (itServicePriceDTO.getServiceId() != null) {
			ITService newITService = itServiceService.findById(itServicePriceDTO.getServiceId());
			itServicePrice.setItService(newITService);
		}
		
		if (itServicePriceDTO.getPlatformId() != null) {
			Platform newPlatform = platformService.findById(itServicePriceDTO.getPlatformId());
			itServicePrice.setOsPlatform(newPlatform);
		}

		return itServicePrice;
	}
}
