package co.edu.uniquindio.uni_eventos.services;

import org.springframework.web.multipart.MultipartFile;

public interface ImageService {

    String uploadImage(MultipartFile file) throws Exception;
    String uploadImage(String base64Img) throws Exception;
    void deleteImage(String name) throws Exception;
}
