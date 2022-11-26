package com.ideas2It.dto;

import java.util.List;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

public class DealerDto {

	private int id;
	
	@NotBlank(message = "city should not be null or empty")
	private String city;
	
	@NotBlank(message = "company should not be null or empty")
	private String company;
	
	@Min(0)
	private int stockAvailable;
    private List<TwoWheelerDto> twoWheelers;
    
	public DealerDto(int id, String city, String company, int stockAvailable,List<TwoWheelerDto> twoWheelers) {
		super();
		this.id = id;
		this.city = city;
		this.company = company;
		this.stockAvailable = stockAvailable;
		this.twoWheelers = twoWheelers;
	}
	
	public DealerDto() {}
    
	public int getStockAvailable() {
		return stockAvailable;
	}
	public void setStockAvailable(int stockAvailable) {
		this.stockAvailable = stockAvailable;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public List<TwoWheelerDto> getTwoWheelers() {
		return twoWheelers;
	}
	public void setTwoWheelers(List<TwoWheelerDto> twoWheelers) {
		this.twoWheelers = twoWheelers;
	}
}
