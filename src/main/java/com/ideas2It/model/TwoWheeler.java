package com.ideas2It.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.ideas2It.util.DateUtil;
import com.ideas2It.util.enumeration.Type; 

/**
 * The TwoWheeler class have two wheeler attributes
 * This class contain getter and setter method for two wheeler attributes
 *
 * @version 1.0
 * @author arunkumar
 */
@Entity
@Table(name = "twowheelers")
public class TwoWheeler extends Vehicle {
    @Column(name = "no_of_stroke", columnDefinition = "int")
    private byte noOfStroke;
    
    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private Type type; 
    
    @JoinColumn(name = "dealer_id", columnDefinition = "int")
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Dealer dealer; 
    
    @JoinColumn(name = "manufacturer_id", columnDefinition = "int not null")
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Manufacturer manufacturer;
   
    public int getAge(Date dateOfManufacture) {
        return DateUtil.differenceBetweenYear(
        		dateOfManufacture, 
        		DateUtil.currentDate);
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

	public Dealer getDealer() {
		return dealer;
	}

	public void setDealer(Dealer dealer) {
		this.dealer = dealer;
	}

	public Manufacturer getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(Manufacturer manufacturer) {
		this.manufacturer = manufacturer;
	}
}