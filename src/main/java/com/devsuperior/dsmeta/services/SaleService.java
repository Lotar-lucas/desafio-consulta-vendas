package com.devsuperior.dsmeta.services;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;

import com.devsuperior.dsmeta.dto.SaleSellerDTO;
import com.devsuperior.dsmeta.dto.SalesSummaryDTO;
import com.devsuperior.dsmeta.projections.SalesBySellerProjection;
import com.devsuperior.dsmeta.projections.SalesSummaryProjection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devsuperior.dsmeta.dto.SaleMinDTO;
import com.devsuperior.dsmeta.entities.Sale;
import com.devsuperior.dsmeta.repositories.SaleRepository;

@Service
public class SaleService {

	@Autowired
	private SaleRepository repository;
	
	public SaleMinDTO findById(Long id) {
		Optional<Sale> result = repository.findById(id);
		Sale entity = result.get();
		return new SaleMinDTO(entity);
	}

//  public SaleMinDTO findSalesBySeller(String maxDate, String minDate, String name){};
//    LocalDate today = LocalDate.ofInstant(Instant.now(), ZoneId.systemDefault());
//    LocalDate todayMinusOneYear = today.minusYears(1L);
//
//    LocalDate max = (maxDate == null || maxDate.isEmpty()) ? today : LocalDate.parse(maxDate);
//    LocalDate min = (minDate == null || minDate.isEmpty()) ? todayMinusOneYear : LocalDate.parse(minDate);

//    List<SalesBySellerProjection> result = repository.searchSaleBySeller( max, min, name);
//    List<SaleSellerDTO> sales = result.stream().map(SaleSellerDTO::new).toList();
//
//    return null;
//  }


  public List<SalesSummaryDTO> findSalesSummary(String maxDate, String minDate) {

    LocalDate today = LocalDate.ofInstant(Instant.now(), ZoneId.systemDefault());
    LocalDate todayMinusOneYear = today.minusYears(1L);

    LocalDate max = (maxDate == null || maxDate.isEmpty()) ? today : LocalDate.parse(maxDate);
    LocalDate min = (minDate == null || minDate.isEmpty()) ? todayMinusOneYear : LocalDate.parse(minDate);

    List<SalesSummaryProjection> result = repository.searchSummary(min, max);
    return result.stream().map(SalesSummaryDTO::new).toList();
  }
}
