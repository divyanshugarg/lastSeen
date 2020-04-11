package org.reliance.jiomart.tech.evaluation.controller;

import org.reliance.jiomart.tech.evaluation.services.LastSeenTimeCalculatorService;
import org.reliance.jiomart.tech.evaluation.util.IncomingParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.DateTimeException;

public class UserInputRetriever {

    IncomingParser incomingParser;
    LastSeenTimeCalculatorService lastSeenTimeCalculatorService;

    public UserInputRetriever() {
        incomingParser = new IncomingParser();
        lastSeenTimeCalculatorService = new LastSeenTimeCalculatorService();
    }

    /*
        Retrieve input (i.e. DateTime object) provided by user and pass to Parser service class to interpret.
        Catching IOException, DateTimeException, IllegalArgumentException or ArithmeticException
     */
    public void getUserInputRetriever(String[] args) {
        System.out.println("Welcome to calculate 'Last Seen By' based on provided input.");
        System.out.println("Provide EXIT command anytime to quit the program.");
        System.out.println("System is ready to follow your input. Please provide Data-Time in ISO 8601 format. E.g. '2020-04-10T19:15:30+01:00', '2020-04-10T19:15:30+01:00[Europe/Paris]' or '2020-04-10T19:15:30Z'");
        // Interactive command-line. Run an infinite loop
        for (; ; ) {
            try {
                BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
                String inputString = bufferRead.readLine();
                if (inputString != null) {
                    if (inputString.equalsIgnoreCase("exit")) {
                        break;
                    } else if (inputString.isEmpty()) {
                        System.out.print("Waiting for input.");
                    } else {
                        // Pass the command to Parser class to interpret
                        if (incomingParser.validateUserProvidedCommand(inputString.trim())) {
                            lastSeenTimeCalculatorService.setLastSeenReportedMessage(incomingParser.getInputInZonedDateTimeFormat(inputString.trim()));
                            System.out.print(lastSeenTimeCalculatorService.getLastSeenReportedMessage());
                        } else {
                            // No action defined yet except logging the error.
                        }
                    }
                    System.out.println();
                }
            } catch (IOException | DateTimeException | IllegalArgumentException | ArithmeticException e) {
                System.out.println("Oops! Error in reading/interpret/calculating the input from console. Please refer F&Q");
                e.printStackTrace();
            }
        }
    }
}
