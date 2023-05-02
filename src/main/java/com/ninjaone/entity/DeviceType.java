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
 * Entity for table "device_type". 
 * 
 * @author Tiago Melo (tiagoharris@gmail.com)
 *
 */
@Entity(name = "device_type")
public class DeviceType {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String name;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "platform_id")
	private Platform platform;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Platform getPlatform() {
		return platform;
	}

	public void setPlatform(Platform platform) {
		this.platform = platform;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;

		if (o == null)
			return false;

		if (this.getClass() != o.getClass())
			return false;

		DeviceType deviceType = (DeviceType) o;

		return Objects.equals(getId(), deviceType.getId()) 
				&& Objects.equals(getName(), deviceType.getName())
				&& Objects.equals(getPlatform().getId(), deviceType.getPlatform().getId());
	}
	
	@Override
	public int hashCode() {
		int hash = 7;

		hash = 31 * hash + Objects.hashCode(id);
		hash = 31 * hash + Objects.hashCode(name);
		hash = 31 * hash + Objects.hashCode(platform.getId());
		return hash;
	}

}
