package com.ecommerce.ecommerce_api.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


@RestController
@RequestMapping("images")
public class ImageController {

    @Value("${assets.items.image-path}")
    private String itemImagePath;

    @GetMapping("/item/{imageName}")
    public ResponseEntity<Resource> getItemImage(@PathVariable String imageName) {
        File file = null;
        ByteArrayResource resource = null;
        try {
            file = ResourceUtils.getFile("classpath:" + itemImagePath + imageName);
            Path path = Paths.get(file.getAbsolutePath());
            resource = new ByteArrayResource(Files.readAllBytes(path));
        } catch (FileNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        return ResponseEntity.ok().contentLength(file.length())
                .contentType(MediaType.IMAGE_JPEG).body(resource);
    }
}
