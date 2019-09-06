package com.codacy.controller;

import com.codacy.entity.GitHubCommit;
import org.kohsuke.github.GHCommit;
import org.kohsuke.github.GitHub;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

//TODO still some work to do to retrieve the necessary fields
@Component("gitHubApi")
public class GitHubApi {
    private static final int REPO_NAME_START = 5;

    public HashMap<String, GitHubCommit> gitCloneByApi(String url) {
        HashMap<String, GitHubCommit> commitHashMap = new HashMap<>();
        try {
            GitHub github;
            String repo = url.substring(url.indexOf(".com/") + REPO_NAME_START, url.indexOf(".git"));
            github = GitHub.connectAnonymously();
            List<GHCommit> ghCommitList = github.getRepository(repo).queryCommits().list().asList();
            commitHashMap = parseGHCommitToGitHubCommitList(ghCommitList);
            return commitHashMap;
        } catch (IOException e) {
            e.printStackTrace();
            return commitHashMap;
        }
    }

    private HashMap<String, GitHubCommit> parseGHCommitToGitHubCommitList(List<GHCommit> ghCommitList) {
        HashMap<String, GitHubCommit> gitHubCommitMap = new HashMap<>();
        for (GHCommit ghCommit : ghCommitList) {
            GitHubCommit gitHubCommit = parseGitLogFields(ghCommit);
            gitHubCommitMap.put(gitHubCommit.getHash(), gitHubCommit);
        }
        return gitHubCommitMap;
    }

    private GitHubCommit parseGitLogFields(GHCommit ghCommit) {
        GitHubCommit gitHubCommit = null;
        try {
            gitHubCommit = new GitHubCommit(ghCommit.getSHA1(),
                    ghCommit.getCommitDate().toString(),
                    ghCommit.getCommitDate().toString(),
                    ghCommit.getCommitDate().toString());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return gitHubCommit;
    }
}
