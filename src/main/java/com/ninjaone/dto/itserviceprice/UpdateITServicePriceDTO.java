package com.ninjaone.dto.itserviceprice;

import java.math.BigDecimal;

/**
 * DTO for managing IT service updates.
 * 
 * @author Tiago Melo (tiagoharris@gmail.com)
 *
 */
public class UpdateITServicePriceDTO implements ITServicePriceDTO {

	private Integer id;
	
	private BigDecimal price;
	
	private Integer serviceId;
	
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
