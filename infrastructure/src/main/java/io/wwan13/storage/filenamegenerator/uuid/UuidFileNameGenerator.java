package io.wwan13.storage.filenamegenerator.uuid;

import io.wwan13.storage.filenamegenerator.FileNameGenerator;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class UuidFileNameGenerator implements FileNameGenerator {

    private static final String FILE_NAME_FORMAT = "%s.%s";

    @Override
    public String generate(String extension) {
        String uuid = UUID.randomUUID().toString();
        return String.format(FILE_NAME_FORMAT, uuid, extension);
    }
}
