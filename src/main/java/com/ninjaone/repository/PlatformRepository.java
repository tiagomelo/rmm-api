package com.ninjaone.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ninjaone.entity.Platform;

/**
 * Repository for handling {@link Platform}.
 * 
 * @author Tiago Melo (tiagoharris@gmail.com)
 *
 */
@Repository
public interface PlatformRepository extends JpaRepository<Platform, Integer> {
	
	/**
	 * Get all platforms ordered by id.
	 * 
	 * @return a list of {@link Platform}
	 */
	public List<Platform> findAllByOrderById();

}
