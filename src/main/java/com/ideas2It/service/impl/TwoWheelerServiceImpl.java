package com.ideas2It.service.impl;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ideas2It.dao.TwoWheelerDao;
import com.ideas2It.model.TwoWheeler;
import com.ideas2It.service.TwoWheelerService;
import com.ideas2It.util.customException.VehicleManagementException;

@Service
public class TwoWheelerServiceImpl implements TwoWheelerService {

	@Autowired
	TwoWheelerDao twoWheelerDao;

	/**
	 * It's sent generated vehicle Code
	 *
	 * @return its return vehicle Code Ex: Vehicle-1,Vehicle-2,.....
	 */
	private String generateVehicleCode() throws VehicleManagementException {
		long code = twoWheelerDao.count();
		return "Vehicle-" + (++code);
	}

	@Override
	public TwoWheeler createTwoWheeler(TwoWheeler twoWheeler) throws VehicleManagementException {
		twoWheeler.setVehicleCode(generateVehicleCode());
		twoWheeler = twoWheelerDao.save(twoWheeler);
		if (null == twoWheeler) {
			throw new VehicleManagementException("some problem when you create twoWheeler");
		}
		return twoWheeler;
	}

	@Override
	public List<TwoWheeler> getTwoWheelers() throws VehicleManagementException {
		List<TwoWheeler> twoWheelers = twoWheelerDao.findAll();
		List<TwoWheeler> twoWheelersForIterate = new CopyOnWriteArrayList<>();
		twoWheelersForIterate.addAll(twoWheelers);
		if (!(twoWheelers.isEmpty())) {
			for (TwoWheeler twoWheeler : twoWheelersForIterate) {
				if (twoWheeler.isDeleted()) {
					twoWheelers.remove(twoWheeler);
				}
			}
			if (twoWheelers.isEmpty()) {
				throw new VehicleManagementException("some problem when you get twoWheeler list");
			} else {
				return twoWheelers;
			}
		} else {
			throw new VehicleManagementException("some problem when you get twoWheeler list");
		}
	}

	@Override
	public TwoWheeler getTwoWheelerByCode(String vehicleCode) throws VehicleManagementException {
		TwoWheeler twoWheeler = twoWheelerDao.findByCode(vehicleCode);
		if (twoWheeler.isDeleted()) {
			throw new VehicleManagementException("some problem when you get twoWheeler by id");
		}
		return twoWheeler;
	}

	@Override
	public boolean deleteTwoWheelerByCode(String vehicleCode) throws VehicleManagementException {
		TwoWheeler twoWheeler = getTwoWheelerByCode(vehicleCode);
		if (null != twoWheeler) {
			twoWheeler.setDeleted(true);
			twoWheelerDao.save(twoWheeler);
			return true;
		} else {
			throw new VehicleManagementException("some problem when you delete twoWheeler by id");
		}
	}

	@Override
	public boolean updateTwoWheelerByCode(String vehicleCode, TwoWheeler twoWheeler) throws VehicleManagementException {
		TwoWheeler twoWheelerForUpdate = getTwoWheelerByCode(vehicleCode);
		if (null != twoWheeler && null != twoWheelerForUpdate) {
			//twoWheelerForUpdate.setBrandName(twoWheeler.getBrandName());
			//twoWheelerForUpdate.setColour(twoWheeler.getColour());
			//twoWheelerForUpdate.setDateOfManufacture(twoWheeler);
			//twoWheeler.setManufacturer(twoWheelerForUpdate.getManufacturer());
			twoWheelerDao.save(twoWheeler);
			return true;
		} else {
			throw new VehicleManagementException("some problem when you create manufacturer");
		}
	}

	@Override
	public List<TwoWheeler> retriveVehiclesInRange(String start, String end) throws VehicleManagementException {
		// TODO Auto-generated method stub
		return null;
	}

	public List<TwoWheeler> searchTwoWheeler(String value) throws VehicleManagementException {
		List<TwoWheeler> twoWheelers = getTwoWheelers();
		twoWheelers = twoWheelers.stream()
		           .filter(twoWheeler -> (twoWheeler.getBrandName().toString().toLowerCase().contains(value)) 
		        		   || (twoWheeler.getColour().toString().toLowerCase().contains(value)) 
		        		   || (twoWheeler.getFuelType().toString().toLowerCase().contains(value))
		        		   || (twoWheeler.getType().toString().toLowerCase().contains(value))
		        		   || (Byte.toString(twoWheeler.getNoOfStroke()).contains(value))
		        		   || (Byte.toString(twoWheeler.getMileage()).contains(value))
		        		   || (twoWheeler.getDateOfManufacture().toString().contains(value)))
		           .collect(Collectors.toList());
		return twoWheelers;
	}

	@Override
	public List<TwoWheeler> getTwoWheelerByCodes(String[] codes) throws VehicleManagementException {
		// TODO Auto-generated method stub
		return null;
	}

}
