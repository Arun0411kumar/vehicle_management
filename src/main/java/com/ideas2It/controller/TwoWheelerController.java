package com.ideas2It.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import com.ideas2It.convertor.Convertor;
import com.ideas2It.dto.TwoWheelerDto;
import com.ideas2It.model.TwoWheeler;
import com.ideas2It.service.TwoWheelerService;
import com.ideas2It.util.customException.VehicleManagementException;
import com.ideas2It.util.logger.VehicleManagementLogger;

@RestController
public class TwoWheelerController {
	
	@Autowired
	private TwoWheelerService twoWheelerService; 
	
	private Convertor convertor = new Convertor();
	
	@GetMapping("/home")
	public String display() { 
		return "welcome to arun world";
    }
 
	@PostMapping("/createTwoWheeler")
	public TwoWheelerDto createTwoWheeler(@RequestBody @Valid TwoWheelerDto twoWheelerDto)
			throws VehicleManagementException {
		TwoWheeler twoWheeler = null;
		try {
			twoWheeler = twoWheelerService.createTwoWheeler(
					convertor.convertDtoToEntity(twoWheelerDto));
		} catch (VehicleManagementException e) {
			VehicleManagementLogger.displayVehicleError(e);
			throw new VehicleManagementException(e.getMessage());
		}
		return convertor.convertEntityToDto(twoWheeler);
	}
	
	@GetMapping(value = "/getTwoWheeler/{code}")
    public TwoWheelerDto getManufacturerByCode(@PathVariable("code") String vehicleCode) throws VehicleManagementException {
		TwoWheeler twoWheeler = null;
    	try {
    		twoWheeler = twoWheelerService.getTwoWheelerByCode(vehicleCode);
		} catch (VehicleManagementException e) {
			VehicleManagementLogger.displayVehicleError(e);
			throw new VehicleManagementException(e.getMessage());
		}
    	return convertor.convertEntityToDto(twoWheeler);
    }

	@GetMapping(value = "/getTwoWheelers")
    public List<TwoWheelerDto> getManufacturers() throws VehicleManagementException {
		List<TwoWheeler> twoWheelers = null;
    	try {
			twoWheelers = twoWheelerService.getTwoWheelers();
		} catch (VehicleManagementException e) {
			VehicleManagementLogger.displayVehicleError(e);
			throw new VehicleManagementException(e.getMessage());
		}
    	return convertor.convertDtoToEntity(twoWheelers);
    }
	
	@DeleteMapping(value = "/deleteTwoWheeler/{code}")
    public String deleteTwoWheelerByCode(@PathVariable("code") String vehicleCode) throws VehicleManagementException {
		String status = "";
    	try {
    		if (twoWheelerService.deleteTwoWheelerByCode(vehicleCode)) {
    			 status = "deleted successfully";	
    		}
		} catch (VehicleManagementException e) { 
			VehicleManagementLogger.displayVehicleError(e);
			throw new VehicleManagementException(e.getMessage());
		}
    	return status;
    }
	
	@PutMapping(value = "/updateTwoWheeler/{code}")
	public String updateManufacturerByCode(@PathVariable("code") String vehicleCode, 
			@RequestBody @Valid TwoWheelerDto twoWheelerDto) throws VehicleManagementException {
		String status = "";
		try {
			if (twoWheelerService.updateTwoWheelerByCode(
					vehicleCode, convertor.convertDtoToEntity(twoWheelerDto))) {
				status = "updated successfully";
			}
		} catch (VehicleManagementException e) {
			VehicleManagementLogger.displayVehicleError(e);
			throw new VehicleManagementException(e.getMessage());
		}
		return status;
	}
	
	@GetMapping(value = "/searchTwoWheelers/{letters}")
	private List<TwoWheelerDto> searchTwoWheelers(@PathVariable("letters") String letters) throws VehicleManagementException {
		List<TwoWheeler> twoWheelers = null;
	    try {
			twoWheelers = twoWheelerService.searchTwoWheeler(letters);
		} catch (VehicleManagementException e) {
			VehicleManagementLogger.displayVehicleError(e);
			throw new VehicleManagementException(e.getMessage());
		}
	    return convertor.convertDtoToEntity(twoWheelers);
    }	
	
	@GetMapping(value = "/range/{start}/{end}")
	private List<TwoWheelerDto> retriveTwoWheelersInRange(
			@PathVariable("start") String start, @PathVariable("end") String end) throws VehicleManagementException {
		List<TwoWheeler> twoWheelers = null;
			try {
				 twoWheelers = twoWheelerService.retriveVehiclesInRange(start, end);
			} catch (VehicleManagementException e) {
				VehicleManagementLogger.displayVehicleError(e);
				throw new VehicleManagementException(e.getMessage());
			}	
			return convertor.convertDtoToEntity(twoWheelers);
	}
	
	@GetMapping(value = "/In")
	public List<TwoWheelerDto> getTwoWheelerInCodes(WebRequest webRequest) throws VehicleManagementException {
		List<TwoWheeler> twoWheelers = null;
		try {
			twoWheelers = twoWheelerService.getTwoWheelerByCodes(
					webRequest.getParameterValues("codes"));
		} catch (VehicleManagementException e) {
			VehicleManagementLogger.displayVehicleError(e);
			throw new VehicleManagementException(e.getMessage());
		}
		return convertor.convertDtoToEntity(twoWheelers);		
	}
}
