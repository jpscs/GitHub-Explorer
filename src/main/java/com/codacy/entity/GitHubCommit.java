package com.codacy.entity;

public class GitHubCommit {
    private String commitHash;
    private String author;
    private String date;
    private String comment;

    public GitHubCommit() {
    }

    public GitHubCommit(String commitHash, String author, String date, String comment) {
        this.commitHash = commitHash;
        this.author = author;
        this.date = date;
        this.comment = comment;
    }

    public String getCommitHash() {
        return commitHash;
    }

    public void setCommitHash(String commitHash) {
        this.commitHash = commitHash;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
