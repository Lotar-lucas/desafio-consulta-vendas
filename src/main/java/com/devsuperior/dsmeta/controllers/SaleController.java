package com.devsuperior.dsmeta.controllers;

import com.devsuperior.dsmeta.dto.SaleSellerDTO;
import com.devsuperior.dsmeta.dto.SalesSummaryDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.devsuperior.dsmeta.dto.SaleMinDTO;
import com.devsuperior.dsmeta.services.SaleService;

import java.util.List;

@RestController
@RequestMapping(value = "/sales")
public class SaleController {

	@Autowired
	private SaleService service;
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<SaleMinDTO> findById(@PathVariable Long id) {
		SaleMinDTO dto = service.findById(id);
		return ResponseEntity.ok(dto);
	}

	@GetMapping(value = "/report")
	public ResponseEntity<?> getReport() {
		// TODO
		return null;
	}

	@GetMapping(value = "/summary")
	public ResponseEntity<List<SalesSummaryDTO>> getSummary(
    @RequestParam(name = "minDate", required = false, defaultValue = "") String minDate,
    @RequestParam(name = "maxDate", required = false, defaultValue = "") String maxDate
    ) {
      List<SalesSummaryDTO> salesSummarys =  service.findSalesSummary(maxDate, minDate);
		  return ResponseEntity.ok(salesSummarys);
	}
}
