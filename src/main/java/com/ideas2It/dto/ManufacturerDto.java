package com.ideas2It.dto;

import java.util.List;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class ManufacturerDto {

	private int id;
	
	@NotBlank(message = "name should not be null or empty")
	private String name;
	
	@NotBlank(message = "name should not be null or empty")
	private String company;
	
	@Min(60000)
	private double investment;
    private List<TwoWheelerDto> twoWheelers;
    
	public ManufacturerDto(int id, String name, String company,double investment,List<TwoWheelerDto> twoWheelers) {
		super();
		this.id = id;
		this.name = name;
		this.company = company;
		this.investment = investment;
		this.twoWheelers = twoWheelers;
	}
	
	public ManufacturerDto() {}
    
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public double getInvestment() {
		return investment;
	}
	public void setInvestment(double investment) {
		this.investment = investment;
	}
	public List<TwoWheelerDto> getTwoWheelers() {
		return twoWheelers;
	}
	public void setTwoWheelers(List<TwoWheelerDto> twoWheelers) {
		this.twoWheelers = twoWheelers;
	}

}
