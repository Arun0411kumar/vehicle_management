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

import com.ideas2It.model.Dealer;
import com.ideas2It.service.DealerService;
import com.ideas2It.util.customException.VehicleManagementException;
import com.ideas2It.util.logger.VehicleManagementLogger;

@RestController
public class DealerController {
	
	@Autowired
	private DealerService dealerService;
	
	@PostMapping(value = "/createDealer")
	public Dealer createDealer(@RequestBody @Valid Dealer dealer) throws VehicleManagementException {
		try {
			dealer = dealerService.createDealer(dealer);
		} catch (VehicleManagementException e) {
			VehicleManagementLogger.displayVehicleError(e);
			throw new VehicleManagementException(e.getMessage());
		}
		return dealer;
	}
	
	@GetMapping(value = "/getDealers")
	public List<Dealer> getDealers() throws VehicleManagementException {
		List<Dealer> dealers = null;
        try {
			dealers = dealerService.getDealers();
		} catch (VehicleManagementException e) {
			VehicleManagementLogger.displayVehicleError(e);
			throw new VehicleManagementException(e.getMessage());
		}
		return dealers;
	}
	
	@GetMapping(value = "/getDealer/{id}")
	public Dealer getDealerById(@PathVariable("id") Integer id) throws VehicleManagementException {
		Dealer dealer = null;
		try {
			dealer = dealerService.getDealerById(id);
		} catch (VehicleManagementException e) {
			VehicleManagementLogger.displayVehicleError(e);
			throw new VehicleManagementException(e.getMessage());
		}
		return dealer;
	}
	
	@DeleteMapping(value = "/deleteDealer/{id}")
	public String deleteDealerById(@PathVariable("id") Integer id) throws VehicleManagementException {
		String status = "";
		try {
			if (dealerService.deleteDealerById(id)) {
				status = "deleted successfully";
			}
		} catch (VehicleManagementException e) {
			VehicleManagementLogger.displayVehicleError(e);
			throw new VehicleManagementException(e.getMessage());
		}
		return status;
	}
	
	@PutMapping(value = "/updateDealer/{id}")
	public String updateDealerById(@PathVariable("id") Integer id,
			@RequestBody @Valid Dealer dealer) throws VehicleManagementException {
		String status = "";
		try {
			if (dealerService.updateDealerById(id, dealer)) {
				status = "updated successfully";
			}
		} catch (VehicleManagementException e) {
			VehicleManagementLogger.displayVehicleError(e);
			throw new VehicleManagementException(e.getMessage());
		}
		return status;
	}
}

