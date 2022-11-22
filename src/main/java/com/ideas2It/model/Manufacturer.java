package com.ideas2It.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * The Manufacturer class have manufacturer attributes
 * This class contain getter and setter method for manufacturer attributes
 *
 * @version 1.0
 * @author arunkumar
 */

@Entity
@Table(name = "manufacturers")
public class Manufacturer extends BaseModel {
	private String name;
    private String company;
    private double investment;
    
    @OneToMany(mappedBy = "manufacturer", 
    		fetch = FetchType.LAZY, 
    		cascade = CascadeType.ALL)
    private List<TwoWheeler> twoWheelers;

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

	public List<TwoWheeler> getTwoWheelers() {
		return twoWheelers;
	}

	public void setTwoWheelers(List<TwoWheeler> twoWheelers) {
		this.twoWheelers = twoWheelers;
	}

	public String toString() {
        return "\n--------- Manufacturer -------------"
               + "\nManufacturer Id : " + getId() 
               + "\nName            : " + getName() 
               + "\nCompany         : " + getCompany() 
               + "\ninvestment      : " + getInvestment()
               + "\n-------------------------------";
    }
}