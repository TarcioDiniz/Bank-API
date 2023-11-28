package com.api.bank.configuration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.LoggerContext;

public class LogConfig {

    public static void setLogFile(String fileName) {
        // Set the system property for the log file name
        System.setProperty("logFileName", "logs/" + fileName + ".log");

        // Get the LoggerContext from LogManager
        LoggerContext context = (LoggerContext) LogManager.getContext(false);

        // Reconfigure the LoggerContext to apply the changes
        context.reconfigure();
    }


}
