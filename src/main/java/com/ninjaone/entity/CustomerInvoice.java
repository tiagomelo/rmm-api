package com.ninjaone.entity;

import java.math.BigDecimal;
import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

/**
 * Entity for table "customer_invoice". 
 * 
 * @author Tiago Melo (tiagoharris@gmail.com)
 *
 */
@Entity(name = "customer_invoice")
public class CustomerInvoice {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "customer_id")
	private Customer customer;

	// see http://www.h2database.com/html/datatypes.html#numeric_type
	private BigDecimal total;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;

		if (o == null)
			return false;

		if (this.getClass() != o.getClass())
			return false;

		CustomerInvoice customerInvoice = (CustomerInvoice) o;

		return Objects.equals(getId(), customerInvoice.getId()) 
				&& Objects.equals(getCustomer().getId(), customerInvoice.getId())
				&& Objects.equals(getTotal(), customerInvoice.getTotal());
	}
	
	@Override
	public int hashCode() {
		int hash = 7;

		hash = 31 * hash + Objects.hashCode(id);
		hash = 31 * hash + Objects.hashCode(customer.getId());
		hash = 31 * hash + Objects.hashCode(total);
		return hash;
	}

}
