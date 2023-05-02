package com.ninjaone.dto.devicetype;

/**
 * DTO interface for Device type information.
 * 
 * @author Tiago Melo (tiagoharris@gmail.com)
 *
 */
public interface DeviceTypeDTO {
	
	Integer getId();
	
	void setId(Integer id);
	
	String getName();
	
	void setName(String name);
	
	Integer getPlatformId();
	
	void setPlatformId(Integer id);
}
