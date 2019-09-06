package com.codacy.controller;

import com.codacy.entity.GitHubCommit;
import com.codacy.util.GitHubConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;

//TODO need to improve the error handling and returning messages to the user
@RestController
public class GitHubCommitController {
    @Autowired
    private GitHubCommitRetriever gitHubCommitRetriever;

    @GetMapping("/commits")
    HashMap<String, GitHubCommit> getCommitList(@RequestParam("gitHubUrl") String gitHubUrl) {
        HashMap<String, GitHubCommit> commitList;

        try {
            commitList = gitHubCommitRetriever.getCommitList(gitHubUrl);
        } catch (Exception e) {
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR, GitHubConstants.REQUEST_FAILED_AND_CAUSED_AN_ERROR_ON_THE_SERVER, e);
        }

        return commitList;
    }
}
