package com.devsuperior.dsmeta.dto;

import com.devsuperior.dsmeta.projections.SalesBySellerProjection;

import java.time.LocalDate;

public class SalesSellerDTO {
  private Long id;
  private Double amount;
  private LocalDate date;
  private String sellerName;

  public SalesSellerDTO() {};

  public SalesSellerDTO(Long id, Double amount, LocalDate date, String name) {
    this.id = id;
    this.amount = amount;
    this.date = date;
    this.sellerName = name;
  }

  public SalesSellerDTO(SalesBySellerProjection projection) {
    id = projection.getId();
    amount = projection.getAmount();
    date = projection.getDate();
    sellerName = projection.getSellerName();
  }

  public Long getId() {
    return id;
  }

  public Double getAmount() {
    return amount;
  }

  public LocalDate getDate() {
    return date;
  }

  public String getsellerName() {
    return sellerName;
  }

  @Override
  public String toString() {
    return "SaleSellerDTO{" +
      "id=" + id +
      ", amount=" + amount +
      ", date=" + date +
      ", name='" + sellerName + '\'' +
      '}';
  }
}
