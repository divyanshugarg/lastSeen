package org.reliance.jiomart.tech.evaluation.util;

import org.reliance.jiomart.tech.evaluation.services.LastSeenTimeCalculatorService;

import java.time.Duration;
import java.time.ZonedDateTime;
import java.time.format.DateTimeParseException;

public class IncomingParser {

    LastSeenTimeCalculatorService lastSeenTimeCalculatorService;

    public IncomingParser() {
        lastSeenTimeCalculatorService = new LastSeenTimeCalculatorService();
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

    /**
     * @param dataTimeInputProvided
     * @return
     */
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

    /**
     * Verify Is provided ZonedDateTime instance is a future data time or not
     *
     * @param userProvidedDatetime
     * @return True if provided date represent a Future data time.
     */
    private boolean isFutureTimeStampProvided(ZonedDateTime userProvidedDatetime) {
        ZonedDateTime currentDateTime = ZonedDateTime.now();
        Duration timeDifference = Duration.between(userProvidedDatetime, currentDateTime);
        return timeDifference.isNegative() || timeDifference.isZero();
    }

    /**
     * Obtains an instance of {@code ZonedDateTime} from a text string such as
     * {@code 2007-12-03T10:15:30+01:00[Europe/Paris]}.
     * <p>
     * The string must represent a valid date-time and is parsed using
     * {@link java.time.format.DateTimeFormatter#ISO_ZONED_DATE_TIME}.
     *
     * @param dataTimeInputProvided the text to parse such as "2007-12-03T10:15:30+01:00[Europe/Paris]", not null
     * @return the parsed zoned date-time, not null
     * @throws DateTimeParseException if the text cannot be parsed
     */
    public ZonedDateTime getInputInZonedDateTimeFormat(String dataTimeInputProvided) {
        return ZonedDateTime.parse(dataTimeInputProvided);
    }

}
