package org.reliance.jiomart.tech.evaluation.util;

import org.reliance.jiomart.tech.evaluation.services.LastSeenTimeCalculator;

import java.time.Duration;
import java.time.ZonedDateTime;
import java.time.format.DateTimeParseException;

public class IncomingParser {

    LastSeenTimeCalculator lastSeenTimeCalculator;

    public IncomingParser() {
        lastSeenTimeCalculator = new LastSeenTimeCalculator();
    }

    /*
        @param  dataTimeInputProvided:   provide Data-Time in ISO 8601 format.
        E.g. '2015-05-05T10:15:30+01:00', '2015-05-05T10:15:30+01:00[Europe/Paris]' or '2015-05-05T10:15:30Z'"
     */
    public boolean validateUserProvidedCommand(String dataTimeInputProvided) {
        if (isInputInCorrectFormat(dataTimeInputProvided)) {
            if (isFutureTimeStampProvided(ZonedDateTime.parse(dataTimeInputProvided))) {
                System.out.print("Provided Date-Time is greater than current time.");
                return false;
            } else {
                return true;
            }
        } else {
            return false;
        }
    }


    private boolean isInputInCorrectFormat(String dataTimeInputProvided) {
        try {
            ZonedDateTime.parse(dataTimeInputProvided);
            return true;
        } catch (DateTimeParseException ex) {
            System.out.println("Provided Data is not in correct format. Error message - " + ex.getLocalizedMessage());
            System.out.print("Please provide Data-Time in ISO 8601 format. E.g. '2020-04-10T19:15:30+01:00', '2020-04-10T19:15:30+01:00[Europe/Paris]' or '2020-04-10T19:15:30Z'");
            return false;
        }
    }

    private boolean isFutureTimeStampProvided(ZonedDateTime userProvidedDatetime) {
        ZonedDateTime currentDateTime = ZonedDateTime.now();
        Duration timeDifference = Duration.between(userProvidedDatetime, currentDateTime);
        return timeDifference.isNegative() || timeDifference.isZero();
    }

    public ZonedDateTime getInputInZonedDateTimeFormat(String dataTimeInputProvided) {
        return ZonedDateTime.parse(dataTimeInputProvided);
    }

}
