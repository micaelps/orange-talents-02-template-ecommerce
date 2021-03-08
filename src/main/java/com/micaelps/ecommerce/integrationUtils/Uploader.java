package com.micaelps.ecommerce.integrationUtils;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public abstract class Uploader {

    private static final String LINK_UPLOAD = "http://upload-fake.com.br/";
    private static final String BAR = "/";

    public static List<String> DefaultUploadImage(List<MultipartFile> files){
        return files.stream()
                .map(image -> LINK_UPLOAD+image.getName()+ BAR + UUID.randomUUID())
                .collect(Collectors.toList());
    }

    public  abstract List<String> uploadImages(List<MultipartFile> files);
}
