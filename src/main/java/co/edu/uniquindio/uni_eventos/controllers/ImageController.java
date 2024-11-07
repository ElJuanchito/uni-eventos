package co.edu.uniquindio.uni_eventos.controllers;

import co.edu.uniquindio.uni_eventos.dtos.MessageDTO;
import co.edu.uniquindio.uni_eventos.services.ImageService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/imagenes")
@SecurityRequirement(name = "bearerAuth")
public class ImageController {

    private final ImageService imageService;

    @PostMapping("/upload")
    public ResponseEntity<MessageDTO<String>> upload(@RequestParam("image") MultipartFile image) throws Exception{
        String response = imageService.uploadImage(image);
        return ResponseEntity.ok().body(new MessageDTO<>(false, response));
    }


    @DeleteMapping("/delete")
    public ResponseEntity<MessageDTO<String>> delete(@RequestParam("idImagen") String idImagen)  throws Exception{
        imageService.deleteImage( idImagen );
        return ResponseEntity.ok().body(new MessageDTO<>(false, "La imagen fue eliminada correctamente"));
    }


}
