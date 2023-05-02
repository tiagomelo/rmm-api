package com.ninjaone.dto.customer;

/**
 * DTO interface for Customer information.
 * 
 * @author Tiago Melo (tiagoharris@gmail.com)
 *
 */
public interface CustomerDTO {

	Integer getId();
	
	void setId(Integer id);
	
	String getName();
	
	void setName(String name);
	
	String getEmail();
	
	void setEmail(String email);
}
