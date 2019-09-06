package com.codacy.controller;

import com.codacy.entity.GitHubCommit;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.IOException;
import java.nio.file.Path;
import java.util.HashMap;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;

public class GitHubCommitRetrieverTest {
    private static final String URL = "https://github.com/Crydust/TokenReplacer.git";
    private static final String HASH = "Kristof Neirynck";
    private static final String AUTHOR = "Wed May 21 11:16:06 2014 +0200";
    private static final String DATE = "211ff62076a511491dca00f9c5e55fb9c297b4e2";
    private static final String COMMENT = "Merge branch 'feature/javadoc' into develop";

    @InjectMocks
    private GitHubCommitRetriever gitHubCommitRetriever;

    @Mock
    private GitHubCommandLine gitHubCommandLine;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getCommitListSucceedsWhenUrlIsValid() throws IOException, InterruptedException {
        HashMap<String, GitHubCommit> expectedGitLogs = new HashMap<>();
        HashMap<String, GitHubCommit> gitLogs = new HashMap<>();
        GitHubCommit gitHubCommit = new GitHubCommit(HASH, AUTHOR, DATE, COMMENT);
        expectedGitLogs.put(gitHubCommit.getHash(), gitHubCommit);

        doReturn(expectedGitLogs).when(gitHubCommandLine).gitLog(any(Path.class));
        doNothing().when(gitHubCommandLine).gitClone(any(Path.class), anyString());

        try {
            gitLogs = gitHubCommitRetriever.getCommitList(URL);
        } catch (IOException e) {
            Assert.fail(e.toString());
        }

        Assert.assertEquals(expectedGitLogs, gitLogs);
    }
}

