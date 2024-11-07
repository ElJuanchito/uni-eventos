package co.edu.uniquindio.uni_eventos.services.impl;

import co.edu.uniquindio.uni_eventos.services.ImageService;
import com.google.cloud.storage.*;
import com.google.firebase.cloud.StorageClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.util.Base64;
import java.util.UUID;

@Service
public class ImageServiceImpl implements ImageService {

    @Override
    public String uploadImage(MultipartFile file) throws Exception {
        Bucket bucket = StorageClient.getInstance().bucket();

        String fileName = String.format(
                "%s-%s",
                UUID.randomUUID().toString(),
                file.getOriginalFilename()
        );

        Blob blob = bucket.create(
                fileName,
                file.getInputStream(),
                file.getContentType()
        );

        return String.format(
                "https://firebasestorage.googleapis.com/v0/b/%s/o/%s?alt=media",
                bucket.getName(),
                blob.getName()
        );
    }

    @Override
    public String   uploadImage(String base64Img) throws Exception {
        if (base64Img == null || !base64Img.startsWith("data:image/")) {
            throw new IllegalArgumentException("Invalid Base64 image format.");
        }

        String[] parts = base64Img.split(",");

        if (parts.length != 2) {
            throw new IllegalArgumentException("Base64 string is improperly formatted.");
        }

        String imageData = parts[1];

        byte[] imageBytes = Base64.getDecoder().decode(imageData);

        String fileName = String.format("%s-s%s", UUID.randomUUID(), ".png");

        Bucket bucket = StorageClient.getInstance().bucket();

        Blob blob = bucket.create(
                fileName,
                new ByteArrayInputStream(imageBytes),
                "image/png"
        );

        return String.format(
                "https://firebasestorage.googleapis.com/v0/b/%s/o/%s?alt=media",
                bucket.getName(),
                blob.getName()
        );
    }

    @Override
    public void deleteImage(String name) throws Exception {
        Bucket bucket = StorageClient.getInstance().bucket();
        Blob blob = bucket.get(name);
        blob.delete();
    }
}
