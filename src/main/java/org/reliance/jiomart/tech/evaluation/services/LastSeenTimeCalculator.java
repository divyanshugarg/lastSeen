package org.reliance.jiomart.tech.evaluation.services;

import java.time.*;
import java.time.temporal.ChronoUnit;

public class LastSeenTimeCalculator {

    public String calculateTimeDifference(ZonedDateTime lastSeenTime) {

        LocalDateTime currentDateTime = LocalDateTime.now();
        LocalDateTime lastSeenDateTime = lastSeenTime.toLocalDateTime();

        ChronoUnit.YEARS.between(lastSeenDateTime, currentDateTime);
        try {
            if (Duration.between(lastSeenDateTime, currentDateTime).isNegative()) {

            }
        } catch (DateTimeException | ArithmeticException e) {
            return "Time Difference calculation is out of range.";
        }


//TODO
        return "TO-DO";
    }

    private void getReportedMessage() {


    }


}
