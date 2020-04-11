package org.reliance.jiomart.tech.evaluation;

import org.reliance.jiomart.tech.evaluation.controller.UserInputRetriever;

public class LastSeenApplication {

    public static void main(String[] args) {
        UserInputRetriever userInputRetriever = new UserInputRetriever();
        userInputRetriever.getUserInputRetriever(args);
    }
}
