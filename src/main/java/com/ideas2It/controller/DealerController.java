package com.ideas2It.controller;

import java.util.ArrayList;
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

import com.ideas2It.dto.DealerDto;
import com.ideas2It.service.DealerService;
import com.ideas2It.util.customException.VehicleManagementException;
import com.ideas2It.util.logger.VehicleManagementLogger;

/**
 * The dealer controller class that implements an application that Simply
 * create, view, search, update and delete these operations passed service from
 * controller class
 *
 * @version 1.0
 * @author arunkumar
 */
@RestController
@RequestMapping("/dealer")
public class DealerController {
	
	@Autowired
	private DealerService dealerService;
	
	/**
	 * Gets the dto of dealer and put it into database
	 * 
	 * @param dealerDto object
	 * @return ResponceEntity
	 * @throws VehicleManagementException
	 */
	@PostMapping(value = "/create-dealer")
	public ResponseEntity<DealerDto> createDealer(@RequestBody @Valid DealerDto dealerDto) 
			throws VehicleManagementException {
		try {
			dealerDto = dealerService.createDealer(dealerDto);
		} catch (VehicleManagementException e) {
			VehicleManagementLogger.displayVehicleError(e);
			throw new VehicleManagementException(e.getMessage());
		}
		return new ResponseEntity<>(dealerDto, HttpStatus.CREATED);
	}
	

	/**
	 * Display the dealers list
	 * 
	 * @return ResponseEntity
	 * @throws VehicleManagementException
	 */
	@GetMapping()
	public ResponseEntity<List<DealerDto>> getDealers() throws VehicleManagementException {
		List<DealerDto> dealers = new ArrayList<>();
        try {
        	dealers = dealerService.getDealers();		
		} catch (VehicleManagementException e) {
			VehicleManagementLogger.displayVehicleError(e);
			throw new VehicleManagementException(e.getMessage());
		}
		return new ResponseEntity<>(dealers, HttpStatus.OK);
	}
	
	/**
	 * Gets a dealerId from the user and display the dealer
	 * 
	 * @param dealerId as a integer wrapper class
	 * @return ResponceEntity
	 * @throws VehicleManagementException
	 */
	@GetMapping(value = "/{id}")
	public ResponseEntity<DealerDto> getDealerById(@PathVariable("id") Integer id) throws VehicleManagementException {
		DealerDto dealerDto = null;
		try {
			dealerDto = dealerService.getDealerById(id);
		} catch (VehicleManagementException e) {
			VehicleManagementLogger.displayVehicleError(e);
			throw new VehicleManagementException(e.getMessage());
		}
		return new ResponseEntity<>(dealerDto, HttpStatus.OK);
	}
	
	/**
	 * Gets a dealerId from the user and marking as deleted
	 * 
	 * @param dealerId as a integer wrapper class
	 * @return ResponceEntity
	 * @throws VehicleManagementException
	 */
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<String> deleteDealerById(@PathVariable("id") Integer id) throws VehicleManagementException {
		String status = "";
		try {
			if (dealerService.deleteDealerById(id)) {
				status = "deleted successfully";
			}
		} catch (VehicleManagementException e) {
			VehicleManagementLogger.displayVehicleError(e);
			throw new VehicleManagementException(e.getMessage());
		}
		return new ResponseEntity<>(status, HttpStatus.OK);
	}
	
	/**
	 * This method get Dealer object If object is null It's throw custom exception
	 * then it will update the given object
	 * 
	 * @param dealerId as a integer wrapper class
	 * @return ResponceEntity
	 * @throws VehicleManagementException
	 */
	@PutMapping(value = "/{id}")
	public ResponseEntity<String> updateDealerById(@PathVariable("id") Integer id,
			@RequestBody @Valid DealerDto dealerDto) throws VehicleManagementException {
		String status = "";
		try {
			if (dealerService.updateDealerById(id, dealerDto)) {
				status = "updated successfully";
			}
		} catch (VehicleManagementException e) {
			VehicleManagementLogger.displayVehicleError(e);
			throw new VehicleManagementException(e.getMessage());
		}
		return new ResponseEntity<>(status, HttpStatus.OK);
	}
}

