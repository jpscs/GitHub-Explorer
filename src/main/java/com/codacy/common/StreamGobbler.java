package com.codacy.common;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class StreamGobbler extends Thread {
    private final InputStream is;
    private final String type;
    private StringBuilder executionOutput = new StringBuilder();
    private List<String> logLines = new ArrayList<>();
    private boolean isLog;

    public StreamGobbler(final InputStream is, final String type) {
        this.is = is;
        this.type = type;
    }

    @Override
    public void run() {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(is));) {
            String line;
            while ((line = br.readLine()) != null) {
                storeOutputIfLog(line);
                System.out.println(type + "> " + line);
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    private void storeOutputIfLog(final String outputLine) {
        if (isLog) {
            logLines.add(outputLine);
        }
    }

    public StringBuilder getExecutionOutput() {
        return executionOutput;
    }

    public List<String> getLogLines() {
        return logLines;
    }

    public boolean isLog() {
        return isLog;
    }

    public void setLog(boolean log) {
        isLog = log;
    }
}
