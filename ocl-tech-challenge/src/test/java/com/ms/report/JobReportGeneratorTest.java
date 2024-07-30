package com.ms.report;

import com.ms.ConstructionJob;
import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class JobReportGeneratorTest {

//	2343225,2345,us_east,RedTeam,ProjectApple,3445s
	private final ConstructionJob constructionJob1 = new ConstructionJob("2343225", 2345, "us_east",
			"RedTeam", "ProjectApple",
			Duration.of(3445L, ChronoUnit.SECONDS));

//	1223456,2345,us_west,BlueTeam,ProjectBanana,2211s
	private final ConstructionJob constructionJob2 = new ConstructionJob("1223456", 2345, "us_west",
			"BlueTeam", "ProjectBanana",
			Duration.of(2211L, ChronoUnit.SECONDS));

//	3244332,2346,eu_west,YellowTeam3,ProjectCarrot,4322s
	private final ConstructionJob constructionJob3 = new ConstructionJob("3244332", 2346, "eu_west",
			"YellowTeam3", "ProjectCarrot",
			Duration.of(4322L, ChronoUnit.SECONDS));

//	1233456,2345,us_west,BlueTeam,ProjectDate,2221s
	private final ConstructionJob constructionJob4 = new ConstructionJob("1233456", 2345, "us_west",
			"BlueTeam", "ProjectDate",
			Duration.of(2221L, ChronoUnit.SECONDS));

//	3244132,2346,eu_west,YellowTeam3,ProjectEgg,4122s
	private final ConstructionJob constructionJob5 = new ConstructionJob("3244132", 2346, "eu_west",
			"YellowTeam3", "ProjectEgg",
			Duration.of(4122L, ChronoUnit.SECONDS));

	private final List<ConstructionJob> constructionJobList = List.of(
			constructionJob1, constructionJob2, constructionJob3, constructionJob4, constructionJob5
	);

	private final Map<Integer, List<ConstructionJob>> contractIdJobMap = Map.of(
			2345, List.of(constructionJob1, constructionJob2, constructionJob4),
			2346, List.of(constructionJob3, constructionJob5)
			);

	private final Map<String, List<ConstructionJob>> geoZoneJobMap = Map.of(
			"us_east", List.of(constructionJob1),
			"us_west", List.of(constructionJob2, constructionJob4),
			"eu_west", List.of(constructionJob3, constructionJob5)
	);

	private final Map<String, Long> geoZoneUniqueCustomerCountReport = Map.of(
			"us_east", 1L,
			"us_west", 2L,
			"eu_west", 2L
	);

	private final Map<String, Set<String>> geoZoneUniqueCustomersReport = Map.of(
			"us_east", Set.of("2343225"),
			"us_west", Set.of("1233456", "1223456"),
			"eu_west", Set.of("3244132", "3244332")
	);

	private final Map<String, Double> geoZoneAvgBuildDurationReport = Map.of(
			"us_east", 3445d,
			"us_west", 2216d,
			"eu_west", 4222d
	);

	private final JobReportGenerator jobReportGenerator = new JobReportGenerator();
	private final Map<Integer, Long> contractIdUniqueCustomerCountReport = Map.of(
			2345, 3L,
			2346, 2L
	);

	@Test
	void generateReport() {
		var expected = JobReport.builder()
				.contractIdToUniqueCustomerCount(contractIdUniqueCustomerCountReport)
				.geoZoneToAvgBuildDuration(geoZoneAvgBuildDurationReport)
				.geoZoneToUniqueCustomersCount(geoZoneUniqueCustomerCountReport)
				.geoZoneUniqueCustomers(geoZoneUniqueCustomersReport)
				.build();
		var actual = jobReportGenerator.generateReport(constructionJobList);

		Assertions.assertEquals(expected, actual);
	}

	@Test
	void groupByContractId() {
		var expected = contractIdJobMap;
		var actual = jobReportGenerator.groupByContractId(constructionJobList);
		Assertions.assertEquals(expected, actual);
	}

	@Test
	void contractIdToUniqueCustomerCountReport() {
		var actual = jobReportGenerator.contractIdToUniqueCustomerCountReport(contractIdJobMap);
		Assertions.assertEquals(contractIdUniqueCustomerCountReport, actual);
	}

	@Test
	void groupByGeoZone() {
		var expected = geoZoneJobMap;
		var actual = jobReportGenerator.groupByGeoZone(constructionJobList);
		Assertions.assertEquals(expected, actual);
	}

	@Test
	void geoZoneToUniqueCustomersCountReport() {
		var actual = jobReportGenerator.geoZoneToUniqueCustomersCountReport(geoZoneJobMap);
		Assertions.assertEquals(geoZoneUniqueCustomerCountReport, actual);
	}

	@Test
	void geoZoneUniqueCustomersReport() {
		var actual = jobReportGenerator.geoZoneUniqueCustomersReport(geoZoneJobMap);
		Assertions.assertEquals(geoZoneUniqueCustomersReport, actual);
	}

	@Test
	void geoZoneToAvgBuildDurationReport() {
		var actual = jobReportGenerator.geoZoneToAvgBuildDurationReport(geoZoneJobMap);
		Assertions.assertEquals(geoZoneAvgBuildDurationReport, actual);
	}
}