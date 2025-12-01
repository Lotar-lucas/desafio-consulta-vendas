package com.devsuperior.dsmeta.dto;

import com.devsuperior.dsmeta.projections.SalesBySellerProjection;

import java.time.LocalDate;

public class SaleSellerDTO {
  private Long id;
  private Double amount;
  private LocalDate date;
  private String name;

  public SaleSellerDTO(Long id, Double amount, LocalDate date, String name) {
    this.id = id;
    this.amount = amount;
    this.date = date;
    this.name = name;
  }

  public SaleSellerDTO(SalesBySellerProjection projection) {
    id = projection.getId();
    amount = projection.getAmount();
    date = projection.getDate();
    name = projection.getName();
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

  public String getName() {
    return name;
  }

  @Override
  public String toString() {
    return "SaleSellerDTO{" +
      "id=" + id +
      ", amount=" + amount +
      ", date=" + date +
      ", name='" + name + '\'' +
      '}';
  }
}
