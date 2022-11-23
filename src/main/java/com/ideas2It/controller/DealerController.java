package com.ideas2It.controller;

import java.util.List;

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

@RestController
public class DealerController {
	
	@Autowired
	private DealerService dealerService;
	
	@PostMapping(value = "/createDealer")
	public Dealer createDealer(@RequestBody Dealer dealer) {
		try {
			dealer = dealerService.createDealer(dealer);
		} catch (VehicleManagementException e) {
			dealer = null;
			e.printStackTrace();
		}
		return dealer;
	}
	
	@GetMapping(value = "/getDealers")
	public List<Dealer> getDealers() {
		List<Dealer> dealers = null;
        try {
			dealers = dealerService.getDealers();
		} catch (VehicleManagementException e) {
			e.printStackTrace();
		}
		return dealers;
	}
	
	@GetMapping(value = "/getDealer/{id}")
	public Dealer getDealerById(@PathVariable("id") Integer id) {
		Dealer dealer = null;
		try {
			dealer = dealerService.getDealerById(id);
		} catch (VehicleManagementException e) {
			e.printStackTrace();
		}
		return dealer;
	}
	
	@DeleteMapping(value = "/deleteDealer/{id}")
	public String deleteDealerById(@PathVariable("id") Integer id) {
		String status = "";
		try {
			if (dealerService.deleteDealerById(id)) {
				status = "deleted successfully";
			}
		} catch (VehicleManagementException e) {
			status = "not deleted successfully";
			e.printStackTrace();
		}
		return status;
	}
	
	@PutMapping(value = "/updateDealer/{id}")
	public String updateDealerById(@PathVariable("id") Integer id,
			@RequestBody Dealer dealer) {
		String status = "";
		try {
			if (dealerService.updateDealerById(id, dealer)) {
				status = "updated successfully";
			}
		} catch (VehicleManagementException e) {
			status = "not updated successfully";
			e.printStackTrace();
		}
		return status;
	}
}

