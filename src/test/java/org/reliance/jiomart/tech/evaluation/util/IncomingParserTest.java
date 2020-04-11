package org.reliance.jiomart.tech.evaluation.util;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.reliance.jiomart.tech.evaluation.services.LastSeenTimeCalculatorService;

import java.time.ZonedDateTime;

import static org.junit.Assert.*;

public class IncomingParserTest {

    static IncomingParser incomingParser;
    static ZonedDateTime currentDateTime;

    @BeforeClass
    public static void beforeClassSetUp() {
        incomingParser = new IncomingParser();
    }

    @Before
    public void beforeEachMethodSetUp() {
        currentDateTime = ZonedDateTime.now();
    }

    @Test
    public void verify_validateUserProvidedCommand_method_when_provided_invalid_Dateformat() {

        assertFalse("Unable to handle invalid format data input", incomingParser.validateUserProvidedCommand("2015-5-5T10:15:30Z"));
        assertFalse("Unable to handle invalid format data input", incomingParser.validateUserProvidedCommand("2015-05-05T10:15:30+0000"));
        assertFalse("Unable to handle invalid format data input", incomingParser.validateUserProvidedCommand("2015-05-05T10:15:30"));
        assertFalse("Unable to handle invalid format data input", incomingParser.validateUserProvidedCommand("2015-05-05 10:15:30Z"));
        assertFalse("Unable to handle invalid format data input", incomingParser.validateUserProvidedCommand("20-05-05 10:15:30Z"));
        assertFalse("Unable to handle invalid format data input", incomingParser.validateUserProvidedCommand("2000-05-05T10:15:30+[Europe/Paris]"));
        assertFalse("Unable to handle invalid format data input", incomingParser.validateUserProvidedCommand("2020-05-05T10:15:30+01:00[Europe/Paris]"));
        currentDateTime = currentDateTime.plusMinutes(30);
        Assert.assertEquals("", false, incomingParser.validateUserProvidedCommand(currentDateTime.toString()));
    }

    @Test
    public void verify_validateUserProvidedCommand_method_when_provided_Valid_Dateformat() {

        assertTrue("Failed to validate correct DateTime.", incomingParser.validateUserProvidedCommand("2015-05-05T10:15:30+01:00[Europe/Paris]"));
        assertTrue("Failed to validate correct DateTime.", incomingParser.validateUserProvidedCommand("2015-05-05T10:15:30Z"));
        assertTrue("Failed to validate correct DateTime.", incomingParser.validateUserProvidedCommand("2015-05-05T10:15:30+01:00"));
    }
}