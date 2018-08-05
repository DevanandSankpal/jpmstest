package com.ds.jpmc.sales.messageprocessor.service;

import java.util.List;

import com.ds.jpmc.sales.messageprocessor.model.Adjustment;
import com.ds.jpmc.sales.messageprocessor.model.Sale;

public interface ReportGenerator {

	public String generateSaleReport(List<Sale> salesData);
	public String generateAdjustmentReport(List<Adjustment> adjustmentData);
}
