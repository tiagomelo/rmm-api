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

import com.ninjaone.dto.devicetype.DeviceTypeDTO;
import com.ninjaone.dto.devicetype.NewDeviceTypeDTO;
import com.ninjaone.dto.devicetype.UpdateDeviceTypeDTO;
import com.ninjaone.entity.DeviceType;
import com.ninjaone.entity.Platform;
import com.ninjaone.service.DeviceTypeService;
import com.ninjaone.service.PlatformService;

import io.micrometer.common.util.StringUtils;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

/**
 * Restful controller responsible for mananing device types.
 * 
 * @author Tiago Melo (tiagoharris@gmail.com)
 *
 */
@RestController
@RequestMapping("/api")
@Tag(name = "deviceType")
public class DeviceTypeController {

	@Autowired
	ModelMapper modelMapper;

	@Autowired
	DeviceTypeService service;
	
	@Autowired
	PlatformService platformService;

	private static final Logger LOGGER = LoggerFactory.getLogger(DeviceTypeController.class);

	/**
	 * Get device type by id.
	 * 
	 * @param deviceTypeId
	 * @return {@link DeviceTypeDTO}
	 */
	@GetMapping("/deviceType/{id}")
	public DeviceTypeDTO getDeviceTypeById(@PathVariable(value = "id", required = true) Integer deviceTypeId) {
		LOGGER.info(String.format("getDeviceTypeById() called with id [%s]", deviceTypeId));

		return convertToDTO(service.findById(deviceTypeId));
	}
	
	/**
	 * Get all device types.
	 * 
	 * @return list of {@link DeviceTypeDTO}
	 */
	@GetMapping("/deviceTypes")
	public List<DeviceTypeDTO> getAllDeviceTypes() {
		List<DeviceType> deviceTypes = service.findAll();

		LOGGER.info(String.format("getAllDeviceTypes() returned %s records", deviceTypes.size()));

		return deviceTypes.stream().map(deviceType -> convertToDTO(deviceType)).collect(Collectors.toList());
	}

	/**
	 * Create device type.
	 * 
	 * @param deviceTypeDTO
	 * @return {@link DeviceTypeDTO}
	 */
	@PostMapping("/deviceType")
	public DeviceTypeDTO createDeviceType(@Valid @RequestBody NewDeviceTypeDTO deviceTypeDTO) {
		DeviceType deviceType = convertToEntity(deviceTypeDTO);

		LOGGER.info("createDeviceType() called");
		
		Platform platform = platformService.findById(deviceTypeDTO.getPlatformId());
		deviceType.setPlatform(platform);

		return convertToDTO(service.save(deviceType));
	}

	/**
	 * Update device type.
	 * 
	 * @param deviceTypeId
	 * @param deviceTypeDTO
	 * @return {@link DeviceTypeDTO}
	 */
	@PutMapping("/deviceType/{id}")
	public DeviceTypeDTO updateDeviceType(@PathVariable(value = "id", required = true) Integer deviceTypeId,
			@Valid @RequestBody UpdateDeviceTypeDTO deviceTypeDTO) {
		deviceTypeDTO.setId(deviceTypeId);
		DeviceType deviceType = convertToEntity(deviceTypeDTO);

		LOGGER.info(String.format("updateDeviceType() called with id [%s]", deviceTypeId));

		return convertToDTO(service.save(deviceType));
	}

	/**
	 * Delete device type.
	 * 
	 * @param deviceTypeId
	 * @return
	 */
	@DeleteMapping("/deviceType/{id}")
	  public ResponseEntity<?> deleteDeviceType(@PathVariable(value = "id") Integer deviceTypeId) {
	    service.delete(deviceTypeId);
	    
	    LOGGER.info(String.format("deleteDeviceType() called with id [%s]", deviceTypeId));
	    
	    return ResponseEntity.ok().build();
	  }
	
	private DeviceTypeDTO convertToDTO(DeviceType deviceType) {
		return modelMapper.map(deviceType, NewDeviceTypeDTO.class);
	}

	private DeviceType convertToEntity(DeviceTypeDTO deviceTypeDTO) {
		DeviceType deviceType = null;
		Platform platform = null;

		if (deviceTypeDTO.getId() != null) {
			deviceType = service.findById(deviceTypeDTO.getId());
			platform = deviceType.getPlatform();
		}

		deviceType = modelMapper.map(deviceTypeDTO, DeviceType.class);
		deviceType.setPlatform(platform);
		
		if (StringUtils.isNotEmpty(deviceTypeDTO.getName())) {
			deviceType.setName(deviceTypeDTO.getName());
		}
		
		if (deviceTypeDTO.getPlatformId() != null) {
			Platform newPlatform = platformService.findById(deviceTypeDTO.getPlatformId());
			deviceType.setPlatform(newPlatform);
		}

		return deviceType;
	}
}
