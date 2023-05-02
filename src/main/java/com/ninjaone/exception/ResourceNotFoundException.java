package com.ninjaone.exception;

/**
 * Exception class that is thrown when it's not possible to find a resource.
 * 
 * @author Tiago Melo (tiagoharris@gmail.com)
 *
 */
public class ResourceNotFoundException extends RuntimeException {
  private static final long serialVersionUID = 1L;

  public ResourceNotFoundException(String resourceName, String fieldName, Object fieldValue) {
    super(String.format("%s not found with %s: '%s'", resourceName, fieldName, fieldValue));
  }
  
  public ResourceNotFoundException(String resourceName, String fieldName1, String fieldName2, Object fieldValue1, Object fieldValue2) {
	    super(String.format("%s not found with %s and %s: '%s and %s'", resourceName, fieldName1, fieldName2, fieldValue1, fieldValue2));
	  }
}
