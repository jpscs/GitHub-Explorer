package com.codacy.controller;

import com.codacy.common.FolderManager;
import com.codacy.entites.GitHubCommit;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;


public class GitHubCommitRetriever {

    public HashMap<String, GitHubCommit> getCommitList(String url) throws IOException {
        String repositoryFolderName = "./tempRepository";
        Path path = Paths.get(repositoryFolderName);
        FolderManager.createWorkFolder(path);

        HashMap<String, GitHubCommit> gitLogs = new HashMap<>();
        return gitLogs;
    }
}
