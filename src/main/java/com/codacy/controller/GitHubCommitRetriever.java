package com.codacy.controller;

import com.codacy.common.FolderManager;
import com.codacy.entity.GitHubCommit;
import com.codacy.util.GitHubConstants;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;

@Component("gitHubCommitRetriever")
public class GitHubCommitRetriever {
    private static final String TEMP_REPOSITORY = "./tempRepository";

    @Autowired
    private GitHubCommandLine gitHubCommandLine;

    @Autowired
    private GitHubApi gitHubApi;

    public HashMap<String, GitHubCommit> getCommitList(final String url) throws IOException {
        HashMap<String, GitHubCommit> gitLogs = new HashMap<>();

        if (StringUtils.isNotBlank(url)) {
            gitLogs = getCommitListFromGitHub(url);
        }

        return gitLogs;
    }

    private HashMap<String, GitHubCommit> getCommitListFromGitHub(final String url) {
        HashMap<String, GitHubCommit> gitLogs = new HashMap<>();
        //gitLogs = gitHubApi.gitCloneByApi(url);

        // if API request fails use the command line to retrieve commit logs
        if (gitLogs.isEmpty()) {
            try {
                Path path = Paths.get(TEMP_REPOSITORY);
                FolderManager.prepareWorkFolder(path);

                gitHubCommandLine.gitClone(path, url);
                gitLogs = gitHubCommandLine.gitLog(path);
            } catch (IOException e) {
                throw new ResponseStatusException(
                        HttpStatus.INTERNAL_SERVER_ERROR, GitHubConstants.REQUEST_FAILED_AND_CAUSED_AN_ERROR_ON_THE_SERVER, e);
            } catch (InterruptedException e) {
                throw new ResponseStatusException(
                        HttpStatus.INTERNAL_SERVER_ERROR, GitHubConstants.REQUEST_FAILED_AND_CAUSED_AN_ERROR_ON_THE_SERVER, e);
            }
        }

        return gitLogs;
    }
}
