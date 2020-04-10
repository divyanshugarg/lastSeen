package org.reliance.jiomart.tech.evaluation.util;

import org.reliance.jiomart.tech.evaluation.services.LastSeenTimeCalculator;

import java.time.ZonedDateTime;
import java.time.format.DateTimeParseException;

public class InputcommandParser {

    LastSeenTimeCalculator lastSeenTimeCalculator;


    public void InputcommandParser() {
        lastSeenTimeCalculator = new LastSeenTimeCalculator();
    }

    public String UserProvidedCommandsParser(String dataTimeInputProvided) {

        try {
            ZonedDateTime zonedDateTime = ZonedDateTime.parse("2015-05-05T10:15:30+01:00[Europe/Paris]");
            zonedDateTime = ZonedDateTime.parse("2015-05-05T10:15:30+01:00");
            zonedDateTime = ZonedDateTime.parse("2015-05-05T10:15:30Z");
            zonedDateTime = ZonedDateTime.parse("2015-05-05T10:15:30+5000");
            zonedDateTime = ZonedDateTime.parse(dataTimeInputProvided);
            return lastSeenTimeCalculator.calculateTimeDifference(zonedDateTime);
        } catch (DateTimeParseException ex) {
            return "Provided Data is not in correct format. Error message - " + ex.getParsedString();
        }

    }
}
