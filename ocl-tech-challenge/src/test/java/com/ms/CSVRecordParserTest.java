package com.ms;

import com.ms.parser.CSVRecordParser;
import com.ms.parser.InvalidRecordException;
import com.ms.parser.RecordParser;
import java.time.Duration;
import java.time.temporal.ChronoUnit;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class CSVRecordParserTest {

	private final RecordParser<ConstructionJob> recordParser = new CSVRecordParser();

	@Test
	void testParseRecord() {
		String record = "2343225,2345,us_east,RedTeam,ProjectApple,3445s";
		ConstructionJob actual = recordParser.parseRecord(record);
		var expected = new ConstructionJob("2343225", 2345, "us_east", "RedTeam", "ProjectApple",
				Duration.of(3445L, ChronoUnit.SECONDS));

		Assertions.assertEquals(expected, actual);
	}

	@Test
	void testParseRecordWithInvalidRecord() {
		String record = "2343225,2345,us_east,ProjectApple,3445s";
		Assertions.assertThrows(InvalidRecordException.class, () -> recordParser.parseRecord(record));
	}

	@Test
	void testParseRecordWithRecordHavingInvalidDuration() {
		String record = "2343225,2345,us_east,RedTeam,ProjectApple,344es";
		Assertions.assertThrows(RuntimeException.class, () -> recordParser.parseRecord(record));
	}
}