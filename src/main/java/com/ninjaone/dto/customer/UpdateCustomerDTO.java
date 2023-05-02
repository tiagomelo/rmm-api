package com.ninjaone.dto.customer;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * DTO for managing customer updates.
 * 
 * @author Tiago Melo (tiagoharris@gmail.com)
 *
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UpdateCustomerDTO implements CustomerDTO {

	private Integer id;

	private String name;
	
	private String email;

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
	public String getEmail() {
		return this.email;
	}

	@Override
	public void setEmail(String email) {
		this.email = email;
	}

}
