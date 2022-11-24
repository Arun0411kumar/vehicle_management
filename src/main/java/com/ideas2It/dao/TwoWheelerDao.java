package com.ideas2It.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ideas2It.model.TwoWheeler;

/**
 * This interface handles vehicle crud operation
 *
 * @version 1.0
 * @author arunkumar
 */
public interface TwoWheelerDao extends JpaRepository<TwoWheeler, Integer> {
	
    default public TwoWheeler findByCode(String code) {
    	char lastChar = code.charAt(code.length() - 1);  
    	int id = lastChar - '0';
		return findById(id).get();	
    }
    
    @Query("from TwoWheeler where (dateOfManufacture between :start and :end) and isDeleted = 0")
    public List<TwoWheeler> retriveTwoWheelersInRange(Date start, Date end);
    
    @Query("from TwoWheeler where (mileage between :start and :end) and isDeleted = 0")
    public List<TwoWheeler> retriveTwoWheelersInRange(Byte start, Byte end);

    @Query("from TwoWheeler where vehicleCode in :codes" )
    List<TwoWheeler> findTwoWheelersInCodes(List<String> codes);
}