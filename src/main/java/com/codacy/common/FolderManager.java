package com.codacy.common;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public final class FolderManager {
    private FolderManager() {
    }

    public static void createWorkFolder(final Path folderPath) throws IOException {
        if (!Files.exists(folderPath)) {
            Files.createDirectory(folderPath);
            System.out.println("Directory was successfully created");
        } else {
            System.out.println("Directory already exists");
        }
    }
}
