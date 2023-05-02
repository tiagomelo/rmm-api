package com.ninjaone.entity;

import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

/**
 * Entity for table "device". 
 * 
 * @author Tiago Melo (tiagoharris@gmail.com)
 *
 */
@Entity(name = "device")
public class Device {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String systemName;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "customer_id")
	private Customer customer;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "type_id")
	private DeviceType deviceType;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getSystemName() {
		return systemName;
	}

	public void setSystemName(String systemName) {
		this.systemName = systemName;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public DeviceType getDeviceType() {
		return deviceType;
	}

	public void setDeviceType(DeviceType deviceType) {
		this.deviceType = deviceType;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;

		if (o == null)
			return false;

		if (this.getClass() != o.getClass())
			return false;

		Device device = (Device) o;

		return Objects.equals(getId(), device.getId()) 
				&& Objects.equals(getSystemName(), device.getSystemName())
				&& Objects.equals(getCustomer().getId(), device.getCustomer().getId())
				&& Objects.equals(getDeviceType().getId(), device.getDeviceType().getId());
	}
	
	@Override
	public int hashCode() {
		int hash = 7;

		hash = 31 * hash + Objects.hashCode(id);
		hash = 31 * hash + Objects.hashCode(systemName);
		hash = 31 * hash + Objects.hashCode(customer.getId());
		hash = 31 * hash + Objects.hashCode(deviceType.getId());
		return hash;
	}

}
