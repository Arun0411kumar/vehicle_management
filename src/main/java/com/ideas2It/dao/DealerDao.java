package com.ideas2It.dao;

import java.sql.SQLException;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ideas2It.model.Dealer;
import com.ideas2It.util.customException.VehicleManagementException;

/**
 * This interface handles dealer crud operation
 *
 * @version 1.0
 * @author arunkumar
 */
public interface DealerDao extends JpaRepository<Dealer, Integer> {

}