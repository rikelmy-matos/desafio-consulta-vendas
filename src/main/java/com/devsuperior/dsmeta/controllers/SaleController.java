package com.devsuperior.dsmeta.controllers;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import com.devsuperior.dsmeta.dto.SaleMinDTO;
import com.devsuperior.dsmeta.dto.SaleMinSummaryDTO;
import com.devsuperior.dsmeta.services.SaleService;

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
	public ResponseEntity<Page<SaleMinDTO>> getReport(Pageable pageable, @RequestParam Optional<String> minDate, @RequestParam Optional<String> maxDate, @RequestParam(defaultValue = "") String name) {
		Page<SaleMinDTO> dto = service.getReport(pageable, minDate, maxDate, name);
		return ResponseEntity.ok(dto);
	}
	
	@GetMapping(value = "/summary")
	public ResponseEntity<List<SaleMinSummaryDTO>> getSummary(@RequestParam Optional<String> minDate, @RequestParam Optional<String> maxDate) {
		List<SaleMinSummaryDTO> dto = service.getSummary(minDate, maxDate);
		return ResponseEntity.ok(dto);
	}
}
