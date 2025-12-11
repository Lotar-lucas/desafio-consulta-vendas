package com.devsuperior.dsmeta.repositories;

import com.devsuperior.dsmeta.dto.SalesSellerDTO;
import com.devsuperior.dsmeta.projections.SalesSummaryProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import com.devsuperior.dsmeta.entities.Sale;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface SaleRepository extends JpaRepository<Sale, Long> {

  @Query("SELECT new com.devsuperior.dsmeta.dto.SalesSellerDTO(s.id, s.amount, s.date, s.seller.name) " +
    "FROM Sale s " +
    "JOIN s.seller seller " +
    "WHERE s.date BETWEEN :minDate AND :maxDate " +
    "AND UPPER(s.seller.name) LIKE UPPER(CONCAT('%', :name, '%'))"
  )
  Page<SalesSellerDTO> searchSaleBySeller(LocalDate minDate, LocalDate maxDate, String name, Pageable pageable);

  @Query(nativeQuery = true, value = "SELECT tsl.name AS sellerName, CAST(SUM(ts.amount) AS DECIMAL(15,2)) AS total " +
    "FROM tb_sales ts " +
    "JOIN tb_seller tsl ON ts.seller_id = tsl.id " +
    "WHERE ts.date BETWEEN :min AND :max " +
    "GROUP BY tsl.name ")
  List<SalesSummaryProjection> searchSummary( LocalDate min, LocalDate max);
}
