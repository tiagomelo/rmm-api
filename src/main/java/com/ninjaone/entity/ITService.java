package com.ninjaone.entity;

import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

/**
 * Entity for table "service". 
 * 
 * @author Tiago Melo (tiagoharris@gmail.com)
 *
 */
@Entity(name = "service")
public class ITService {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String type;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;

		if (o == null)
			return false;

		if (this.getClass() != o.getClass())
			return false;

		ITService service = (ITService) o;

		return Objects.equals(getId(), service.getId()) && Objects.equals(getType(), service.getType());
	}
	
	@Override
	public int hashCode() {
		int hash = 7;

		hash = 31 * hash + Objects.hashCode(id);
		hash = 31 * hash + Objects.hashCode(type);
		return hash;
	}

}
