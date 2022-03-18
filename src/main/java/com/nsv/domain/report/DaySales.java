package com.nsv.domain.report;

import java.time.Instant;

public record  DaySales(Long id, Instant closedDate,Double total, Double totalWithoutTaxes, Double totalRefund, Double owed ) { }