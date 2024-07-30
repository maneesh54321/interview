package com.ms;

import com.ms.parser.CSVRecordParser;
import com.ms.parser.RecordParser;
import com.ms.report.JobReport;
import com.ms.report.JobReportGenerator;
import com.ms.report.printer.BasicReportPrinter;
import com.ms.report.printer.ReportPrinter;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class Main {

	private static final String input = """
			2343225,2345,us_east,RedTeam,ProjectApple,3445s
			1223456,2345,us_west,BlueTeam,ProjectBanana,2211s
			3244332,2346,eu_west,YellowTeam3,ProjectCarrot,4322s
			1233456,2345,us_west,BlueTeam,ProjectDate,2221s
			3244132,2346,eu_west,YellowTeam3,ProjectEgg,4122s
			""";

	public static void main(String[] args) throws IOException {

		RecordParser<ConstructionJob> jobRecordParser = new CSVRecordParser();
		List<ConstructionJob> jobs = input.lines().map(jobRecordParser::parseRecord).toList();

		var jobReportGenerator = new JobReportGenerator();
		JobReport jobReport = jobReportGenerator.generateReport(jobs);

		ReportPrinter reportPrinter = new BasicReportPrinter();
		try (InputStream report = reportPrinter.print(jobReport)) {
			report.transferTo(System.out);
		}
	}
}