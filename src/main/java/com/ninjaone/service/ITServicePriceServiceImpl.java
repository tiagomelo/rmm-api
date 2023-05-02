package com.ninjaone.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ninjaone.entity.ITServicePrice;
import com.ninjaone.exception.ResourceNotFoundException;
import com.ninjaone.repository.ITServicePriceRepository;

/**
 * ITServicePriceServiceImpl implements {@link ITServicePriceService}.
 * 
 * @author Tiago Melo (tiagoharris@gmail.com)
 *
 */
@Service
public class ITServicePriceServiceImpl implements ITServicePriceService {

	@Autowired
	ITServicePriceRepository repository;
	
	@Override
	public ITServicePrice findById(Integer id) {
		ITServicePrice platform = repository.findById(id).orElse(null);
	    
	    if (platform == null) {
	      throw new ResourceNotFoundException(ITServicePrice.class.getSimpleName(), "id", id);
	    }
	    
	    return platform;
	}

	@Override
	public List<ITServicePrice> findAll() {
		return repository.findAllByOrderById();
	}

	@Override
	public ITServicePrice save(ITServicePrice newITServicePrice) {
		return repository.save(newITServicePrice);
	}

	@Override
	public void delete(Integer id) {
		ITServicePrice itServicePrice = findById(id);
	    
	    repository.delete(itServicePrice);
	}

}
