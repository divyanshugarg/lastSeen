package org.reliance.jiomart.tech.evaluation;

import org.reliance.jiomart.tech.evaluation.util.InputcommandParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class LastSeenApplication {

    public static void main(String[] args) {
        InputcommandParser inputcommandParser = new InputcommandParser();
        switch (args.length) {
            case 0:
                System.out.println("Welcome to calculate 'Last Seen By' based on provided input.");
                System.out.println("Provide EXIT command anytime to quit the program.");
                System.out.println("System is ready to follow your input. Please provide Data-Time in ISO 8601 format (yyyy-MM-dd'T'HH:mm:ssXXX) 2011-12-03T10:15:30+01:00 ...");
                // Interactive command-line. Run an infinite loop
                for (; ; ) {
                    try {
                        BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
                        String inputString = bufferRead.readLine();
                        if (inputString != null) {
                            if (inputString.equalsIgnoreCase("exit")) {
                                break;
                            } else if (inputString.isEmpty()) {
                                System.out.println("Waiting for input.");
                            } else {
                                inputcommandParser.UserProvidedCommandsParser(inputString.trim());
                            }
                        }
                    } catch (IOException | IllegalArgumentException e) {
                        System.out.println("Oops! Error in reading the input from console.");
                        e.printStackTrace();
                    }
                }
                break;
            case 1:

                break;
            default:
                System.out.println("Invalid input. Usage: java -jar <jar_file_path> <input_file_path>");
        }
    }
}
