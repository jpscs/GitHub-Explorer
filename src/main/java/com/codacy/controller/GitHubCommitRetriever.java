package com.codacy.controller;

import com.codacy.common.FolderManager;
import com.codacy.entity.GitHubCommit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;

@Component("gitHubCommitRetriever")
public class GitHubCommitRetriever {
    private static final String TEMP_REPOSITORY = "./tempRepository";

    @Autowired
    private GitHubCommandLine gitHubCommandLine;

    public HashMap<String, GitHubCommit> getCommitList(final String url) throws IOException, InterruptedException {
        String repositoryFolderName = TEMP_REPOSITORY;
        Path path = Paths.get(repositoryFolderName);
        FolderManager.createWorkFolder(path);

        gitHubCommandLine.gitClone(path, url);
        HashMap<String, GitHubCommit> gitLogs = gitHubCommandLine.gitLog(path);
        return gitLogs;
    }
}
