package com.devsuperior.dsmeta.repositories;

import com.devsuperior.dsmeta.projections.SalesBySellerProjection;
import com.devsuperior.dsmeta.projections.SalesSummaryProjection;
import org.springframework.data.jpa.repository.JpaRepository;

import com.devsuperior.dsmeta.entities.Sale;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface SaleRepository extends JpaRepository<Sale, Long> {

  @Query(nativeQuery = true, value = "SELECT ts.id, ts.date, ts.amount, tsl.name" +
    "FROM tb_sales ts" +
    "JOIN tb_seller tsl ON ts.seller_id = tsl.id" +
    "WHERE ts.date BETWEEN :min AND :max " +
    "AND UPPER(tsl.name) LIKE Upper(CONCAT('%',':name','%')")
  List<SalesBySellerProjection> searchSaleBySeller(LocalDate max, LocalDate min, String name);

  @Query(nativeQuery = true, value = "SELECT tsl.name AS sellerName, CAST(SUM(ts.amount) AS DECIMAL(15,2)) AS total " +
    "FROM tb_sales ts " +
    "JOIN tb_seller tsl ON ts.seller_id = tsl.id " +
    "WHERE ts.date BETWEEN :min AND :max " +
    "GROUP BY tsl.name ")
  List<SalesSummaryProjection> searchSummary( LocalDate min, LocalDate max);
}
