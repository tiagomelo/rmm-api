package com.ninjaone.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ninjaone.entity.ITService;
import com.ninjaone.exception.ResourceNotFoundException;
import com.ninjaone.repository.ITServiceRepository;

/**
 * ITServiceServiceImpl implements {@link ITServiceService}.
 * 
 * @author Tiago Melo (tiagoharris@gmail.com)
 *
 */
@Service
public class ITServiceServiceImpl implements ITServiceService {

	@Autowired
	ITServiceRepository repository;
	
	@Override
	public ITService findById(Integer id) {
		ITService platform = repository.findById(id).orElse(null);
	    
	    if (platform == null) {
	      throw new ResourceNotFoundException(ITService.class.getSimpleName(), "id", id);
	    }
	    
	    return platform;
	}

	@Override
	public List<ITService> findAll() {
		return repository.findAllByOrderById();
	}

	@Override
	public ITService save(ITService newITService) {
		return repository.save(newITService);
	}

	@Override
	public void delete(Integer id) {
		ITService platform = findById(id);
	    
	    repository.delete(platform);
	}

}
