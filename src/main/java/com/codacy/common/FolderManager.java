package com.codacy.common;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public final class FolderManager {
    private FolderManager() {
    }

    public static boolean createWorkFolder(final Path folderPath) throws IOException {
        if (Files.exists(folderPath)) {
            try {
                System.out.println("Directory already exists, cleaning it up...");
                FileUtils.cleanDirectory(new File(folderPath.toString()));
                return true;
            } catch (IOException ex) {
                System.err.println("Failed to clean directory!");
                return false;
            }
        } else {
            Files.createDirectory(folderPath);
            System.out.println("Directory was successfully created");
            return true;
        }
    }
}
