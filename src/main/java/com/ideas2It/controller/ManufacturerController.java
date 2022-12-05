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

import com.ideas2It.dto.ManufacturerDto;
import com.ideas2It.service.ManufacturerService;
import com.ideas2It.util.customException.VehicleManagementException;
import com.ideas2It.util.logger.VehicleManagementLogger;

/**
 * The manufacturer controller class that implements an application that Simply
 * create, view, search, update and delete these operations passed service from
 * controller class
 *
 * @version 1.0
 * @author arunkumar
 */
@RestController
@RequestMapping("/manufacturer")
public class ManufacturerController {
	
	@Autowired
	private ManufacturerService manufacturerService;
	
	/**
	 * Gets the dto of manufacturer and put it into database
	 * 
	 * @param ManufacturerDto object
	 * @return ResponceEntity
	 * @throws VehicleManagementException
	 */
	@PostMapping(value = "/create-manufacturer")
	public ResponseEntity<ManufacturerDto> createManufacturer(@RequestBody @Valid ManufacturerDto manufacturerDto) 
			throws VehicleManagementException {
		try {
			manufacturerDto = manufacturerService.createManufacturer(manufacturerDto);
		} catch (VehicleManagementException e) {
			VehicleManagementLogger.displayVehicleError(e);
			throw new VehicleManagementException(e.getMessage());
		}
		return new ResponseEntity<>(manufacturerDto, HttpStatus.CREATED);
	}
	
	/**
	 * Display the manufacturers list
	 * 
	 * @return ResponseEntity
	 * @throws VehicleManagementException
	 */
	@GetMapping()
	public ResponseEntity<List<ManufacturerDto>> getManufacturers() throws VehicleManagementException {
		List<ManufacturerDto> manufacturers = null;
        try {
        	manufacturers = manufacturerService.getManufacturers();
		} catch (VehicleManagementException e) {
			VehicleManagementLogger.displayVehicleError(e);
			throw new VehicleManagementException(e.getMessage());
		}
		return new ResponseEntity<>(manufacturers, HttpStatus.OK);
	}
	
	/**
	 * Gets a manufacturerId from the user and display the manufacturer
	 * 
	 * @param manufacturerId as a integer wrapper class
	 * @return ResponceEntity
	 * @throws VehicleManagementException
	 */
	@GetMapping(value = "/{id}")
	public ResponseEntity<ManufacturerDto> getManufacturerById(@PathVariable("id") Integer id) throws VehicleManagementException {
		ManufacturerDto manufacturerDto = null;
		try {
			manufacturerDto = manufacturerService.getManufacturerById(id);
		} catch (VehicleManagementException e) {
			VehicleManagementLogger.displayVehicleError(e);
			throw new VehicleManagementException(e.getMessage());
		}
		return new ResponseEntity<>(manufacturerDto, HttpStatus.OK);
	}
	
	/**
	 * Gets a manufacturerId from the user and marking as deleted
	 * 
	 * @param manufacturerId as a integer wrapper class
	 * @return ResponceEntity
	 * @throws VehicleManagementException
	 */
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<String> deleteManufacturerById(@PathVariable("id") Integer id) throws VehicleManagementException {
		String status = "";
		try {
			if (manufacturerService.deleteManufacturerById(id)) {
				status = "deleted successfully";
			}
		} catch (VehicleManagementException e) {
			VehicleManagementLogger.displayVehicleError(e);
			throw new VehicleManagementException(e.getMessage());
		}
		return new ResponseEntity<>(status, HttpStatus.OK);
	}
	
	/**
	 * This method get manufacturer object If object is null It's throw custom exception
	 * then it will update the given object
	 * 
	 * @param manufacturerId as a integer wrapper class
	 * @return ResponceEntity
	 * @throws VehicleManagementException
	 */
	@PutMapping(value = "/{id}")
	public ResponseEntity<String> updateManufacturerById(@PathVariable("id") Integer id, 
			@RequestBody @Valid ManufacturerDto manufacturerDto) throws VehicleManagementException {
		String status = "";
		try {
			if (manufacturerService.updateManufacturerById(
					id, manufacturerDto)) {
				status = "updated successfully";
			}
		} catch (VehicleManagementException e) {
			VehicleManagementLogger.displayVehicleError(e);
			throw new VehicleManagementException(e.getMessage());
		}
		return new ResponseEntity<>(status, HttpStatus.OK);
	}
}
