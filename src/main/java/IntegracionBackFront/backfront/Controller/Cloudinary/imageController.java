package IntegracionBackFront.backfront.Controller.Cloudinary;

import IntegracionBackFront.backfront.Services.Cloudinary.CloudinaryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;


@RequestMapping("/api/image")
@RestController
@CrossOrigin
public class imageController {

    private final CloudinaryService Cservice;

    public imageController(CloudinaryService cservice) {
        Cservice = cservice;
    }

    @PostMapping("/upload")
    public ResponseEntity<?> uploadImage (@RequestParam("image") MultipartFile file) throws IOException {
        try {
            String imageURL = Cservice.uploadImage(file);
            return  ResponseEntity.ok(Map.of(
                    "message", "Imagen subida exitosamente",
                    "url", imageURL
            ));

        }catch (IOException e){
            return ResponseEntity.internalServerError().body("Error al subir la imagen" + e.getMessage()
            );

        }
    }

    @PostMapping("/upload-to-folder")
    public ResponseEntity<?> uploadImageToFolder(
            @RequestParam("image") MultipartFile file,
            @RequestParam String folder
    ){
        try{
            String imageUrl = Cservice.uploadImage(file, folder);
            return ResponseEntity.ok(Map.of(
                    "message", "Imagen subida exitosamente",
                    "url", imageUrl
            ));

        }catch (IOException e){
            return  ResponseEntity.internalServerError().body("Error al subir la imagen");

        }

    }

}
