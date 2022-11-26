package com.ideas2It.controller;

import java.util.ArrayList;
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

import com.ideas2It.convertor.Convertor;
import com.ideas2It.dto.DealerDto;
import com.ideas2It.model.Dealer;
import com.ideas2It.service.DealerService;
import com.ideas2It.util.customException.VehicleManagementException;
import com.ideas2It.util.logger.VehicleManagementLogger;

@RestController
public class DealerController {
	
	@Autowired
	private DealerService dealerService;
	
	private Convertor convertor = new Convertor();
	
	@PostMapping(value = "/createDealer")
	public DealerDto createDealer(@RequestBody @Valid DealerDto dealerDto) 
			throws VehicleManagementException {
		Dealer dealer = null;
		try {
			dealer = dealerService.createDealer(convertor.convertDtoToEntity(dealerDto));
		} catch (VehicleManagementException e) {
			VehicleManagementLogger.displayVehicleError(e);
			throw new VehicleManagementException(e.getMessage());
		}
		return convertor.convertEntityToDto(dealer);
	}
	
	@GetMapping(value = "/getDealers")
	public List<DealerDto> getDealers() throws VehicleManagementException {
		List<DealerDto> dealers = new ArrayList<>();
        try {
			for (Dealer dealer : dealerService.getDealers()) {
				dealers.add(convertor.convertEntityToDto(dealer));
			}
		} catch (VehicleManagementException e) {
			VehicleManagementLogger.displayVehicleError(e);
			throw new VehicleManagementException(e.getMessage());
		}
		return dealers;
	}
	
	@GetMapping(value = "/getDealer/{id}")
	public DealerDto getDealerById(@PathVariable("id") Integer id) throws VehicleManagementException {
		Dealer dealer = null;
		try {
			dealer = dealerService.getDealerById(id);
		} catch (VehicleManagementException e) {
			VehicleManagementLogger.displayVehicleError(e);
			throw new VehicleManagementException(e.getMessage());
		}
		return convertor.convertEntityToDto(dealer);
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
			@RequestBody @Valid DealerDto dealerDto) throws VehicleManagementException {
		String status = "";
		try {
			if (dealerService.updateDealerById(id, convertor.convertDtoToEntity(dealerDto))) {
				status = "updated successfully";
			}
		} catch (VehicleManagementException e) {
			VehicleManagementLogger.displayVehicleError(e);
			throw new VehicleManagementException(e.getMessage());
		}
		return status;
	}
}

