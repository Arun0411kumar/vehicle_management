  package com.ideas2It.service.impl;

import java.text.ParseException;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ideas2It.dao.TwoWheelerDao;
import com.ideas2It.model.Dealer;
import com.ideas2It.model.TwoWheeler;
import com.ideas2It.service.DealerService;
import com.ideas2It.service.ManufacturerService;
import com.ideas2It.service.TwoWheelerService;
import com.ideas2It.util.DateUtil;
import com.ideas2It.util.customException.VehicleManagementException;

@Service
public class TwoWheelerServiceImpl implements TwoWheelerService {

	@Autowired
	private TwoWheelerDao twoWheelerDao;
	@Autowired
    private ManufacturerService manufacturerService;
	@Autowired
    private DealerService dealerService;

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
		twoWheeler.setManufacturer(manufacturerService.getManufacturerById(twoWheeler.getManufacturer().getId()));
		Dealer dealer = twoWheeler.getDealer();
		if (null != dealer) {
			twoWheeler.setDealer(dealerService.getDealerById(dealer.getId()));
		}
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
		if (null == twoWheeler || twoWheeler.isDeleted()) {
			throw new VehicleManagementException("some problem when you get twoWheeler by id");
		}
		return twoWheeler;
	}

	@Override
	public boolean deleteTwoWheelerByCode(String vehicleCode) throws VehicleManagementException {
		TwoWheeler twoWheeler = getTwoWheelerByCode(vehicleCode);
		if (null != twoWheeler) {
			twoWheeler.setDeleted(true);
			return twoWheelerDao.save(twoWheeler).equals(twoWheeler);
		} else {
			throw new VehicleManagementException("some problem when you delete twoWheeler by id");
		}
	}

	@Override
	public boolean updateTwoWheelerByCode(String vehicleCode, TwoWheeler twoWheeler) throws VehicleManagementException {
		TwoWheeler twoWheelerForUpdate = getTwoWheelerByCode(vehicleCode);
		if (null != twoWheeler && null != twoWheelerForUpdate) {
			twoWheelerForUpdate.setBrandName(twoWheeler.getBrandName());
			twoWheelerForUpdate.setColour(twoWheeler.getColour());
			twoWheelerForUpdate.setDateOfManufacture(twoWheeler.getDateOfManufacture());
			twoWheelerForUpdate.setFuelType(twoWheeler.getFuelType());
			twoWheelerForUpdate.setMileage(twoWheeler.getMileage());
			twoWheelerForUpdate.setNoOfStroke(twoWheeler.getNoOfStroke());
			twoWheelerForUpdate.setType(twoWheeler.getType());
			twoWheelerForUpdate.setManufacturer(manufacturerService.getManufacturerById(twoWheeler.getManufacturer().getId()));
			Dealer dealer = twoWheeler.getDealer();
			if (null != dealer) {
				twoWheelerForUpdate.setDealer(dealerService.getDealerById(dealer.getId()));
			}
			return twoWheelerDao.save(twoWheelerForUpdate).equals(twoWheelerForUpdate);
		} else {
			throw new VehicleManagementException("some problem when you update twoWheeler by code");
		}
	}

	@Override
	public List<TwoWheeler> retriveVehiclesInRange(String start, String end) throws VehicleManagementException {
		List<TwoWheeler> twoWheelers = null;
		try {
			twoWheelers = twoWheelerDao.retriveTwoWheelersInRange(DateUtil.getDate(start), DateUtil.getDate(end));
		} catch (ParseException e) {
			twoWheelers = twoWheelerDao.retriveTwoWheelersInRange(Byte.parseByte(start), Byte.parseByte(end));
		}
		if (twoWheelers.isEmpty()) {
			throw new VehicleManagementException("some problem when you update twoWheeler by code");
		}
		return twoWheelers;
	}

	public List<TwoWheeler> searchTwoWheeler(String value) throws VehicleManagementException {
		List<TwoWheeler> twoWheelers = getTwoWheelers();
		twoWheelers = twoWheelers
				.stream()
		        .filter(twoWheeler -> (twoWheeler.getBrandName().toString().contains(value.toUpperCase())) 
		      		   || (twoWheeler.getColour().toString().contains(value.toUpperCase())) 
		       		   || (twoWheeler.getFuelType().toString().contains(value.toUpperCase()))
		       		   || (twoWheeler.getType().toString().contains(value.toUpperCase()))
		      		   || (twoWheeler.getVehicleCode().toLowerCase().contains(value.toLowerCase()))
		       		   || (Byte.toString(twoWheeler.getNoOfStroke()).contains(value))
		       		   || (Byte.toString(twoWheeler.getMileage()).contains(value))
             		   || (twoWheeler.getDateOfManufacture().toString().contains(value)))
	           .collect(Collectors.toList());
		if (twoWheelers.isEmpty()) {
			throw new VehicleManagementException("some problem when you update twoWheeler by code");
		}
		return twoWheelers;
	}

	@Override
	public List<TwoWheeler> getTwoWheelerByCodes(String[] codes) throws VehicleManagementException {
		List<TwoWheeler> twoWheelers = null;
        List<String> codesAsList =  Arrays.asList(codes);
        twoWheelers = twoWheelerDao.findTwoWheelersInCodes(codesAsList);
        if (twoWheelers.isEmpty()) {
			throw new VehicleManagementException("some problem when you update twoWheeler by code");
        }
        return twoWheelers;
	}

}
