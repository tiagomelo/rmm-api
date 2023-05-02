package com.ninjaone.entity;

import java.math.BigDecimal;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

/**
 * Entity for table "service_price". 
 * 
 * @author Tiago Melo (tiagoharris@gmail.com)
 *
 */
@Entity(name = "service_price")
public class ITServicePrice {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "service_id", insertable=false, updatable=false)
	private Integer serviceId;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "service_id")
	private ITService itService;

	@Column(name = "platform_id", insertable=false, updatable=false)
	private Integer platformId;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "platform_id")
	private Platform osPlatform;
	
	// see http://www.h2database.com/html/datatypes.html#numeric_type
	private BigDecimal price;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public ITService getItService() {
		return itService;
	}

	public void setItService(ITService itService) {
		this.itService = itService;
	}

	public Integer getPlatformId() {
		return platformId;
	}

	public void setPlatformId(Integer platformId) {
		this.platformId = platformId;
	}
	
	public Platform getOsPlatform() {
		return osPlatform;
	}

	public void setOsPlatform(Platform platform) {
		this.osPlatform = platform;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;

		if (o == null)
			return false;

		if (this.getClass() != o.getClass())
			return false;

		ITServicePrice service = (ITServicePrice) o;

		return Objects.equals(getId(), service.getId()) 
				&& Objects.equals(getItService(), service.getItService())
				&& Objects.equals(getOsPlatform(), service.getOsPlatform())
				&& Objects.equals(getPrice(), service.getPrice());
	}
	
	@Override
	public int hashCode() {
		int hash = 7;

		hash = 31 * hash + Objects.hashCode(id);
		hash = 31 * hash + Objects.hashCode(itService.getId());
		hash = 31 * hash + Objects.hashCode(osPlatform.getId());
		hash = 31 * hash + Objects.hashCode(price);
		return hash;
	}

	public Integer getServiceId() {
		return serviceId;
	}

	public void setServiceId(Integer serviceId) {
		this.serviceId = serviceId;
	}

}
