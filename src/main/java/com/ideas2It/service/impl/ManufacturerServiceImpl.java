package com.ideas2It.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ideas2It.convertor.ManufacturerMapper;
import com.ideas2It.dao.ManufacturerDao;
import com.ideas2It.dto.ManufacturerDto;
import com.ideas2It.model.Manufacturer;
import com.ideas2It.service.ManufacturerService;
import com.ideas2It.util.customException.VehicleManagementException;

/**
 * This class performs create, read, update, delete operation This class pass
 * the data into manufacturer dao
 *
 * @version 1.0
 * @author arunkumar
 */
@Service
public class ManufacturerServiceImpl implements ManufacturerService {
	
	@Autowired
	private ManufacturerDao manufacturerDao;
	
	@Autowired
	private ManufacturerMapper manufacturerMapper;
	
	/**
	 * {@inheritDoc}
	 */
	public ManufacturerDto createManufacturer(ManufacturerDto manufacturerDto)
			throws VehicleManagementException {
		Manufacturer manufacturer = manufacturerDao.save(manufacturerMapper.convertDtoToEntity(manufacturerDto));
		if (null == manufacturer) {
			throw new VehicleManagementException("some problem when you create manufacturer");
		}
		return manufacturerMapper.convertEntityToDto(manufacturer);
	}

	/**
	 * {@inheritDoc}
	 */
	public List<ManufacturerDto> getManufacturers() throws VehicleManagementException {
		List<Manufacturer> manufacturers = manufacturerDao.findAll();
		List<Manufacturer> manufacturersForIterate = new CopyOnWriteArrayList<>();
		manufacturersForIterate.addAll(manufacturers);
		if (!(manufacturers.isEmpty())) {
		    for (Manufacturer manufacturer: manufacturersForIterate) {
			    if (manufacturer.isDeleted()) {
			    	manufacturers.remove(manufacturer);
			    }
		    }
		    if (manufacturers.isEmpty()) {
				throw new VehicleManagementException("some problem when you get manufacturer list");	    	
		    } else {
		    	return entityToDto(manufacturers);
		    }
		} else {
			throw new VehicleManagementException("some problem when you get manufacturer list");			
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public ManufacturerDto getManufacturerById(int manufacturerId) throws VehicleManagementException {
		Manufacturer manufacturer = manufacturerDao.findById(manufacturerId).orElse(null);	    
		if (null == manufacturer || manufacturer.isDeleted()) {
			throw new VehicleManagementException("some problem when you get manufacturer by id");				
		}
		return manufacturerMapper.convertEntityToDto(manufacturer);
	}

	/**
	 * {@inheritDoc}
	 */
	public boolean deleteManufacturerById(int manufacturerId) throws VehicleManagementException {
		Manufacturer manufacturer = manufacturerMapper.convertDtoToEntity(getManufacturerById(manufacturerId));
		if (null != manufacturer) {
		    manufacturer.setDeleted(true);
		    return manufacturerDao.save(manufacturer).equals(manufacturer);
		} else {
			throw new VehicleManagementException("some problem when you delete manufacturer by id");			
		}
	}
	
	/**
	 * {@inheritDoc}
	 */
	public boolean updateManufacturerById(int manufacturerId, ManufacturerDto manufacturerDto) throws VehicleManagementException {
		Manufacturer manufacturer = manufacturerMapper.convertDtoToEntity(getManufacturerById(manufacturerId));
		if (null != manufacturerDto && null != manufacturer) {
			manufacturer.setCompany(manufacturerDto.getCompany());
			manufacturer.setName(manufacturerDto.getName());
			manufacturer.setInvestment(manufacturerDto.getInvestment());
		    return manufacturerDao.save(manufacturer).equals(manufacturer);
		} else {
			throw new VehicleManagementException("some problem when you create manufacturer");			
		}
	}
	
	public List<ManufacturerDto> entityToDto(List<Manufacturer> manufacturers) {
		List<ManufacturerDto> manufacturerDto = new ArrayList<>();
		for (Manufacturer manufacturer: manufacturers) {
			manufacturerDto.add(manufacturerMapper.convertEntityToDto(manufacturer));
		}
		return manufacturerDto;
	}
}