package com.ideas2It.service;

import java.util.List;

import com.ideas2It.model.Dealer;
import com.ideas2It.util.customException.VehicleManagementException;

/**
 * This interface handles dealer crud operation
 *
 * @version 1.0
 * @author arunkumar
 */
public interface DealerService {

	/**
	 * This method create dealer object and return it
	 * 
	 * @param dealer
	 * @throws VehicleManagementException
	 * @return its return dealer object
	 */
	public Dealer createDealer(Dealer dealer) throws VehicleManagementException;

	/**
	 * This method was return dealer details
	 * 
	 * @throws VehicleManagementException
	 * @return its return dealer details
	 */
	public List<Dealer> getDealers() throws VehicleManagementException;

	/**
	 * This method was return dealer details by given id
	 * 
	 * @throws VehicleManagementException
	 * @return its return dealer details
	 */
	public Dealer getDealerById(int dealerId) throws VehicleManagementException;

	/**
	 * This method gets user given input wheather it's in, then it will return
	 * object
	 *
	 * @param dealerId
	 * @throws VehicleManagementException
	 * @return its return two wheeler object
	 */
	public boolean deleteDealerById(int dealerId) throws VehicleManagementException;

	/**
	 * Gets dealer id then change the specified value
	 *
	 * @param dealerId
	 * @param result
	 * @throws VehicleManagementException
	 * @return if value updated then it return true, else it return false
	 */
	public boolean updateDealer(Dealer dealer) throws VehicleManagementException;
}