package com.ideas2It.convertor;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ideas2It.dto.DealerDto;
import com.ideas2It.dto.ManufacturerDto;
import com.ideas2It.dto.TwoWheelerDto;
import com.ideas2It.model.Dealer;
import com.ideas2It.model.Manufacturer;
import com.ideas2It.model.TwoWheeler;

@Component
public class TwoWheelerMapper {
	
	public TwoWheelerDto convertEntityToDto(TwoWheeler twoWheeler) {
		TwoWheelerDto dto = new TwoWheelerDto();
		Manufacturer manufacturer = twoWheeler.getManufacturer();
		Dealer dealer = twoWheeler.getDealer();
		ManufacturerDto manufacturerDto = new ManufacturerDto(manufacturer.getId(), manufacturer.getName(),
				manufacturer.getCompany(), manufacturer.getInvestment(), null);
		if (null != dealer) {
			DealerDto dealerDto = new DealerDto(dealer.getId(),dealer.getCity(),
					dealer.getCompany(), dealer.getStockAvailable(), null);
			dto.setDealer(dealerDto);
		}
		dto.setId(twoWheeler.getId());
		dto.setBrandName(twoWheeler.getBrandName());
		dto.setVehicleCode(twoWheeler.getVehicleCode());
		dto.setColour(twoWheeler.getColour());
		dto.setType(twoWheeler.getType());
		dto.setFuelType(twoWheeler.getFuelType());
		dto.setDateOfManufacture(twoWheeler.getDateOfManufacture());
		dto.setMileage(twoWheeler.getMileage());
		dto.setNoOfStroke(twoWheeler.getNoOfStroke());
		dto.setManufacturer(manufacturerDto);
		return dto;
	}
	
	public TwoWheeler convertDtoToEntity(TwoWheelerDto dto) {
		TwoWheeler twoWheeler = new TwoWheeler();
		twoWheeler.setBrandName(dto.getBrandName());
		twoWheeler.setColour(dto.getColour());
		twoWheeler.setFuelType(dto.getFuelType());
		twoWheeler.setType(dto.getType());
		twoWheeler.setDateOfManufacture(dto.getDateOfManufacture());
		twoWheeler.setMileage(dto.getMileage());
		twoWheeler.setNoOfStroke(dto.getNoOfStroke());
		twoWheeler.setManufacturer(new ManufacturerMapper().convertDtoToEntity(dto.getManufacturer()));
		Dealer dealer = new DealerMapper().convertDtoToEntity(dto.getDealer());
		if (null != dealer) {
			twoWheeler.setDealer(dealer);
		}
		return twoWheeler;
	}
	
	public List<TwoWheelerDto> convertDtoToEntity(List<TwoWheeler> twoWheelers) {
		List<TwoWheelerDto> twoWheelersDto = new ArrayList<>();
        for (TwoWheeler twoWheeler : twoWheelers) {
        	twoWheelersDto.add(convertEntityToDto(twoWheeler));
        }
		return twoWheelersDto;
	}
}
