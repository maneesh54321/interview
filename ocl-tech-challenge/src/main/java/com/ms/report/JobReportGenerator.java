package com.ms.report;

import com.ms.ConstructionJob;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.stream.Collectors;

public class JobReportGenerator {

	/**
	 * generates Job Report based on the provided job records
	 *
	 * @param constructionJobs job records
	 * @return JobReport
	 */
	public JobReport generateReport(List<ConstructionJob> constructionJobs) {

		Map<String, List<ConstructionJob>> geoZoneToJobMap = groupByGeoZone(constructionJobs);

		Map<Integer, List<ConstructionJob>> contractIdJobMap = groupByContractId(constructionJobs);

		return JobReport.builder()
				.contractIdToUniqueCustomerCount(contractIdToUniqueCustomerCountReport(contractIdJobMap))
				.geoZoneToUniqueCustomersCount(geoZoneToUniqueCustomersCountReport(geoZoneToJobMap))
				.geoZoneToAvgBuildDuration(geoZoneToAvgBuildDurationReport(geoZoneToJobMap))
				.geoZoneUniqueCustomers(geoZoneUniqueCustomersReport(geoZoneToJobMap))
				.build();
	}


	/**
	 *
	 * Note: Exposed this for unit testing, With PowerMockito, it can be made private and unit tested
	 *
	 * @param constructionJobs
	 * @return
	 */
	public Map<Integer, List<ConstructionJob>> groupByContractId(
			List<ConstructionJob> constructionJobs) {
		return constructionJobs.stream().collect(Collectors.groupingBy(ConstructionJob::contractId));
	}

	/**
	 *
	 * Note: Exposed this for unit testing, With PowerMockito, it can be made private and unit tested
	 *
	 * @param contractIdJobMap
	 * @return
	 */
	public Map<Integer, Long> contractIdToUniqueCustomerCountReport(
			Map<Integer, List<ConstructionJob>> contractIdJobMap) {
		return contractIdJobMap.entrySet().stream()
				.collect(Collectors.toMap(
						Entry::getKey,
						entry -> entry.getValue().stream().map(ConstructionJob::customerId).distinct().count()
				));
	}

	/**
	 *
	 * Note: Exposed this for unit testing, With PowerMockito, it can be made private and unit tested
	 *
	 * @param constructionJobs
	 * @return
	 */
	public Map<String, List<ConstructionJob>> groupByGeoZone(
			List<ConstructionJob> constructionJobs) {
		return constructionJobs.stream().collect(Collectors.groupingBy(ConstructionJob::geoZone));
	}

	/**
	 *
	 * Note: Exposed this for unit testing, With PowerMockito, it can be made private and unit tested
	 *
	 * @param geoZoneToJobMap
	 * @return
	 */
	public Map<String, Long> geoZoneToUniqueCustomersCountReport(
			Map<String, List<ConstructionJob>> geoZoneToJobMap) {
		return geoZoneToJobMap.entrySet().stream()
				.collect(
						Collectors.toMap(
								Entry::getKey,
								entry -> entry.getValue().stream().map(ConstructionJob::customerId).distinct()
										.count()
						));
	}

	/**
	 *
	 * Note: Exposed this for unit testing, With PowerMockito, it can be made private and unit tested
	 *
	 * @param geoZoneToJobMap
	 * @return
	 */
	public Map<String, Set<String>> geoZoneUniqueCustomersReport(Map<String, List<ConstructionJob>> geoZoneToJobMap) {
		return geoZoneToJobMap.entrySet().stream()
				.collect(
						Collectors.toMap(
								Entry::getKey,
								entry -> entry.getValue().stream().map(ConstructionJob::customerId)
										.collect(Collectors.toSet())
						));
	}

	/**
	 *
	 * Note: Exposed this for unit testing, With PowerMockito, it can be made private and unit tested
	 *
	 * @param geoZoneToJobMap
	 * @return
	 */
	public Map<String, Double> geoZoneToAvgBuildDurationReport(
			Map<String, List<ConstructionJob>> geoZoneToJobMap) {
		return geoZoneToJobMap.entrySet().stream()
				.collect(Collectors.toMap(
						Entry::getKey,
						entry -> entry.getValue().stream()
								.mapToInt(job -> Math.toIntExact(job.buildDuration().get(ChronoUnit.SECONDS)))
								.average().orElse(Integer.MIN_VALUE)
				));
	}

}
