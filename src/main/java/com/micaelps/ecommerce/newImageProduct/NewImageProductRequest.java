package com.micaelps.ecommerce.newImageProduct;

import com.micaelps.ecommerce.newProduct.Product;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class NewImageProductRequest {


    private List<MultipartFile> images = new ArrayList<>();

    public NewImageProductRequest(List<MultipartFile> images) {
        this.images = images;
    }

    public void setImages(List<MultipartFile> images) {
        this.images = images;
    }

    public NewImageProductRequest() {
    }

    public List<MultipartFile> getImages() {
        return images;
    }

    public List<String> toLinks() {
        return this.images.stream().map(image -> image.getName()).collect(Collectors.toList());
    }
}
