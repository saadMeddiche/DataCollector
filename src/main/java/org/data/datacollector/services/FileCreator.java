package org.data.datacollector.services;

import java.io.File;

public class FileCreator {

    public static void createFolderIfNotExists(String folderPath) {
        File folder = new File(folderPath);
        if (!folder.exists()) {
            folder.mkdirs();
        }
    }

    public static void createFileIfNotExists(String filePath) {
        File file = new File(filePath);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static void createFileIfNotExists(String folderPath, String fileName) {
        createFolderIfNotExists(folderPath);
        createFileIfNotExists(String.format("%s/%s", folderPath, fileName));
    }
}
