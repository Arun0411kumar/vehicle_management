package com.ideas2It.dao;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

import com.ideas2It.model.TwoWheeler;
import com.ideas2It.util.customException.VehicleManagementException;

/**
 * This interface handles vehicle crud operation
 *
 * @version 1.0
 * @author arunkumar
 */
public interface VehicleDao {

	/**
	 * This method return two wheeler object if its inserted
	 *
	 * @param Code         - Code from the service
	 * @param twoWheeler - two wheeler from the service
	 * @throws exception
	 * @return its return two wheeler object
	 */
	public TwoWheeler insertTwoWheeler(TwoWheeler twoWheeler) throws VehicleManagementException;

	/**
	 * This method was return two wheeler details
	 * 
	 * @throws SQLException, ParseException
	 * @return its return two wheeler details
	 */
	public List<TwoWheeler> retriveTwoWheelers() throws VehicleManagementException;

	/**
	 * This method gets user given input whether it's in, then it will return object
	 *
	 * @param userVehicleCode - Code from the service
	 * @throws SQLException, ParseException
	 * @return its return two wheeler object
	 */
	public TwoWheeler retriveTwoWheelerByCode(String vehicleCode) throws VehicleManagementException;

	/**
	 * This method gets user given input wheather its in then it will remove object
	 *
	 * @param userVehicleCode - Code from the service
	 * @throws SQLException
	 * @return if object removed it return true, else it return false
	 */
	public boolean deleteTwoWheelerByCode(String vehicleCode) throws VehicleManagementException;

	/**
	 * Gets vehicle Code then change the specified value
	 *
	 * @param vehicleCode
	 * @param result
	 * @throws SQLException
	 * @return if value updated then it return true, else it return false
	 */
	public boolean updateVehicle(TwoWheeler twoWheeler) throws VehicleManagementException;

	/**
	 * It's gets value and return that specific last code
	 *
	 * @param value - Gets value from the user
	 * @return it's return two wheeler list based on value
	 */
	public int getLastId() throws VehicleManagementException;

	/**
	 * It's gets value and return that specific searched list
	 *
	 * @param value - Gets value from the user
	 * @return it's return two wheeler list based on value
	 */
	public List<TwoWheeler> searchVehicle(String value) throws VehicleManagementException;

	/**
	 * this method get the vehicle details with the given range
	 *
	 * @param userChoiceOne
	 * @param userChoiceTwo
	 * @return it return the given range
	 */
	public List<TwoWheeler> retriveVehiclesInRange(String start, String end) throws VehicleManagementException;

	/**
	 * this method retrive the vehicle details with the given Codes
	 *
	 * @param result
	 * @throws SQLException
	 * @throws ParseException
	 * @return it return the given range
	 */
	public List<TwoWheeler> retriveTwoWheelerByCodes(String codes[]) throws VehicleManagementException;
}