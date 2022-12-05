package com.ideas2It.convertor;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.ideas2It.dto.DealerDto;
import com.ideas2It.dto.TwoWheelerDto;
import com.ideas2It.model.Dealer;
import com.ideas2It.model.TwoWheeler;

@Component
public class DealerMapper {
	
	public DealerDto convertEntityToDto(Dealer dealer) {
		DealerDto dto = new DealerDto();
		List<TwoWheelerDto> twoWheelers = new ArrayList<>();
		dto.setId(dealer.getId());
		dto.setCity(dealer.getCity());
		dto.setCompany(dealer.getCompany());
		dto.setStockAvailable(dealer.getStockAvailable());
		if (!twoWheelers.isEmpty()) {
			for (TwoWheeler twoWheeler : dealer.getTwoWheelers()) {
				TwoWheelerDto twdto = new TwoWheelerMapper().convertEntityToDto(twoWheeler);
				twdto.setManufacturer(null);
				twdto.setDealer(null);
				twoWheelers.add(twdto);
			}
		}
		dto.setTwoWheelers(twoWheelers);
		return dto;
	}
	
	public Dealer convertDtoToEntity(DealerDto dto) {
		Dealer dealer = new Dealer();
		dealer.setId(dto.getId());
		dealer.setCity(dto.getCity());
		dealer.setCompany(dto.getCompany());
		dealer.setStockAvailable(dto.getStockAvailable());
		return dealer;
	}
}
