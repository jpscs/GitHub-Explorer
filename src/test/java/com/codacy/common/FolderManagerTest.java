package com.codacy.common;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.Assert.assertTrue;

public class FolderManagerTest {
    private final static String folderName = "./testTempRepository";
    private final static Path path = Paths.get(folderName);


    @After
    public void cleanUp() throws IOException {
        Files.delete(path);
    }

    @Test
    public void folderIsCreatedWhenDoesNotExistTest() throws IOException {
        FolderManager.createWorkFolder(path);
        assertTrue(Files.exists(path));
    }

    @Test
    public void folderIsNotCreatedWIfItExistsTest() throws IOException {
        FolderManager.createWorkFolder(path);
        FolderManager.createWorkFolder(path);

        assertTrue(Files.exists(path));
    }

}
