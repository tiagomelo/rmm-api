package com.ninjaone.dto.itserviceprice;

import java.math.BigDecimal;

/**
 * DTO interface for IT Service information.
 * 
 * @author Tiago Melo (tiagoharris@gmail.com)
 *
 */
public interface ITServicePriceDTO {

	Integer getId();
	
	void setId(Integer id);
	
	Integer getServiceId();
	
	void setServiceId(Integer id);
	
	Integer getPlatformId();
	
	void setPlatformId(Integer id);
	
	BigDecimal getPrice();
	
	void setPrice(BigDecimal price);
}
