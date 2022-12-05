  package com.ideas2It.service.impl;

import java.text.ParseException;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ideas2It.convertor.DealerMapper;
import com.ideas2It.convertor.ManufacturerMapper;
import com.ideas2It.convertor.TwoWheelerMapper;
import com.ideas2It.dao.TwoWheelerDao;
import com.ideas2It.dto.DealerDto;
import com.ideas2It.dto.TwoWheelerDto;
import com.ideas2It.model.TwoWheeler;
import com.ideas2It.service.DealerService;
import com.ideas2It.service.ManufacturerService;
import com.ideas2It.service.TwoWheelerService;
import com.ideas2It.util.DateUtil;
import com.ideas2It.util.customException.VehicleManagementException;

/**
 * This class performs create, read, update, delete, search, range operation 
 * This class pass the data into TwoWheeler dao
 *
 * @version 1.0
 * @author arunkumar
 */
@Service
public class TwoWheelerServiceImpl implements TwoWheelerService {

	@Autowired
	private TwoWheelerDao twoWheelerDao;
	@Autowired
    private ManufacturerService manufacturerService;
	@Autowired
    private DealerService dealerService;
	@Autowired
	private TwoWheelerMapper twoWheelerMapper;

	/**
	 * It's sent generated vehicle Code
	 *
	 * @return its return vehicle Code Ex: Vehicle-1,Vehicle-2,.....
	 */
	private String generateVehicleCode() throws VehicleManagementException {
		long code = twoWheelerDao.count();
		return "Vehicle-" + (++code);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override 
	public TwoWheelerDto createTwoWheeler(TwoWheelerDto twoWheelerDto) throws VehicleManagementException {
		TwoWheeler twoWheeler = twoWheelerMapper.convertDtoToEntity(twoWheelerDto);
		twoWheeler.setVehicleCode(generateVehicleCode());
		twoWheeler = twoWheelerDao.save(twoWheeler);
		if (null == twoWheeler) {
			throw new VehicleManagementException("some problem when you create twoWheeler");
		}
		return twoWheelerMapper.convertEntityToDto(twoWheeler);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<TwoWheelerDto> getTwoWheelers() throws VehicleManagementException {
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
				return twoWheelerMapper.convertDtoToEntity(twoWheelers);
			}
		} else {
			throw new VehicleManagementException("some problem when you get twoWheeler list");
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public TwoWheelerDto getTwoWheelerByCode(String vehicleCode) throws VehicleManagementException {
		TwoWheeler twoWheeler = twoWheelerDao.findByCode(vehicleCode);
		if (null == twoWheeler || twoWheeler.isDeleted()) {
			throw new VehicleManagementException("some problem when you get twoWheeler by id");
		}
		return twoWheelerMapper.convertEntityToDto(twoWheeler);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean deleteTwoWheelerByCode(String vehicleCode) throws VehicleManagementException {
		TwoWheeler twoWheeler = twoWheelerMapper.convertDtoToEntity(getTwoWheelerByCode(vehicleCode));
		if (null != twoWheeler) {
			twoWheeler.setDeleted(true);
			return twoWheelerDao.save(twoWheeler).equals(twoWheeler);
		} else {
			throw new VehicleManagementException("some problem when you delete twoWheeler by id");
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean updateTwoWheelerByCode(String vehicleCode, TwoWheelerDto twoWheelerDto) throws VehicleManagementException {
		TwoWheeler twoWheeler = twoWheelerMapper.convertDtoToEntity(getTwoWheelerByCode(vehicleCode));
		if (null != twoWheelerDto && null != twoWheeler) {
			twoWheeler.setBrandName(twoWheelerDto.getBrandName());
			twoWheeler.setColour(twoWheelerDto.getColour());
			twoWheeler.setDateOfManufacture(twoWheelerDto.getDateOfManufacture());
			twoWheeler.setFuelType(twoWheelerDto.getFuelType());
			twoWheeler.setMileage(twoWheelerDto.getMileage());
			twoWheeler.setNoOfStroke(twoWheelerDto.getNoOfStroke());
			twoWheeler.setType(twoWheelerDto.getType());
			twoWheeler.setManufacturer(new ManufacturerMapper().convertDtoToEntity(twoWheelerDto.getManufacturer()));
			DealerDto dealerDto = twoWheelerDto.getDealer();
			if (null != dealerDto) {
				twoWheeler.setDealer(new DealerMapper().convertDtoToEntity(dealerDto));
			}
			return twoWheelerDao.save(twoWheeler).equals(twoWheeler);
		} else {
			throw new VehicleManagementException("some problem when you update twoWheeler by code");
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<TwoWheelerDto> retriveVehiclesInRange(String start, String end) throws VehicleManagementException {
		List<TwoWheeler> twoWheelers = null;
		try {
			twoWheelers = twoWheelerDao.retriveTwoWheelersInRange(DateUtil.getDate(start), DateUtil.getDate(end));
		} catch (ParseException e) {
			twoWheelers = twoWheelerDao.retriveTwoWheelersInRange(Byte.parseByte(start), Byte.parseByte(end));
		}
		if (twoWheelers.isEmpty()) {
			throw new VehicleManagementException("some problem when you update twoWheeler by code");
		}
		return twoWheelerMapper.convertDtoToEntity(twoWheelers);
	}

	/**
	 * {@inheritDoc}
	 */
	public List<TwoWheelerDto> searchTwoWheeler(String value) throws VehicleManagementException {
		List<TwoWheelerDto> twoWheelers = getTwoWheelers();
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

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<TwoWheelerDto> getTwoWheelerByCodes(String[] codes) throws VehicleManagementException {
		List<TwoWheeler> twoWheelers = null;
        List<String> codesAsList =  Arrays.asList(codes);
        twoWheelers = twoWheelerDao.findTwoWheelersInCodes(codesAsList);
        if (twoWheelers.isEmpty()) {
			throw new VehicleManagementException("some problem when you update twoWheeler by code");
        }
        return twoWheelerMapper.convertDtoToEntity(twoWheelers);
	}

}
