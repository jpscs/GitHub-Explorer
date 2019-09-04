package com.codacy.controller;

import com.codacy.entites.GitHubCommit;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.HashMap;

public class GitHubCommitRetrieverTest {

    private static final String URL = "https://github.com/Crydust/TokenReplacer.git";
    GitHubCommitRetriever gitHubCommitRetriever;

    @Before
    public void setUp() {
        gitHubCommitRetriever = new GitHubCommitRetriever();
    }

    @Test
    public void getCommitListSucceedsWhenUrlIsValid() {
        HashMap<String, GitHubCommit> gitLogs = new HashMap<>();
        try {
            gitLogs = gitHubCommitRetriever.getCommitList(URL);
        } catch(IOException e) {
            Assert.fail(e.toString());
        }
        Assert.assertNotNull(gitLogs);
    }
}

