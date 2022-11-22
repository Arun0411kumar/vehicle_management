package com.ideas2It.service.impl;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ideas2It.dao.ManufacturerDao;
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
	
	/**
	 * {@inheritDoc}
	 */
	public Manufacturer createManufacturer(Manufacturer manufacturer)
			throws VehicleManagementException {
		manufacturer = manufacturerDao.save(manufacturer);
		if (null == manufacturer) {
			throw new VehicleManagementException("some problem when you create manufacturer");
		}
		return manufacturer;
	}

	/**
	 * {@inheritDoc}
	 */
	public List<Manufacturer> getManufacturers() throws VehicleManagementException {
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
		    	return manufacturers;
		    }
		} else {
			throw new VehicleManagementException("some problem when you get manufacturer list");			
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public Manufacturer getManufacturerById(int manufacturerId) throws VehicleManagementException {
		Manufacturer manufacturer = manufacturerDao.findById(manufacturerId).get();
		if (manufacturer.isDeleted()) {
			throw new VehicleManagementException("some problem when you get manufacturer by id");				
		}
		return manufacturer;
	}

	/**
	 * {@inheritDoc}
	 */
	public boolean deleteManufacturerById(int manufacturerId) throws VehicleManagementException {
		Manufacturer manufacturer = getManufacturerById(manufacturerId);
		if (null != manufacturer) {
		    manufacturer.setDeleted(true);
		    manufacturerDao.save(manufacturer);
		    return true;
		} else {
			throw new VehicleManagementException("some problem when you delete manufacturer by id");			
		}
	}
	
	/**
	 * {@inheritDoc}
	 */
	public boolean updateManufacturerById(int manufacturerId, Manufacturer manufacturer) throws VehicleManagementException {
		Manufacturer manufacturerForUpdate = getManufacturerById(manufacturerId);
		if (null != manufacturer && null != manufacturerForUpdate) {
			manufacturerForUpdate.setCompany(manufacturer.getCompany());
			manufacturerForUpdate.setName(manufacturer.getName());
			manufacturerForUpdate.setInvestment(manufacturer.getInvestment());
		    manufacturerDao.save(manufacturerForUpdate);
		    return true;
		} else {
			throw new VehicleManagementException("some problem when you create manufacturer");			
		}
	}
}