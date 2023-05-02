package com.ninjaone.repository;

import java.math.BigDecimal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ninjaone.entity.CustomerInvoice;

/**
 * Repository for handling {@link CustomerInvoice}.
 * 
 * @author tiagomelo
 *
 */
@Repository
public interface CustomerInvoiceRepository extends JpaRepository<CustomerInvoice, Integer> {
	
	public CustomerInvoice findByCustomerId(Integer customerId);
	
	/**
	 * Updates customer's invoice value.
	 * 
	 * @param customerId
	 * @param value
	 */
	@Modifying
	@Query(nativeQuery = true, value = "MERGE INTO customer_invoice c1 USING ("
			+ "SELECT CAST(:custId AS BIGINT) as customer_id, "
			+ "SELECT CAST(:value AS NUMERIC) as total) c2 "
			+ "on c1.customer_id = c2.customer_id "
			+ "WHEN MATCHED THEN "
				+ "UPDATE SET c1.total = c1.total + :value "
			+ "WHEN NOT MATCHED THEN "
				+ "INSERT (customer_id, total) VALUES (c2.customer_id, c2.total)")
	public void updateCustomerInvoiceTotalValue(@Param("custId") Integer customerId, @Param("value") BigDecimal value);

}
