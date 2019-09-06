package com.codacy.entity;

public class GitHubCommit {
    private String hash;
    private String author;
    private String date;
    private String comment;

    public GitHubCommit() {
    }

    public GitHubCommit(String hash, String author, String date, String comment) {
        this.hash = hash;
        this.author = author;
        this.date = date;
        this.comment = comment;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
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
