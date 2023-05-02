package com.ninjaone.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ninjaone.entity.ITService;
import com.ninjaone.entity.ITServicePrice;
import com.ninjaone.entity.Platform;

/**
 * Repository for handling {@link ITServicePrice}.
 * 
 * @author Tiago Melo (tiagoharris@gmail.com)
 *
 */
@Repository
public interface ITServicePriceRepository extends JpaRepository<ITServicePrice, Integer> {
	
	/**
	 * Get all IT service prices.
	 * 
	 * @return a list of {@link ITServicePrice}
	 */
	public List<ITServicePrice> findAllByOrderById();
	
	/**
	 * Find IT service price by {@link ITService} id.
	 * 
	 * @param id
	 * @return {@link ITServicePrice}
	 */
	public ITServicePrice findByServiceId(Integer id);
	
	/**
	 * Find IT service price by {@link ITService} id and {@link Platform} id.
	 * 
	 * @param serviceId
	 * @param platformId
	 * @return {@link ITServicePrice}
	 */
	@Query(nativeQuery = true, value = "SELECT * "
			+ "FROM service_price "
			+ "WHERE service_id = :serviceId "
			+ "AND (platform_id = :platformId OR platform_id = 1) "
			+ "LIMIT 1")
	public Optional<ITServicePrice> findFirstByServiceIdAndPlatformId(@Param("serviceId") Integer serviceId, @Param("platformId") Integer platformId);

}
