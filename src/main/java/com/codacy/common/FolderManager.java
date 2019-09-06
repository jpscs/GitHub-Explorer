package com.codacy.common;

import com.codacy.util.GitHubConstants;
import org.apache.commons.io.FileUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public final class FolderManager {
    private FolderManager() {
    }

    public static boolean prepareWorkFolder(final Path folderPath) throws IOException {
        if (Files.exists(folderPath)) {
            try {
                System.out.println("Directory already exists, cleaning it up...");
                FileUtils.cleanDirectory(new File(folderPath.toString()));
                return true;
            } catch (IOException e) {
                System.err.println("Failed to clean directory!");
                throw new ResponseStatusException(
                        HttpStatus.INTERNAL_SERVER_ERROR, GitHubConstants.REQUEST_FAILED_AND_CAUSED_AN_ERROR_ON_THE_SERVER, e);
            }
        } else {
            Files.createDirectory(folderPath);
            System.out.println("Directory was successfully created");
            return true;
        }
    }
}
