package com.codacy.common;

import org.junit.After;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.Assert.assertTrue;

//TODO test exceptions
public class FolderManagerTest {
    private static final String FOLDER_NAME = "./testTempRepository";
    private static final Path PATH = Paths.get(FOLDER_NAME);


    @After
    public void cleanUp() throws IOException {
        Files.delete(PATH);
    }

    @Test
    public void folderIsCreatedWhenDoesNotExistTest() throws IOException {
        FolderManager.prepareWorkFolder(PATH);
        assertTrue(Files.exists(PATH));
    }

    @Test
    public void folderContentsAreDeletedWhenWorkingFolderAlreadyExists() throws IOException {
        FolderManager.prepareWorkFolder(PATH);
        //create dummy file
        File file = new File(PATH.toString() + "/file.txt");
        file.createNewFile();

        assertTrue(!isFolderEmpty(PATH.toFile()));

        FolderManager.prepareWorkFolder(PATH);
        assertTrue(isFolderEmpty(PATH.toFile()));
    }

    private boolean isFolderEmpty(File file) {
        if (file.isDirectory()) {
            return file.list().length <= 0;
        }
        return true;
    }

}
