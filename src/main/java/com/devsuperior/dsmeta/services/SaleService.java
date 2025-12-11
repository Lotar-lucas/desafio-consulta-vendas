package com.devsuperior.dsmeta.services;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;

import com.devsuperior.dsmeta.dto.SalesSellerDTO;
import com.devsuperior.dsmeta.dto.SalesSummaryDTO;
import com.devsuperior.dsmeta.projections.SalesBySellerProjection;
import com.devsuperior.dsmeta.projections.SalesSummaryProjection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.devsuperior.dsmeta.dto.SaleMinDTO;
import com.devsuperior.dsmeta.entities.Sale;
import com.devsuperior.dsmeta.repositories.SaleRepository;

@Service
public class SaleService {

	@Autowired
	private SaleRepository repository;

  private record DateRange(LocalDate min, LocalDate max) {}
	
	public SaleMinDTO findById(Long id) {
		Optional<Sale> result = repository.findById(id);
		Sale entity = result.get();
		return new SaleMinDTO(entity);
	}

  public Page<SalesSellerDTO> findSalesBySeller(Pageable pageable, String  minDate, String maxDate, String name){
    DateRange dateRange = getDates(maxDate, minDate);
    LocalDate min = dateRange.min();
    LocalDate max = dateRange.max();

    return repository.searchSaleBySeller(min, max, name, pageable);
  }


  public List<SalesSummaryDTO> findSalesSummary(String maxDate, String minDate) {
    DateRange dateRange = getDates(maxDate, minDate);
    LocalDate min = dateRange.min();
    LocalDate max = dateRange.max();

    List<SalesSummaryProjection> result = repository.searchSummary(min, max);
    return result.stream().map(SalesSummaryDTO::new).toList();
  }

  public DateRange getDates(String maxDate, String minDate) {
    LocalDate today = LocalDate.ofInstant(Instant.now(), ZoneId.systemDefault());
    LocalDate todayMinusOneYear = today.minusYears(1L);

    LocalDate max = (maxDate == null || maxDate.isEmpty()) ? today : LocalDate.parse(maxDate);
    LocalDate min = (minDate == null || minDate.isEmpty()) ? todayMinusOneYear : LocalDate.parse(minDate);

    return new DateRange(min, max);
  }
}
