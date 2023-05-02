package com.ninjaone.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ninjaone.entity.Platform;
import com.ninjaone.exception.ResourceNotFoundException;
import com.ninjaone.repository.PlatformRepository;

/**
 * PlatformServiceImpl implements {@link PlatformService}.
 * 
 * @author Tiago Melo (tiagoharris@gmail.com)
 *
 */
@Service
public class PlatformServiceImpl implements PlatformService {

	@Autowired
	PlatformRepository repository;
	
	@Override
	public Platform findById(Integer id) {
		Platform platform = repository.findById(id).orElse(null);
	    
	    if (platform == null) {
	      throw new ResourceNotFoundException(Platform.class.getSimpleName(), "id", id);
	    }
	    
	    return platform;
	}

	@Override
	public List<Platform> findAll() {
		return repository.findAllByOrderById();
	}

	@Override
	public Platform save(Platform newPlatform) {
		return repository.save(newPlatform);
	}

	@Override
	public void delete(Integer id) {
		Platform platform = findById(id);
	    
	    repository.delete(platform);
	}

}
