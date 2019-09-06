package com.codacy.controller;

import com.codacy.entity.GitHubCommit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.HashMap;

//TODO need to improve the error handling and returning messages to the user
@RestController
public class GitHubCommitController {
    @Autowired
    private GitHubCommitRetriever gitHubCommitRetriever;

    @GetMapping("/commits")
    HashMap<String, GitHubCommit> getCommitList(@RequestParam("gitHubUrl") String gitHubUrl) {
        HashMap<String, GitHubCommit> commitList = new HashMap<>();

        try {
            commitList = gitHubCommitRetriever.getCommitList(gitHubUrl);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return commitList;
    }
}
