package core.startup.mealtoktok.infra.gcs;

import java.io.IOException;
import java.nio.channels.Channels;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import core.startup.mealtoktok.common.dto.Image;
import core.startup.mealtoktok.domain.global.File;
import core.startup.mealtoktok.domain.global.FileUploader;
import core.startup.mealtoktok.infra.gcs.exception.FileUploadFailException;

import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;

@Component
@RequiredArgsConstructor
@Slf4j
public class GcsFileUploader implements FileUploader {

    private final Storage storage;

    @Value("${spring.cloud.gcp.storage.bucket}")
    private String bucketName;

    @Value("${spring.cloud.gcp.storage.url}")
    private String storagePath;

    @Override
    public List<Image> upload(List<File> files) {
        List<Image> images = new ArrayList<>();
        for (File file : files) {
            if (file != null) {
                images.add(upload(file));
            }
        }
        return images;
    }

    @Override
    public Image upload(File file) {
        String uuid = UUID.randomUUID().toString();
        BlobInfo blobInfo =
                BlobInfo.newBuilder(bucketName, uuid).setContentType(file.contentType()).build();

        try (var writer = storage.writer(blobInfo);
                var input = file.inputStream()) {
            input.transferTo(Channels.newOutputStream(writer));
            log.info("이미지 업로드 성공 - {}", storagePath + uuid);
            return Image.from(storagePath + uuid);
        } catch (IOException e) {
            log.warn("ImageEntity upload failed", e);
            throw FileUploadFailException.EXCEPTION;
        }
    }
}
