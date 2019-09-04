package com.codacy.common;

import org.junit.After;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.Assert.assertTrue;

public class FolderManagerTest {
    private static final String FOLDER_NAME = "./testTempRepository";
    private static final Path PATH = Paths.get(FOLDER_NAME);


    @After
    public void cleanUp() throws IOException {
        Files.delete(PATH);
    }

    @Test
    public void folderIsCreatedWhenDoesNotExistTest() throws IOException {
        FolderManager.createWorkFolder(PATH);
        assertTrue(Files.exists(PATH));
    }

    @Test
    public void folderIsNotCreatedWIfItExistsTest() throws IOException {
        FolderManager.createWorkFolder(PATH);
        FolderManager.createWorkFolder(PATH);

        assertTrue(Files.exists(PATH));
    }

}
