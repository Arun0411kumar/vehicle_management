package com.ideas2It.dao;

import org.springframework.data.jpa.repository.JpaRepository;

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
}