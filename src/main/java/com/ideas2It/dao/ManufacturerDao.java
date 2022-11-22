package com.ideas2It.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ideas2It.model.Manufacturer;

/**
 * This interface handles manufacturer crud operation
 *
 * @version 1.0
 * @author arunkumar
 */
public interface ManufacturerDao extends JpaRepository<Manufacturer, Integer> {

}