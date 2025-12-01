package com.devsuperior.dsmeta.projections;

import java.time.LocalDate;

public interface SalesSummaryProjection {
    String getSellerName();
    Double getTotal();
}
