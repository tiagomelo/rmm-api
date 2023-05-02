package com.ninjaone.dto.customer;

import java.math.BigDecimal;

/**
 * DTO with Customer invoice information.
 * 
 * @author Tiago Melo (tiagoharris@gmail.com)
 *
 */
public class CustomerInvoiceDTO {
	
	private Integer customerId;
	
	private BigDecimal total;
	
	public Integer getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}
}
