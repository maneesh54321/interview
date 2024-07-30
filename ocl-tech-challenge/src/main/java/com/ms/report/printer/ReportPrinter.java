package com.ms.report.printer;

import com.ms.report.JobReport;
import java.io.IOException;
import java.io.InputStream;

public interface ReportPrinter {
	InputStream print(JobReport jobReport) throws IOException;
}
