package com.ms.report.printer;

import com.ms.report.JobReport;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.Set;

public class BasicReportPrinter implements ReportPrinter {

	private static final String LINE_BREAK = "\n";

	@Override
	public InputStream print(JobReport jobReport) throws IOException {
		var outputStream = new ByteArrayOutputStream();
		var result = new StringBuilder();
		Map<Integer, Long> contractIdToUniqueCustomerCount = jobReport.getContractIdToUniqueCustomerCount();
		if(contractIdToUniqueCustomerCount != null) {
			result.append("The number of unique customerId for each contractId");
			result.append(LINE_BREAK);
			result.append(contractIdToUniqueCustomerCount);
			result.append(LINE_BREAK);
			result.append(LINE_BREAK);
		}
		Map<String, Long> geoZoneToUniqueCustomersCount = jobReport.getGeoZoneToUniqueCustomersCount();
		if(geoZoneToUniqueCustomersCount != null) {
			result.append("The number of unique customerId for each geozone");
			result.append(LINE_BREAK);
			result.append(geoZoneToUniqueCustomersCount);
			result.append(LINE_BREAK);
			result.append(LINE_BREAK);
		}
		Map<String, Set<String>> geoZoneUniqueCustomers = jobReport.getGeoZoneUniqueCustomers();
		if(geoZoneUniqueCustomers != null) {
			result.append("The list of unique customerId for each geozone");
			result.append(LINE_BREAK);
			result.append(geoZoneUniqueCustomers);
			result.append(LINE_BREAK);
			result.append(LINE_BREAK);
		}
		Map<String, Double> geoZoneToAvgBuildDuration = jobReport.getGeoZoneToAvgBuildDuration();
		if(geoZoneToAvgBuildDuration != null) {
			result.append("The average build duration for each geozone");
			result.append(LINE_BREAK);
			result.append(geoZoneToAvgBuildDuration);
			result.append(LINE_BREAK);
			result.append(LINE_BREAK);
		}
		outputStream.write(result.toString().getBytes(StandardCharsets.UTF_8));
		return new ByteArrayInputStream(outputStream.toByteArray());
	}
}
