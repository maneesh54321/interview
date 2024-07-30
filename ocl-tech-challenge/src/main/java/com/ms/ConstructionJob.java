package com.ms;

import java.time.Duration;

public record ConstructionJob(String customerId, Integer contractId, String geoZone, String teamCode,
                              String projectCode, Duration buildDuration) {
}
