package com.codacy.controller;

import com.codacy.entity.GitHubCommit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.HashMap;

@RestController
public class GitHubCommitController {
    @Autowired
    private GitHubCommitRetriever gitHubCommitRetriever;

    @GetMapping("/commits")
    HashMap<String, GitHubCommit> getCommitList(@RequestParam("gitHubUrl") String gitHubUrl) {
        try {
            return gitHubCommitRetriever.getCommitList(gitHubUrl);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        HashMap<String, GitHubCommit> xpto = new HashMap<>();
        return xpto;
    }
}
