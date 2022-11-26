package com.ideas2It.dto;

import java.util.Date;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.ideas2It.util.enumeration.Brand;
import com.ideas2It.util.enumeration.Colours;
import com.ideas2It.util.enumeration.FuelType;
import com.ideas2It.util.enumeration.Type;

public class TwoWheelerDto {

	private int id;
	private String vehicleCode;
	
	@NotNull(message = "brand name should not be null or empty")
	private Brand brandName;
	
	@NotNull(message = "fuel type should not be null or empty")
	private FuelType fuelType;
	
	@NotNull(message = "colour should not be null or empty")
	private Colours colour;
	
	@NotNull(message = "date of manufacture should not be null or empty")
	private Date dateOfManufacture;
	
	@Min(11)
	@Max(78)
	private byte mileage;
	
	@NotNull(message = "no of stroke should not be null or empty")
	private byte noOfStroke;
	
	@NotNull(message = "type could not be null or empty")
	private Type type;
	
	private ManufacturerDto manufacturer;
	private DealerDto dealer;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
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
	public byte getMileage() {
		return mileage;
	}
	public void setMileage(byte mileage) {
		this.mileage = mileage;
	}
	public byte getNoOfStroke() {
		return noOfStroke;
	}
	public void setNoOfStroke(byte noOfStroke) {
		this.noOfStroke = noOfStroke;
	}
	public Type getType() {
		return type;
	}
	public void setType(Type type) {
		this.type = type;
	}
	public FuelType getFuelType() {
		return fuelType;
	}
	public void setFuelType(FuelType fuelType) {
		this.fuelType = fuelType;
	}
	public ManufacturerDto getManufacturer() {
		return manufacturer;
	}
	public void setManufacturer(ManufacturerDto manufacturer) {
		this.manufacturer = manufacturer;
	}
	public DealerDto getDealer() {
		return dealer;
	}
	public void setDealer(DealerDto dealer) {
		this.dealer = dealer;
	}
}
