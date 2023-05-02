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

import com.ninjaone.dto.platform.PlatformDTO;
import com.ninjaone.entity.Platform;
import com.ninjaone.service.PlatformService;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

/**
 * Restful controller responsible for mananing platforms.
 * 
 * @author Tiago Melo (tiagoharris@gmail.com)
 */
@RestController
@RequestMapping("/api")
@Tag(name = "platform")
public class PlatformController {

	@Autowired
	ModelMapper modelMapper;

	@Autowired
	PlatformService service;

	private static final Logger LOGGER = LoggerFactory.getLogger(PlatformController.class);

	/**
	 * Get platform by id.
	 * 
	 * @param platformId
	 * @return {@link PlatformDTO}
	 */
	@GetMapping("/platform/{id}")
	public PlatformDTO getPlatformById(@PathVariable(value = "id", required = true) Integer platformId) {
		LOGGER.info(String.format("getPlatformById() called with id [%s]", platformId));

		return convertToDTO(service.findById(platformId));
	}
	
	/**
	 * Get all platforms.
	 * 
	 * @return list of {@link PlatformDTO}
	 */
	@GetMapping("/platforms")
	public List<PlatformDTO> getAllPlatforms() {
		List<Platform> platforms = service.findAll();

		LOGGER.info(String.format("getAllPlatforms() returned %s records", platforms.size()));

		return platforms.stream().map(platform -> convertToDTO(platform)).collect(Collectors.toList());
	}

	/**
	 * Create platform.
	 * 
	 * @param platformDTO
	 * @return {@link PlatformDTO}
	 */
	@PostMapping("/platform")
	public PlatformDTO createPlatform(@Valid @RequestBody PlatformDTO platformDTO) {
		Platform platform = convertToEntity(platformDTO);

		LOGGER.info("createPlatform() called");

		return convertToDTO(service.save(platform));
	}

	/**
	 * Update platform.
	 * 
	 * @param platformId
	 * @param platformDTO
	 * @return {@link PlatformDTO}
	 */
	@PutMapping("/platform/{id}")
	public PlatformDTO updatePlatform(@PathVariable(value = "id", required = true) Integer platformId,
			@Valid @RequestBody PlatformDTO platformDTO) {
		platformDTO.setId(platformId);
		Platform platform = convertToEntity(platformDTO);

		LOGGER.info(String.format("updatePlatform() called with id [%s]", platformId));

		return convertToDTO(service.save(platform));
	}

	/**
	 * Delete platform.
	 * 
	 * @param platformId
	 * @return
	 */
	@DeleteMapping("/platform/{id}")
	  public ResponseEntity<?> deletePlatform(@PathVariable(value = "id") Integer platformId) {
	    service.delete(platformId);
	    
	    LOGGER.info(String.format("deletePlatform() called with id [%s]", platformId));
	    
	    return ResponseEntity.ok().build();
	  }
	
	private PlatformDTO convertToDTO(Platform platform) {
		return modelMapper.map(platform, PlatformDTO.class);
	}

	private Platform convertToEntity(PlatformDTO platformDTO) {
		Platform platform = null;

		if (platformDTO.getId() != null) {
			platform = service.findById(platformDTO.getId());
		}

		platform = modelMapper.map(platformDTO, Platform.class);

		return platform;
	}
}
