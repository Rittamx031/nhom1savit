package savit.group2.sockstore.restcontroller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import savit.group2.sockstore.service.UploadService;

import java.io.File;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@RestController
public class UploadRestController {
    @Autowired
    UploadService uploadService;
    @PostMapping("/rest/upload/{folder}")
    public JsonNode upload(@PathParam("file") MultipartFile file, @PathVariable("folder") String folder) {
        Path path = Paths.get("");
        path = path.resolve("src").resolve("main").resolve("resources").resolve("static").resolve("assets").resolve(folder);
        try {
            InputStream inputStream = file.getInputStream();
            Files.copy(inputStream, path.resolve(file.getOriginalFilename()), StandardCopyOption.REPLACE_EXISTING);
        } catch (Exception e){
            e.printStackTrace();
        }
        File savedFile = uploadService.save(file, folder);
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode response = mapper.createObjectNode();
        response.put("name", savedFile.getName());
        response.put("size", savedFile.length());
        return response;
    }
}
