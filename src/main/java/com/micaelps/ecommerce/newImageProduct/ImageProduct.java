package com.micaelps.ecommerce.newImageProduct;

import com.micaelps.ecommerce.newProduct.Product;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
public class ImageProduct {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    private String path;
    @ManyToOne
    private Product product;


    public ImageProduct(String path, Product product) {
        this.path = path;
        this.product = product;
    }
    @Deprecated
    public ImageProduct() {
    }

    public String getPath() {
        return path;
    }
}
