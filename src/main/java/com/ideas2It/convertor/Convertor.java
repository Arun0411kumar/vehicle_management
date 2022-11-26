package com.ideas2It.convertor;

import java.util.ArrayList;
import java.util.List;

import com.ideas2It.dto.DealerDto;
import com.ideas2It.dto.ManufacturerDto;
import com.ideas2It.dto.TwoWheelerDto;
import com.ideas2It.model.Dealer;
import com.ideas2It.model.Manufacturer;
import com.ideas2It.model.TwoWheeler;

public class Convertor {

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
	
	public ManufacturerDto convertEntityToDto(Manufacturer manufacturer) {
		ManufacturerDto dto = new ManufacturerDto();
		List<TwoWheelerDto> twoWheelers = new ArrayList<>();
		dto.setId(manufacturer.getId());
		dto.setName(manufacturer.getName());
		dto.setCompany(manufacturer.getCompany());
		dto.setInvestment(manufacturer.getInvestment());
		if (null != manufacturer.getTwoWheelers()) {
		for (TwoWheeler twoWheeler: manufacturer.getTwoWheelers()) {
			TwoWheelerDto twdto = convertEntityToDto(twoWheeler);
            twdto.setManufacturer(null);
            twdto.setDealer(null);
			twoWheelers.add(twdto);
		}
		}
		dto.setTwoWheelers(twoWheelers);
		return dto;
	}
	
	public DealerDto convertEntityToDto(Dealer dealer) {
		DealerDto dto = new DealerDto();
		List<TwoWheelerDto> twoWheelers = new ArrayList<>();
        dto.setId(dealer.getId());
        dto.setCity(dealer.getCity());
        dto.setCompany(dealer.getCompany());
        dto.setStockAvailable(dealer.getStockAvailable());
		if (!twoWheelers.isEmpty()) {
		for (TwoWheeler twoWheeler: dealer.getTwoWheelers()) {
			TwoWheelerDto twdto = convertEntityToDto(twoWheeler);
            twdto.setManufacturer(null);
            twdto.setDealer(null);
			twoWheelers.add(twdto);
		}
		}
		dto.setTwoWheelers(twoWheelers);
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
		twoWheeler.setManufacturer(convertDtoToEntity(dto.getManufacturer()));
		Dealer dealer = convertDtoToEntity(dto.getDealer());
		if (null != dealer) {
			twoWheeler.setDealer(dealer);
		}
		return twoWheeler;
	}
	
	public Manufacturer convertDtoToEntity(ManufacturerDto dto) {
		Manufacturer manufacturer = new Manufacturer();
		manufacturer.setId(dto.getId());
		manufacturer.setName(dto.getName());
		manufacturer.setCompany(dto.getCompany());
		manufacturer.setInvestment(dto.getInvestment());
		return manufacturer;
	}
	
	public Dealer convertDtoToEntity(DealerDto dto) {
		Dealer dealer = new Dealer();
		dealer.setId(dto.getId());
		dealer.setCity(dto.getCity());
		dealer.setCompany(dto.getCompany());
		dealer.setStockAvailable(dto.getStockAvailable());
		return dealer;
	}
	
	public List<TwoWheelerDto> convertDtoToEntity(List<TwoWheeler> twoWheelers) {
		List<TwoWheelerDto> twoWheelersDto = new ArrayList<>();
        for (TwoWheeler twoWheeler : twoWheelers) {
        	twoWheelersDto.add(convertEntityToDto(twoWheeler));
        }
		return twoWheelersDto;
	}
}
