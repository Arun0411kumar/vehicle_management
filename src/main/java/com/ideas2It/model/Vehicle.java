package com.ideas2It.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import com.ideas2It.util.enumeration.Brand;
import com.ideas2It.util.enumeration.Colours;
import com.ideas2It.util.enumeration.FuelType;

/**
 * The vehicle class have vehicle based attributes
 * The getter and setter method is used to update and retrieve the attributes 
 *
 * @version 1.0
 * @author arunkumar
 */
@MappedSuperclass
public class Vehicle extends BaseModel {
	@Column(name = "vehicle_code", columnDefinition = "varchar(255)")
    private String vehicleCode;
	
	@NotBlank(message = "brand name should not be null or empty")
	@Column(name = "brand_name")
    @Enumerated(EnumType.STRING)
    private Brand brandName;
	
	@NotBlank(message = "fuel type should not be null or empty")
	@Column(name = "fuel_type")
    @Enumerated(EnumType.STRING)
    private FuelType fuelType;
	
	@Min(11)
	@Max(78)
	@Column(name = "mileage", columnDefinition = "int")
	private byte mileage;
	
	@NotBlank(message = "colour should not be null or empty")
	@Column(name = "colour")
    @Enumerated(EnumType.STRING)
    private Colours colour;
	
	@NotBlank(message = "date of birth should not be null or empty")
    @Column(name = "date_of_manufacture", columnDefinition = "date")
    private Date dateOfManufacture;

	public String getVehicleCode() {
		return vehicleCode;
	}

	public void setVehicleCode(String vehicleCode) {
		this.vehicleCode = vehicleCode;
	}

	public Brand getBrandName() {
		return brandName;
	}

	public void setBrandName(Brand brandName) {
		this.brandName = brandName;
	}

	public FuelType getFuelType() {
		return fuelType;
	}

	public void setFuelType(FuelType fuelType) {
		this.fuelType = fuelType;
	}

	public byte getMileage() {
		return mileage;
	}

	public void setMileage(byte mileage) {
		this.mileage = mileage;
	}

	public Colours getColour() {
		return colour;
	}

	public void setColour(Colours colour) {
		this.colour = colour;
	}

	public Date getDateOfManufacture() {
		return dateOfManufacture;
	}
	
	public void setDateOfManufacture(Date dateOfManufacture) {
		this.dateOfManufacture = dateOfManufacture;
	} 

}