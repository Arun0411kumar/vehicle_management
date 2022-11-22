package com.ideas2It.dao;

import java.sql.SQLException;
import java.util.List;

import com.ideas2It.model.Dealer;
import com.ideas2It.util.customException.VehicleManagementException;

/**
 * This interface handles dealer crud operation
 *
 * @version 1.0
 * @author arunkumar
 */
public interface DealerDao {

	/**
	 *
	 */
	public Dealer insertDealers(Dealer dealer) throws VehicleManagementException;

	/**
	 * This method was return dealer details
	 * 
	 * @throws SQLException
	 * @return its return dealer details
	 */
	public List<Dealer> getDealers() throws VehicleManagementException;

	/**
	 * This method is used to get all details based on given user dealer id
	 * 
	 * @param dealerId
	 * @throws SQLException, ParseException
	 * @return Dealer
	 */
	public Dealer retriveDealerById(int dealerId) throws VehicleManagementException;

	/**
	 * This method gets user given input whether its in then it will remove object
	 *
	 * @param dealerId - id from the service
	 * @throws SQLException
	 * @return if object removed it return true, else it return false
	 */
	public boolean deleteDealerById(int dealerId) throws VehicleManagementException;

	/**
	 * Gets vehicle id then change the specified value
	 *
	 * @param vehicleId
	 * @param result
	 * @throws SQLException
	 * @return if value updated then it return true, else it return false
	 */
	public boolean updateDealer(Dealer dealer) throws VehicleManagementException;
}