package com.devsuperior.dsmeta.services;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.devsuperior.dsmeta.dto.SaleMinDTO;
import com.devsuperior.dsmeta.dto.SaleMinSummaryDTO;
import com.devsuperior.dsmeta.entities.Sale;
import com.devsuperior.dsmeta.repositories.SaleRepository;

@Service
public class SaleService {

	@Autowired
	private SaleRepository repository;
	
	private LocalDate today;
	
	public SaleMinDTO findById(Long id) {
		Optional<Sale> result = repository.findById(id);
		Sale entity = result.get();
		return new SaleMinDTO(entity);
	}
	
	public Page<SaleMinDTO> getReport(Pageable pageable, Optional<String> minDate, Optional<String> maxDate, String name) {
		today = LocalDate.ofInstant(Instant.now(), ZoneId.systemDefault());
		
		if(!minDate.isPresent()) {
			LocalDate result = today.minusYears(1L);
			minDate = Optional.of(result.toString());
		}
		if(!maxDate.isPresent()) {
			maxDate = Optional.of(today.toString());
		}
		
		Page<SaleMinDTO>result = repository.getReport(pageable, LocalDate.parse(minDate.get()), LocalDate.parse(maxDate.get()), name);
		return result;
	}

	public List<SaleMinSummaryDTO> getSummary(Optional<String> minDate, Optional<String> maxDate) {
		today = LocalDate.ofInstant(Instant.now(), ZoneId.systemDefault());
	
		if(!minDate.isPresent()) {
			LocalDate result = today.minusYears(1L);
			minDate = Optional.of(result.toString());
		}
		if(!maxDate.isPresent()) {
			maxDate = Optional.of(today.toString());
		}
		
		List<SaleMinSummaryDTO>result = repository.getSummary(LocalDate.parse(minDate.get()), LocalDate.parse(maxDate.get()));
		return result;	
	}
}
