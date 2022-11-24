package com.ideas2It.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import com.ideas2It.model.TwoWheeler;
import com.ideas2It.service.DealerService;
import com.ideas2It.service.ManufacturerService;
import com.ideas2It.service.TwoWheelerService;
import com.ideas2It.util.customException.VehicleManagementException;
import com.ideas2It.util.logger.VehicleManagementLogger;

@RestController
public class TwoWheelerController {
	
	@Autowired
	private TwoWheelerService twoWheelerService; 
 
	@PostMapping("/createTwoWheeler")
	public TwoWheeler createTwoWheeler(@RequestBody TwoWheeler twoWheeler) {
		try {
			twoWheeler = twoWheelerService.createTwoWheeler(twoWheeler);
		} catch (VehicleManagementException e) {
			twoWheeler = null;
			VehicleManagementLogger.displayVehicleError(e);
		}
		return twoWheeler;
	}
	
	@GetMapping(value = "/getTwoWheeler/{code}")
    public TwoWheeler getManufacturerByCode(@PathVariable("code") String vehicleCode) {
		TwoWheeler twoWheeler = null;
    	try {
    		twoWheeler = twoWheelerService.getTwoWheelerByCode(vehicleCode);
		} catch (VehicleManagementException e) {
			VehicleManagementLogger.displayVehicleError(e);
		}
    	return twoWheeler;
    }

	@GetMapping(value = "/getTwoWheelers")
    public List<TwoWheeler> getManufacturers() {
		List<TwoWheeler> twoWheelers = null;
    	try {
			twoWheelers = twoWheelerService.getTwoWheelers();
			System.out.println(twoWheelers);
		} catch (VehicleManagementException e) {
			VehicleManagementLogger.displayVehicleError(e);
		}
    	return twoWheelers;
    }
	
	@DeleteMapping(value = "/deleteTwoWheeler/{code}")
    public String deleteTwoWheelerByCode(@PathVariable("code") String vehicleCode) {
		String status = "";
    	try {
    		if (twoWheelerService.deleteTwoWheelerByCode(vehicleCode)) {
    			 status = "deleted successfully";	
    		}
		} catch (VehicleManagementException e) { 
			status = "Please provide valid id";
			VehicleManagementLogger.displayVehicleError(e);
		}
    	return status;
    }
	
	@PutMapping(value = "/updateTwoWheeler/{code}")
	public String updateManufacturerByCode(@PathVariable("code") String vehicleCode, 
			@RequestBody TwoWheeler twoWheeler) {
		String status = "";
		try {
			System.out.println(twoWheeler);
			if (twoWheelerService.updateTwoWheelerByCode(vehicleCode, twoWheeler)) {
				status = "updated successfully";
			}
		} catch (VehicleManagementException e) {
			status = "not updated successfully";
			e.printStackTrace();
		}
		return status;
	}
	
	@GetMapping(value = "/searchTwoWheelers/{letters}")
	private List<TwoWheeler> searchTwoWheelers(@PathVariable("letters") String letters) {
		List<TwoWheeler> twoWheelers = null;
	    try {
			twoWheelers = twoWheelerService.searchTwoWheeler(letters);
		} catch (VehicleManagementException e) {
			VehicleManagementLogger.displayVehicleError(e);
		}
	    return twoWheelers;
    }	
	
	@GetMapping(value = "/range/{start}/{end}")
	private List<TwoWheeler> retriveTwoWheelersInRange(@PathVariable("start") String start, @PathVariable("end") String end) {
		List<TwoWheeler> twoWheelers = null;
			try {
				 twoWheelers = twoWheelerService.retriveVehiclesInRange(start, end);
			} catch (VehicleManagementException e) {
				VehicleManagementLogger.displayVehicleError(e);
			}	
			return twoWheelers;
	}
	
	@GetMapping(value = "/In")
	public List<TwoWheeler> getTwoWheelerInCodes(WebRequest webRequest) {
		List<TwoWheeler> twoWheelers = null;
		String[] codes = webRequest.getParameterValues("codes");
		try {
			twoWheelers = twoWheelerService.getTwoWheelerByCodes(codes);
		} catch (VehicleManagementException e) {
			VehicleManagementLogger.displayVehicleError(e);
		}
		return twoWheelers;		
	}
}
