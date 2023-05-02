package com.ninjaone.dto.devicetype;

import com.fasterxml.jackson.annotation.JsonInclude;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 * DTO for managing new device type creation.
 * 
 * @author Tiago Melo (tiagoharris@gmail.com)
 *
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class NewDeviceTypeDTO implements DeviceTypeDTO {

	private Integer id;

	@NotBlank(message = "'name' property is missing")
	private String name;

	@NotNull(message = "'platformId' property is missing")
	private Integer platformId;

	@Override
	public Integer getId() {
		return this.id;
	}

	@Override
	public void setId(Integer id) {
		this.id = id;
	}

	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public Integer getPlatformId() {
		return this.platformId;
	}

	@Override
	public void setPlatformId(Integer id) {
		this.platformId = id;
	}
}
