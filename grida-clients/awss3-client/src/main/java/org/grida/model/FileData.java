package org.grida.model;

import lombok.Builder;
import org.grida.exception.AwsS3ClientException;
import org.grida.exception.AwsS3ClientErrorCode;

public class FileData {

    private static final String DIRECTORY_DELIMITER = "/";
    private static final String DIRECTORY_FORMAT = "/dir1/dir2";
    private static final String FILE_PATH_FORMAT = "%s/%s.%s";

    private final String directory;
    private final String fileName;
    private final String extension;

    @Builder
    public FileData(String directory, String fileName, String extension) {
        validateDirectory(directory);
        this.directory = directory;
        this.fileName = fileName;
        this.extension = extension;
    }

    private void validateDirectory(String directory) {
        if (!directory.startsWith(DIRECTORY_DELIMITER)
                || directory.endsWith(DIRECTORY_DELIMITER)) {
            throw new AwsS3ClientException(AwsS3ClientErrorCode.INVALID_DIRECTORY_FORMAT, DIRECTORY_FORMAT);
        }
    }

    public String getFilePath() {
        return String.format(FILE_PATH_FORMAT, directory, fileName, extension);
    }

    public String getExtension() {
        return extension;
    }
}
