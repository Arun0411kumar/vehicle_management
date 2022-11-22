package com.ideas2It.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ideas2It.model.Manufacturer;
import com.ideas2It.service.ManufacturerService;
import com.ideas2It.service.impl.ManufacturerServiceImpl;
import com.ideas2It.util.customException.VehicleManagementException;

@RestController
public class ManufacturerController {
	
	@Autowired
	private ManufacturerService manufacturerService;
	
	@PostMapping(value = "/createManufacturer")
	public Manufacturer createManufacturer(@RequestBody Manufacturer manufacturer) {
		try {
			return manufacturerService.createManufacturer(manufacturer);
		} catch (VehicleManagementException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	@GetMapping(value = "/getManufacturers")
	public List<Manufacturer> getManufacturers() {
        try {
			return manufacturerService.getManufacturers();
		} catch (VehicleManagementException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	@GetMapping(value = "/getManufacturer/{id}")
	public Manufacturer getManufacturerById(@PathVariable("id") Integer id) {
		try {
			return manufacturerService.getManufacturerById(id);
		} catch (VehicleManagementException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	@PostMapping(value = "/deleteManufacturer/{id}")
	public String deleteManufacturerById(@PathVariable("id") Integer id) {
		try {
			if (manufacturerService.deleteManufacturerById(id)) {
				return "deleted successfully";
			}
		} catch (VehicleManagementException e) {
			e.printStackTrace();
		}
		return "not deleted successfully";
	}
	
	@PostMapping(value = "/updateManufacturer/{id}")
	public String updateManufacturerById(@PathVariable("id") Integer id, @RequestBody Manufacturer manufacturer) {
		try {
			if (manufacturerService.updateManufacturerById(id, manufacturer)) {
				return "updated successfully";
			}
		} catch (VehicleManagementException e) {
			e.printStackTrace();
		}
		return "not updated successfully";
	}
}
