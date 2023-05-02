package com.ninjaone.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ninjaone.entity.ITService;

/**
 * Repository for handling {@link ITService}.
 * 
 * @author Tiago Melo (tiagoharris@gmail.com)
 *
 */
@Repository
public interface ITServiceRepository extends JpaRepository<ITService, Integer> {

	/**
	 * Get all IT services ordered by id.
	 * 
	 * @return a list of {@link ITService}
	 */
	public List<ITService> findAllByOrderById();

}
