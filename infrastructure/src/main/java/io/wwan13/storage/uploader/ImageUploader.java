package io.wwan13.storage.uploader;

public interface ImageUploader {

    String upload(String imageUrl, ImageType imageType);
}
