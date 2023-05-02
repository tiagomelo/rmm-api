package com.ninjaone.dto.devicetype;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * DTO for managing device type updates.
 * 
 * @author Tiago Melo (tiagoharris@gmail.com)
 *
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UpdateDeviceTypeDTO implements DeviceTypeDTO {

	private Integer id;

	private String name;

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
