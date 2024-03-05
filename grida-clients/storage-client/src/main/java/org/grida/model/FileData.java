package org.grida.model;

import lombok.Builder;

public class FileData {

    private static final String FILE_PATH_FORMAT = "%s/%s.%s";

    private final String directory;
    private final String fileName;
    private final String extension;

    @Builder
    public FileData(String directory, String fileName, String extension) {
        this.directory = directory;
        this.fileName = fileName;
        this.extension = extension;
    }

    public String getFilePath() {
        return String.format(FILE_PATH_FORMAT, directory, fileName, extension);
    }

    public String getExtension() {
        return extension;
    }
}
