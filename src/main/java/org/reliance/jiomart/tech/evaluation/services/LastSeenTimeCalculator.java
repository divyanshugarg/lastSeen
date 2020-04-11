package org.reliance.jiomart.tech.evaluation.services;

import java.time.*;
import java.time.temporal.ChronoUnit;

public class LastSeenTimeCalculator {

    public void setLastSeenTimeMessage(ZonedDateTime lastSeenTime) {
        long timeGapInSecond = getTimeDifferenceInSecond(lastSeenTime);
        if (timeGapInSecond <= 0) {
            System.out.println("Please provide Past Timestamp to calculate Last Seen Time Message.");
        } else {
            String reportedMessageFormat = "Last seen %s ago.";
            String gap = getLastSeenTimeGap(lastSeenTime);
            if (gap.equalsIgnoreCase("online")) {
                System.out.print(gap);
            } else {
                System.out.printf(reportedMessageFormat, getLastSeenTimeGap(lastSeenTime));
            }
        }
    }

    private Long getTimeDifferenceInSecond(ZonedDateTime lastSeenTime) {
        ZonedDateTime currentDateTime = ZonedDateTime.now();
        Duration timeGapFromLastSeen = Duration.between(lastSeenTime, currentDateTime);
        return timeGapFromLastSeen.getSeconds();
    }

    /*
         |   Last Activity Time  | Reported as             |
         |:---------------------:|-------------------------|
         |  Greater than 1 Year  | Last seen # years ago   |
         | > 1 Month & < 1 Year  | Last seen # months ago  |
         | > 1 Day & < 1 Month  | Last seen # days ago    |
         | > 1 Hour & < 1 Day    | Last seen # hours ago   |
         | > 1 Minute & < 1 Hour | Last seen # minutes ago |
     */
    private String getLastSeenTimeGap(ZonedDateTime lastSeenTime) {
        try {
            ZonedDateTime currentDateTime = ZonedDateTime.now();
            int divisor;    // E.g. Assuming average days in a year is 365. divisor = 365*24*60*60; Another approach if don't have library support.
            long quotient;

            // Case 1: Greater than 1 Year
            quotient = ChronoUnit.YEARS.between(lastSeenTime, currentDateTime);
            String reportedMessageFormat = "Last seen %s %s ago";
            if (quotient > 0) {
                return quotient + " years";
            }

            // Case 2:  > 1 Month & < 1 Year
            quotient = ChronoUnit.MONTHS.between(lastSeenTime, currentDateTime);
            //No need to check less than 12.
            if (quotient > 0) {
                return quotient + " months";
            }

            // Case 3 :  > 1 Day & < 1 Month
            quotient = ChronoUnit.DAYS.between(lastSeenTime, currentDateTime);
            if (quotient > 0) {
                return quotient + " days";
            }

            // Case 4: > 1 Hour & < 1 Day
            quotient = ChronoUnit.HOURS.between(lastSeenTime, currentDateTime);
            if (quotient > 0) {
                return quotient + " hours";
            }

            // Case 5: > 1 Minute & < 1 Hour
            quotient = ChronoUnit.MINUTES.between(lastSeenTime, currentDateTime);
            if (quotient > 0) {
                return quotient + " minutes";
            } else {
                return "online";
            }
        } catch (Exception ex) {
            System.err.println("Error calculating the 'Last Seen Time'. Refer F&Q. Exception: " + ex.getLocalizedMessage());
            throw ex;
        }
    }

}
