package com.codacy.controller;


import com.codacy.common.CommandLineHandler;
import com.codacy.entity.GitHubCommit;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class GitHubCommandLine extends CommandLineHandler {

    private static final int HASH_INDEX = 0;
    private static final int AUTHOR_INDEX = 1;
    private static final int DATE_INDEX = 2;
    private static final int COMMENT_INDEX = 3;
    private static final String GIT = "git";
    private static final String LOG_COMMAND = "log";
    private static final String PRETTY_FORMAT = "--pretty=format:\"%H,%an,%ad,%s\"";
    private static final String REGEX_SPLIT_COMMIT = "\\s*,\\s*";
    private static final String CLONE = "clone";

    public void gitClone(final Path directory, final String originUrl) throws IOException, InterruptedException {
        runCommand(directory.getParent(), GIT, CLONE, originUrl, directory.getFileName().toString());
    }

    public HashMap<String, GitHubCommit> gitLog(final Path directory) throws IOException, InterruptedException {
        runCommand(directory, GIT, LOG_COMMAND, PRETTY_FORMAT);
        return parseCommitLogsToGitHubCommitList(getOutputGobbler().getLogLines());
    }

    private HashMap<String, GitHubCommit> parseCommitLogsToGitHubCommitList(final List<String> logLines) {
        HashMap<String, GitHubCommit> gitHubCommitMap = new HashMap<>();
        for (String line : logLines) {
            GitHubCommit gitHubCommit = parseGitLogFields(line);
            gitHubCommitMap.put(gitHubCommit.getCommitHash(), gitHubCommit);
        }
        return gitHubCommitMap;
    }

    private GitHubCommit parseGitLogFields(final String logLine) {
        List<String> commitValues = Arrays.asList(logLine.split(REGEX_SPLIT_COMMIT));
        GitHubCommit gitHubCommit = new GitHubCommit(commitValues.get(HASH_INDEX),
                                                commitValues.get(AUTHOR_INDEX),
                                                commitValues.get(DATE_INDEX),
                                                commitValues.get(COMMENT_INDEX));
        return gitHubCommit;
    }
}
