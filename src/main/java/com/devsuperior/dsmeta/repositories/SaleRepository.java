package com.devsuperior.dsmeta.repositories;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.devsuperior.dsmeta.dto.SaleMinDTO;
import com.devsuperior.dsmeta.dto.SaleMinSummaryDTO;
import com.devsuperior.dsmeta.entities.Sale;

public interface SaleRepository extends JpaRepository<Sale, Long> {
	
	
	/*
	 * SELECT TB_SALES.ID, DATE, AMOUNT, TB_SELLER.NAME FROM TB_SALES
		INNER JOIN  TB_SELLER ON TB_SALES.SELLER_ID = TB_SELLER.ID
		WHERE DATE BETWEEN '2022-05-01' AND '2022-05-31'
		AND LOWER(TB_SELLER.NAME) LIKE LOWER('%Odinson%')
	 */
	
	/*
	 * SELECT SUM(TB_SALES.AMOUNT), TB_SELLER.NAME FROM TB_SALES
		INNER JOIN TB_SELLER ON TB_SALES.SELLER_ID = TB_SELLER.ID
		WHERE DATE BETWEEN '2022-05-01' AND '2023-05-31'
		GROUP BY (TB_SELLER.NAME)

	 */
	
	@Query("SELECT new com.devsuperior.dsmeta.dto.SaleMinDTO(obj.id, obj.amount, obj.date, obj.seller.name) FROM Sale obj "
			+ "WHERE obj.date BETWEEN :minDate AND :maxDate "
			+ "AND LOWER(obj.seller.name) LIKE CONCAT('%', LOWER(:name),'%')")
	Page<SaleMinDTO> getReport(Pageable pageable, LocalDate minDate, LocalDate maxDate, String name);
	
	
	
	@Query("SELECT new com.devsuperior.dsmeta.dto.SaleMinSummaryDTO(SUM(obj.amount), obj.seller.name) FROM Sale obj "
			+ "WHERE obj.date BETWEEN :minDate AND :maxDate "
			+ "GROUP BY obj.seller.name")
	List<SaleMinSummaryDTO> getSummary(LocalDate minDate, LocalDate maxDate);
}
