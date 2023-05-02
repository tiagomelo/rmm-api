package com.ninjaone.dto.customer;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 * DTO with Customer device information.
 * 
 * @author Tiago Melo (tiagoharris@gmail.com)
 *
 */
public class CustomerDeviceDTO {
	
	private Integer id;
	
	@NotBlank(message = "'systemName' property is missing")
	private String systemName;
	
	@NotNull(message = "'typeId' property is missing")
	private Integer typeId;

	private Integer customerId;
	
	public String getSystemName() {
		return systemName;
	}

	public void setSystemName(String systemName) {
		this.systemName = systemName;
	}

	public Integer getTypeId() {
		return typeId;
	}

	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
	}

	public Integer getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
}
