package org.reliance.jiomart.tech.evaluation.services;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;

public class LastSeenTimeCalculatorServiceTest {

    static LastSeenTimeCalculatorService lastSeenTimeCalculatorService;
    static ZonedDateTime currentDateTime;

    @BeforeClass
    public static void beforeClassSetUp() {
        lastSeenTimeCalculatorService = new LastSeenTimeCalculatorService();
    }

    @Before
    public void beforeEachMethodSetUp() {
        currentDateTime = ZonedDateTime.now();
    }

    @Test
    public void verify_SetLastSeenTimeCalculator_Method_Handle_Null_Input() {
        try {
            lastSeenTimeCalculatorService.setLastSeenReportedMessage(null);
        } catch (Exception e) {
            Assert.fail("Failed to handle Null input");
        }
    }

    @Test
    public void verify_SetLastSeenTimeCalculator_Method_When_Pass_Future_Date() {
        try {
            currentDateTime.plusMinutes(2);
            lastSeenTimeCalculatorService.setLastSeenReportedMessage(currentDateTime);
            Assert.fail("Excepted Exception when user provide future Timestamp.");
        } catch (Exception e) {
            Assert.assertTrue("Failed to handle Future Date as Excepted.", (e instanceof IllegalArgumentException));
        }
    }

    @Test
    public void verify_SetLastSeenTimeCalculator_Method_When_provide_Current_Time() {
        try {
            currentDateTime = currentDateTime.minusSeconds(10);
            lastSeenTimeCalculatorService.setLastSeenReportedMessage(currentDateTime);
            Assert.assertEquals("Excepted Exception when user provide current Timestamp.", "online", lastSeenTimeCalculatorService.getLastSeenReportedMessage());

        } catch (Exception e) {
            Assert.fail("SetLastSeenTimeCalculator failed to handle valid Input. Exception message : " + e);
        }
    }

    @Test
    public void verify_getLastSeenTimeGap_Method_When_provide_Few_Years_Before_Passed_Date() {
        try {
            lastSeenTimeCalculatorService.setReportedMessageFormat("Last seen %s");
            currentDateTime = currentDateTime.minus(1, ChronoUnit.YEARS);
            Thread.sleep(10);
            lastSeenTimeCalculatorService.setLastSeenReportedMessage(currentDateTime);
            Assert.assertEquals("Excepted Exception when user provide future Timestamp.", "Last seen 1 years", lastSeenTimeCalculatorService.getLastSeenReportedMessage());
        } catch (Exception e) {
            Assert.fail("Failed to validate when provide passed date more than year. Exception " + e);
        }
    }

    @Test
    public void verify_getLastSeenTimeGap_Method_When_provide_Passed_Few_Months_Before_Passed_Date() {
        try {
            lastSeenTimeCalculatorService.setReportedMessageFormat("Last seen %s");
            currentDateTime = currentDateTime.minus(1, ChronoUnit.MONTHS);
            Thread.sleep(10);
            lastSeenTimeCalculatorService.setLastSeenReportedMessage(currentDateTime);
            Assert.assertEquals("Excepted Exception when user provide future Timestamp.", "Last seen 1 months", lastSeenTimeCalculatorService.getLastSeenReportedMessage());
        } catch (Exception e) {
            Assert.fail("Failed to validate when provide passed date more than year. Exception " + e);
        }
    }

    @Test
    public void verify_getLastSeenTimeGap_Method_When_provide_Few_Days_Before_Passed_Date() {
        try {
            lastSeenTimeCalculatorService.setReportedMessageFormat("Last seen %s");
            currentDateTime = currentDateTime.minus(1, ChronoUnit.DAYS);
            Thread.sleep(10);
            lastSeenTimeCalculatorService.setLastSeenReportedMessage(currentDateTime);
            Assert.assertEquals("Excepted Exception when user provide future Timestamp.", "Last seen 1 days", lastSeenTimeCalculatorService.getLastSeenReportedMessage());
        } catch (Exception e) {
            Assert.fail("Failed to validate when provide passed date more than year. Exception " + e);
        }
    }

    @Test
    public void verify_getLastSeenTimeGap_Method_When_provide_Few_Hours_Before_Passed_Date() {
        try {
            lastSeenTimeCalculatorService.setReportedMessageFormat("Last seen %s");
            currentDateTime = currentDateTime.minus(2, ChronoUnit.HOURS);
            Thread.sleep(10);
            lastSeenTimeCalculatorService.setLastSeenReportedMessage(currentDateTime);
            Assert.assertEquals("Excepted Exception when user provide future Timestamp.", "Last seen 2 hours", lastSeenTimeCalculatorService.getLastSeenReportedMessage());
        } catch (Exception e) {
            Assert.fail("Failed to validate when provide passed date more than year. Exception " + e);
        }
    }

    @Test
    public void verify_getLastSeenTimeGap_Method_When_provide_Passed_Few_Minutes_Before_Passed_Date() {
        try {
            lastSeenTimeCalculatorService.setReportedMessageFormat("Last seen %s");
            currentDateTime = currentDateTime.minus(40, ChronoUnit.MINUTES);
            Thread.sleep(10);
            lastSeenTimeCalculatorService.setLastSeenReportedMessage(currentDateTime);
            Assert.assertEquals("Excepted Exception when user provide future Timestamp.", "Last seen 40 minutes", lastSeenTimeCalculatorService.getLastSeenReportedMessage());
        } catch (Exception e) {
            Assert.fail("Failed to validate when provide passed date more than year. Exception " + e);
        }
    }
}