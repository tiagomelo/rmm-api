package com.ninjaone.entity;

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
 * Entity for table "device_service". 
 * 
 * @author Tiago Melo (tiagoharris@gmail.com)
 *
 */
@Entity(name = "device_service")
public class DeviceITService {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "device_id", insertable=false, updatable=false)
	private Integer deviceId;
	
	@Column(name = "service_id", insertable=false, updatable=false)
	private Integer serviceId;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "device_id")
	private Device device;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "service_id")
	private ITService itService;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Device getDevice() {
		return device;
	}

	public void setDevice(Device device) {
		this.device = device;
	}

	public ITService getItService() {
		return itService;
	}

	public void setItService(ITService itService) {
		this.itService = itService;
	}

	public Integer getServiceId() {
		return serviceId;
	}

	public void setServiceId(Integer serviceId) {
		this.serviceId = serviceId;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;

		if (o == null)
			return false;

		if (this.getClass() != o.getClass())
			return false;

		DeviceITService deviceITService = (DeviceITService) o;

		return Objects.equals(getId(), deviceITService.getId()) 
				&& Objects.equals(getDevice().getId(), deviceITService.getDevice().getId())
				&& Objects.equals(getItService().getId(), deviceITService.getItService().getId());
	}
	
	@Override
	public int hashCode() {
		int hash = 7;

		hash = 31 * hash + Objects.hashCode(id);
		hash = 31 * hash + Objects.hashCode(device.getId());
		hash = 31 * hash + Objects.hashCode(itService.getId());
		return hash;
	}

}
