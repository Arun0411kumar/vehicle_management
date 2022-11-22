package com.ideas2It.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
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
@Table(name = "dealers")
public class Dealer extends BaseModel {
    private String company;
    @Column(name = "stock_available")
    private int stockAvailable;
    private String city;
    
    @OneToMany(mappedBy = "dealer", 
    		fetch = FetchType.LAZY, 
    		cascade = CascadeType.ALL)
    private List<TwoWheeler> twoWheelers;   
	
    public String getCompany() {
	    return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public int getStockAvailable() {
	    return stockAvailable;
    }

    public void setStockAvailable(int stockAvailable) {
	    this.stockAvailable = stockAvailable;
    }

    public String getCity() {
	    return city;
    }

    public void setCity(String city) {
	    this.city = city;
    }

    public List<TwoWheeler> getTwoWheelers() {
        return twoWheelers;
    } 

    public void setTwoWheelers(List<TwoWheeler> twoWheelers) {
        this.twoWheelers = twoWheelers;
    }

    public String toString() {
        return "\n--------- Dealer -------------"
               + "\nDealer Id       : " + getId() 
               + "\nCompany         : " + getCompany()
               + "\nstock available : " + getStockAvailable() 
               + "\nCity            : " + getCity()
               + "\n-------------------------------";
    } 
}