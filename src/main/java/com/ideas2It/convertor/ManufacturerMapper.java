package com.ideas2It.convertor;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ideas2It.dto.ManufacturerDto;
import com.ideas2It.dto.TwoWheelerDto;
import com.ideas2It.model.Manufacturer;
import com.ideas2It.model.TwoWheeler;

@Component
public class ManufacturerMapper {

	@Autowired
	private TwoWheelerMapper twoWheelerMapper;
	
	public Manufacturer convertDtoToEntity(ManufacturerDto dto) {
		Manufacturer manufacturer = new Manufacturer();
		manufacturer.setId(dto.getId());
		manufacturer.setName(dto.getName());
		manufacturer.setCompany(dto.getCompany());
		manufacturer.setInvestment(dto.getInvestment());
		return manufacturer;
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
			TwoWheelerDto twdto = twoWheelerMapper.convertEntityToDto(twoWheeler);
            twdto.setManufacturer(null);
            twdto.setDealer(null);
			twoWheelers.add(twdto);
		}
		}
		dto.setTwoWheelers(twoWheelers);
		return dto;
	}
}
