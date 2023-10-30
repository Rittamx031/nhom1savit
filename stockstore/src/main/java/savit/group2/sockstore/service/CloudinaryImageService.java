package savit.group2.sockstore.service;

import com.cloudinary.Cloudinary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.util.Map;
import java.io.IOException;
import java.util.UUID;

@Service
public class CloudinaryImageService {
    @Autowired
    Cloudinary config;

    public String saveImage(MultipartFile multipartFile) throws IOException {
        return config.uploader()
                .upload(multipartFile.getBytes(),
                        (Map.of("public_id", UUID.randomUUID().toString(), "folder", "SOCKDETAIL")))
                .get("url")
                .toString();
    }

}
