package com.ninjaone.dto.itserviceprice;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotNull;

/**
 * DTO for managing new IT service creation.
 * 
 * @author Tiago Melo (tiagoharris@gmail.com)
 *
 */
public class NewITServicePriceDTO implements ITServicePriceDTO {

	private Integer id;
	
	@NotNull(message = "'price' property is missing")
	private BigDecimal price;
	
	@NotNull(message = "'serviceId' property is missing")
	private Integer serviceId;
	
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
	public Integer getServiceId() {
		return this.serviceId;
	}

	@Override
	public void setServiceId(Integer id) {
		this.serviceId = id;
	}

	@Override
	public Integer getPlatformId() {
		return this.platformId;
	}

	@Override
	public void setPlatformId(Integer id) {
		this.platformId = id;
	}

	@Override
	public BigDecimal getPrice() {
		return this.price;
	}

	@Override
	public void setPrice(BigDecimal price) {
		this.price = price;
	}

}
