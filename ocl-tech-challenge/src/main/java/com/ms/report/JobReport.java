package com.ms.report;

import java.util.Map;
import java.util.Objects;
import java.util.Set;

public class JobReport {

	private final Map<Integer, Long> contractIdToUniqueCustomerCount;
	private final Map<String, Long> geoZoneToUniqueCustomersCount;
	private final Map<String, Set<String>> geoZoneUniqueCustomers;
	private final Map<String, Double> geoZoneToAvgBuildDuration;

	private JobReport(
			Map<Integer, Long> contractIdToUniqueCustomerCount, Map<String, Long> geoZoneToUniqueCustomersCount,
			Map<String, Set<String>> geoZoneUniqueCustomers, Map<String, Double> geoZoneToAvgBuildDuration) {
		this.contractIdToUniqueCustomerCount = contractIdToUniqueCustomerCount;
		this.geoZoneToUniqueCustomersCount = geoZoneToUniqueCustomersCount;
		this.geoZoneUniqueCustomers = geoZoneUniqueCustomers;
		this.geoZoneToAvgBuildDuration = geoZoneToAvgBuildDuration;
	}

	public Map<Integer, Long> getContractIdToUniqueCustomerCount() {
		return contractIdToUniqueCustomerCount;
	}

	public Map<String, Long> getGeoZoneToUniqueCustomersCount() {
		return geoZoneToUniqueCustomersCount;
	}

	public Map<String, Set<String>> getGeoZoneUniqueCustomers() {
		return geoZoneUniqueCustomers;
	}

	public Map<String, Double> getGeoZoneToAvgBuildDuration() {
		return geoZoneToAvgBuildDuration;
	}

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}
		if (object == null || getClass() != object.getClass()) {
			return false;
		}
		JobReport jobReport = (JobReport) object;
		return Objects.equals(contractIdToUniqueCustomerCount,
				jobReport.contractIdToUniqueCustomerCount) && Objects.equals(
				geoZoneToUniqueCustomersCount, jobReport.geoZoneToUniqueCustomersCount)
				&& Objects.equals(geoZoneUniqueCustomers, jobReport.geoZoneUniqueCustomers)
				&& Objects.equals(geoZoneToAvgBuildDuration, jobReport.geoZoneToAvgBuildDuration);
	}

	@Override
	public int hashCode() {
		return Objects.hash(contractIdToUniqueCustomerCount, geoZoneToUniqueCustomersCount,
				geoZoneUniqueCustomers, geoZoneToAvgBuildDuration);
	}

	public static JobReportBuilder builder(){
		return new JobReportBuilder();
	}

	public static class JobReportBuilder {

		private Map<Integer, Long> contractIdToUniqueCustomerCount;
		private Map<String, Long> geoZoneToUniqueCustomersCount;
		private Map<String, Set<String>> geoZoneUniqueCustomers;
		private Map<String, Double> geoZoneToAvgBuildDuration;

		public JobReportBuilder contractIdToUniqueCustomerCount(
				Map<Integer, Long> contractIdToUniqueCustomerCount) {
			this.contractIdToUniqueCustomerCount = contractIdToUniqueCustomerCount;
			return this;
		}

		public JobReportBuilder geoZoneToUniqueCustomersCount(
				Map<String, Long> geoZoneToUniqueCustomersCount) {
			this.geoZoneToUniqueCustomersCount = geoZoneToUniqueCustomersCount;
			return this;
		}

		public JobReportBuilder geoZoneUniqueCustomers(
				Map<String, Set<String>> geoZoneUniqueCustomers) {
			this.geoZoneUniqueCustomers = geoZoneUniqueCustomers;
			return this;
		}

		public JobReportBuilder geoZoneToAvgBuildDuration(
				Map<String, Double> geoZoneToAvgBuildDuration) {
			this.geoZoneToAvgBuildDuration = geoZoneToAvgBuildDuration;
			return this;
		}

		public JobReport build() {
			return new JobReport(this.contractIdToUniqueCustomerCount, this.geoZoneToUniqueCustomersCount,
					this.geoZoneUniqueCustomers, this.geoZoneToAvgBuildDuration);
		}
	}
}
