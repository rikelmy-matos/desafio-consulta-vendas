package com.devsuperior.dsmeta.dto;

import java.time.LocalDate;

import com.devsuperior.dsmeta.entities.Sale;

public class SaleMinSummaryDTO {

	private Double amount;
	private String sellerName;
	
	public SaleMinSummaryDTO(Double amount, String sellerName) {
		this.amount = amount;
		this.sellerName = sellerName;
	}

	public SaleMinSummaryDTO(Long id, Double amount, LocalDate date, String sellerName) {
		this.amount = amount;
		this.sellerName = sellerName;
	}
	
	public SaleMinSummaryDTO(Sale entity) {
		amount = entity.getAmount();
		sellerName = entity.getSeller().getName();
	}

	public Double getAmount() {
		return amount;
	}


	public String getSellerName() {
		return sellerName;
	}
}