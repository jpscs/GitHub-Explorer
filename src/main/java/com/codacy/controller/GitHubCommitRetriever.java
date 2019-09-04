package com.codacy.controller;

import com.codacy.common.FolderManager;
import com.codacy.entity.GitHubCommit;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;

public class GitHubCommitRetriever {

    private static final String TEMP_REPOSITORY = "./tempRepository";
    private GitHubCommandLine gitHubCommandLine = new GitHubCommandLine();

    public HashMap<String, GitHubCommit> getCommitList(final String url) throws IOException, InterruptedException {
        String repositoryFolderName = TEMP_REPOSITORY;
        Path path = Paths.get(repositoryFolderName);
        FolderManager.createWorkFolder(path);

        gitHubCommandLine.gitClone(path, url);
        HashMap<String, GitHubCommit> gitLogs = gitHubCommandLine.gitLog(path);
        return gitLogs;
    }
}
