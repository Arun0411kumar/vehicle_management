package com.ideas2It.service.impl;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ideas2It.dao.DealerDao;
import com.ideas2It.model.Dealer;
import com.ideas2It.service.DealerService;
import com.ideas2It.util.customException.VehicleManagementException;

/**
 * This class performs create, read, update, delete operation This class pass
 * the data into Dealer dao
 *
 * @version 1.0
 * @author arunkumar
 */

@Service
public class DealerServiceImpl implements DealerService {
	
	@Autowired
	private DealerDao dealerDao;
	
	/**
	 * {@inheritDoc}
	 */
	public Dealer createDealer(Dealer dealer)
			throws VehicleManagementException {
		dealer = dealerDao.save(dealer);
		if (null == dealer) {
			throw new VehicleManagementException("some problem when you create dealer");
		}
		return dealer;
	}

	/**
	 * {@inheritDoc}
	 */
	public List<Dealer> getDealers() throws VehicleManagementException {
		List<Dealer> dealers = dealerDao.findAll();
		List<Dealer> dealersForIterate = new CopyOnWriteArrayList<>();
		dealersForIterate.addAll(dealers);
		if (!(dealers.isEmpty())) {
		    for (Dealer dealer: dealersForIterate) {
			    if (dealer.isDeleted()) {
			    	dealers.remove(dealer);
			    }
		    }
		    if (dealers.isEmpty()) {
				throw new VehicleManagementException("some problem when you get dealer list");	    	
		    } else {
		    	return dealers;
		    }
		} else {
			throw new VehicleManagementException("some problem when you get dealer list");			
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public Dealer getDealerById(int dealerId) throws VehicleManagementException {
		Dealer dealer = dealerDao.findById(dealerId).orElse(null);
		if (null == dealer || dealer.isDeleted()) {
			throw new VehicleManagementException("some problem when you get dealer by id");				
		}
		return dealer;
	}

	/**
	 * {@inheritDoc}
	 */
	public boolean deleteDealerById(int dealerId) throws VehicleManagementException {
		Dealer dealer = getDealerById(dealerId);
		if (null != dealer) {
		    dealer.setDeleted(true);
		    return dealerDao.save(dealer).equals(dealer);
		} else {
			throw new VehicleManagementException("some problem when you delete Dealer by id");			
		}
	}
	
	/**
	 * {@inheritDoc}
	 */
	public boolean updateDealerById(int dealerId, Dealer dealer) throws VehicleManagementException {
		Dealer dealerForUpdate = getDealerById(dealerId);
		if (null != dealer && null != dealerForUpdate) {
			dealerForUpdate.setCompany(dealer.getCompany());
			dealerForUpdate.setCity(dealer.getCity());;
			dealerForUpdate.setStockAvailable(dealer.getStockAvailable());;
		    return dealerDao.save(dealerForUpdate).equals(dealerForUpdate);
		} else {
			throw new VehicleManagementException("some problem when you create dealer");			
		}
	}
}
