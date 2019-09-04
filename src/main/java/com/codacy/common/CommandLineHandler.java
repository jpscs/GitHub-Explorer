package com.codacy.common;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;

public abstract class CommandLineHandler {
    private static final String DIRECTORY = "directory";
    private static final String STREAM_ERROR = "ERROR";
    private static final String STREAM_OUTPUT = "OUTPUT";
    private static final String GIT_COMMAND_LOG = "log";

    private StreamGobbler outputGobbler;

    protected void runCommand(final Path directory, final String... command) throws IOException, InterruptedException {
        Objects.requireNonNull(directory, DIRECTORY);
        if (!Files.exists(directory)) {
            throw new RuntimeException("can't run command in non-existing directory '" + directory + "'");
        }
        ProcessBuilder processBuilder = new ProcessBuilder()
                .command(command)
                .directory(directory.toFile());

        Process process = processBuilder.start();

        // Use StreamGobbler to handle errors from the execution of command
        StreamGobbler errorGobbler = new StreamGobbler(process.getErrorStream(), STREAM_ERROR);
        //Use StreamGobbler to handle output resulting from the command line execution
        outputGobbler = new StreamGobbler(process.getInputStream(), STREAM_OUTPUT);
        outputGobbler.setLog(command[1].equals(GIT_COMMAND_LOG));

        outputGobbler.start();
        errorGobbler.start();

        int exitCode = process.waitFor();
        errorGobbler.join();
        outputGobbler.join();

        if (exitCode != 0) {
            throw new AssertionError(String.format("runCommand returned %d", exitCode));
        }
    }

    public StreamGobbler getOutputGobbler() {
        return outputGobbler;
    }
}
