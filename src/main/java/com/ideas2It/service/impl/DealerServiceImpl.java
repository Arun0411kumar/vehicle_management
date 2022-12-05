package com.ideas2It.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ideas2It.convertor.DealerMapper;
import com.ideas2It.dao.DealerDao;
import com.ideas2It.dto.DealerDto;
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
	
	@Autowired 
	private DealerMapper dealerMapper;
	
	/**
	 * {@inheritDoc}
	 */
	public DealerDto createDealer(DealerDto dealerDto)
			throws VehicleManagementException {
		Dealer dealer = dealerMapper.convertDtoToEntity(dealerDto);
		dealer = dealerDao.save(dealer);
		if (null == dealer) {
			throw new VehicleManagementException("some problem when you create dealer");
		}
		return dealerMapper.convertEntityToDto(dealer);
	}

	/**
	 * {@inheritDoc}
	 */
	public List<DealerDto> getDealers() throws VehicleManagementException {
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
		    	return entityToDto(dealers);
		    }
		} else {
			throw new VehicleManagementException("some problem when you get dealer list");			
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public DealerDto getDealerById(int dealerId) throws VehicleManagementException {
		Dealer dealer = dealerDao.findById(dealerId).orElse(null);
		if (null == dealer || dealer.isDeleted()) {
			throw new VehicleManagementException("some problem when you get dealer by id");				
		}
		return dealerMapper.convertEntityToDto(dealer);
	}

	/**
	 * {@inheritDoc}
	 */
	public boolean deleteDealerById(int dealerId) throws VehicleManagementException {
		Dealer dealer = dealerMapper.convertDtoToEntity(getDealerById(dealerId));
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
	public boolean updateDealerById(int dealerId, DealerDto dealerDto) throws VehicleManagementException {
		Dealer dealer = dealerMapper.convertDtoToEntity(getDealerById(dealerId));
		if (null != dealerDto && null != dealer) {
			dealer.setCompany(dealerDto.getCompany());
			dealer.setCity(dealerDto.getCity());;
			dealer.setStockAvailable(dealerDto.getStockAvailable());;
		    return dealerDao.save(dealer).equals(dealer);
		} else {
			throw new VehicleManagementException("some problem when you create dealer");			
		}
	}
	
	public List<DealerDto> entityToDto(List<Dealer> dealers) {
		List<DealerDto> dealerDto = new ArrayList<>();
		for (Dealer dealer: dealers) {
			dealerDto.add(dealerMapper.convertEntityToDto(dealer));
		}
		return dealerDto;
	}
}
