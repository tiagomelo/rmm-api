package com.ninjaone.dto.customer;

import com.fasterxml.jackson.annotation.JsonInclude;

import jakarta.validation.constraints.NotBlank;

/**
 * DTO for managing new customer creation.
 * 
 * @author Tiago Melo (tiagoharris@gmail.com)
 *
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class NewCustomerDTO implements CustomerDTO {

	private Integer id;

	@NotBlank(message = "'name' property is missing")
	private String name;
	
	@NotBlank(message = "'email' property is missing")
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
