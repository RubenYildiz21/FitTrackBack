package com.fittrack.fit_track.service;

import java.io.IOException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cloudinary.Cloudinary;
import com.cloudinary.Transformation;
import com.cloudinary.utils.ObjectUtils;

@Service
public class CloudinaryService {
    @Autowired
    private Cloudinary cloudinary;

    public String uploadImage(MultipartFile file) throws IOException {

        Transformation transformation = new Transformation()
        .width(500)
        .height(500)
        .crop("fill")
        .gravity("auto");

        Map<?, ?> uploadResult = cloudinary.uploader().upload(file.getBytes(),
                ObjectUtils.asMap(
                    "resource_type", "auto",
                    "transformation", transformation
                    )); // auto gère les images et vidéos
        return uploadResult.get("url").toString(); // Retourne l'URL de l'image stockée
    }
}
