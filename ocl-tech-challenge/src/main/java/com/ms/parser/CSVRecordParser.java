package com.ms.parser;

import com.ms.ConstructionJob;
import java.time.Duration;
import java.time.temporal.ChronoUnit;

public class CSVRecordParser implements RecordParser<ConstructionJob> {

	private static final String SEPARATOR = ",";

	@Override
	public ConstructionJob parseRecord(String jobRecord) {
		String[] columns = jobRecord.split(SEPARATOR);
		if (columns.length != 6) {
			throw new InvalidRecordException("Record has invalid number of fields!!");
		}
		String customerId = columns[0];
		Integer contractId = Integer.valueOf(columns[1]);
		String geoZone = columns[2];
		String teamCode = columns[3];
		String projectCode = columns[4];
		Duration buildDuration = Duration.of(
				Long.parseLong(columns[5].substring(0, columns[5].length() - 1)), ChronoUnit.SECONDS);
		return new ConstructionJob(customerId, contractId, geoZone, teamCode, projectCode,
				buildDuration);
	}
}
