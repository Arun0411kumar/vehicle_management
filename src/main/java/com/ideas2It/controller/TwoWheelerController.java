package com.ideas2It.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import com.ideas2It.dto.TwoWheelerDto;
import com.ideas2It.service.TwoWheelerService;
import com.ideas2It.util.customException.VehicleManagementException;
import com.ideas2It.util.logger.VehicleManagementLogger;

/**
 * The TwoWheeler Controller controller class that implements an application that Simply
 * create, view, search, update and delete these operations called from service
 * to controller class
 *
 * @version 1.0
 * @author arunkumar
 */
@RestController
@RequestMapping("twoWheeler")
public class TwoWheelerController {
	
	@Autowired
	private TwoWheelerService twoWheelerService; 
 
	/**
	 * Gets the dto of twoWheeler and put it into database
	 * 
	 * @param twoWheelerDto object
	 * @return ResponceEntity
	 * @throws VehicleManagementException
	 */
	@PostMapping("/create-twoWheeler")
	public ResponseEntity<TwoWheelerDto> createTwoWheeler(@RequestBody @Valid TwoWheelerDto twoWheelerDto)
			throws VehicleManagementException {
		try {
			twoWheelerDto = twoWheelerService.createTwoWheeler(
					twoWheelerDto);
		} catch (VehicleManagementException e) {
			VehicleManagementLogger.displayVehicleError(e);
			throw new VehicleManagementException(e.getMessage());
		}
		return new ResponseEntity<>(twoWheelerDto, HttpStatus.CREATED);
	}
	
	/**
	 * Gets a twoWheeler code from the user and display the twoWheeler object
	 * 
	 * @param twoWheeler code as a string class object
	 * @return ResponceEntity
	 * @throws VehicleManagementException
	 */
	@GetMapping(value = "/{code}")
    public ResponseEntity<TwoWheelerDto> getManufacturerByCode(@PathVariable("code") String vehicleCode) throws VehicleManagementException {
		TwoWheelerDto twoWheelerDto = null;
    	try {
    		twoWheelerDto = twoWheelerService.getTwoWheelerByCode(vehicleCode);
		} catch (VehicleManagementException e) {
			VehicleManagementLogger.displayVehicleError(e);
			throw new VehicleManagementException(e.getMessage());
		}
		return new ResponseEntity<>(twoWheelerDto, HttpStatus.OK);
    }

	/**
	 * Display the twoWheelers list
	 * 
	 * @return ResponseEntity
	 * @throws VehicleManagementException
	 */
	@GetMapping()
    public ResponseEntity<List<TwoWheelerDto>> getManufacturers() throws VehicleManagementException {
		List<TwoWheelerDto> twoWheelers = null;
    	try {
			twoWheelers = twoWheelerService.getTwoWheelers();
		} catch (VehicleManagementException e) {
			VehicleManagementLogger.displayVehicleError(e);
			throw new VehicleManagementException(e.getMessage());
		}
		return new ResponseEntity<>(twoWheelers, HttpStatus.OK);
    }
	
	/**
	 * Gets a twoWheeler code from the user and marking as deleted
	 * 
	 * @param twoWheeler code as a string class object
	 * @return ResponceEntity
	 * @throws VehicleManagementException
	 */
	@DeleteMapping(value = "/{code}")
    public ResponseEntity<String> deleteTwoWheelerByCode(@PathVariable("code") String vehicleCode) throws VehicleManagementException {
		String status = "";
    	try {
    		if (twoWheelerService.deleteTwoWheelerByCode(vehicleCode)) {
    			 status = "deleted successfully";	
    		}
		} catch (VehicleManagementException e) { 
			VehicleManagementLogger.displayVehicleError(e);
			throw new VehicleManagementException(e.getMessage());
		}
		return new ResponseEntity<>(status, HttpStatus.OK);
    }
	
	/**
	 * This method get TwoWheelerDto object If object is null It's throw custom exception
	 * then it will update the given object
	 * 
	 * @param twoWheeler code as a string class object
	 * @return ResponceEntity
	 * @throws VehicleManagementException
	 */
	@PutMapping(value = "/{code}")
	public ResponseEntity<String> updateManufacturerByCode(@PathVariable("code") String vehicleCode, 
			@RequestBody @Valid TwoWheelerDto twoWheelerDto) throws VehicleManagementException {
		String status = "";
		try {
			if (twoWheelerService.updateTwoWheelerByCode(
					vehicleCode, twoWheelerDto)) {
				status = "updated successfully";
			}
		} catch (VehicleManagementException e) {
			VehicleManagementLogger.displayVehicleError(e);
			throw new VehicleManagementException(e.getMessage());
		}
		return new ResponseEntity<>(status, HttpStatus.OK);
	}
	
	/**
	 * This method get list of twoWheeler based on given letter matching
	 * 
	 * @param letters - string class object
	 * @return ResponceEntity
	 * @throws VehicleManagementException
	 */
	@GetMapping(value = "/search/{letters}")
	private ResponseEntity<List<TwoWheelerDto>> searchTwoWheelers(@PathVariable("letters") String letters) throws VehicleManagementException {
		List<TwoWheelerDto> twoWheelers = null;
	    try {
			twoWheelers = twoWheelerService.searchTwoWheeler(letters);
		} catch (VehicleManagementException e) {
			VehicleManagementLogger.displayVehicleError(e);
			throw new VehicleManagementException(e.getMessage());
		}
		return new ResponseEntity<>(twoWheelers, HttpStatus.OK);
    }	
	
	/**
	 * This method get list of twoWheeler based on given starting date/mileage
	 * and ending date/mileage
	 * 
	 * @param start - string class object
	 * @param end - string class object
	 * @return ResponceEntity
	 * @throws VehicleManagementException
	 */
	@GetMapping(value = "/range/{start}/{end}")
	private ResponseEntity<List<TwoWheelerDto>> retriveTwoWheelersInRange(
			@PathVariable("start") String start, @PathVariable("end") String end) throws VehicleManagementException {
		List<TwoWheelerDto> twoWheelers = null;
		try {
			 twoWheelers = twoWheelerService.retriveVehiclesInRange(start, end);
		} catch (VehicleManagementException e) {
			VehicleManagementLogger.displayVehicleError(e);
			throw new VehicleManagementException(e.getMessage());
		}	
		return new ResponseEntity<>(twoWheelers, HttpStatus.OK);		
	}
	
	/**
	 * This method get list of twoWheeler based on given list of codes
	 * 
	 * @param WebRequest
	 * @return ResponceEntity
	 * @throws VehicleManagementException
	 */
	@GetMapping(value = "/twoWheeler-in-codes")
	public ResponseEntity<List<TwoWheelerDto>> getTwoWheelerInCodes(WebRequest webRequest) throws VehicleManagementException {
		List<TwoWheelerDto> twoWheelers = null;
		try {
			twoWheelers = twoWheelerService.getTwoWheelerByCodes(
					webRequest.getParameterValues("codes"));
		} catch (VehicleManagementException e) {
			VehicleManagementLogger.displayVehicleError(e);
			throw new VehicleManagementException(e.getMessage());
		}
		return new ResponseEntity<>(twoWheelers, HttpStatus.OK);	
	}
}
