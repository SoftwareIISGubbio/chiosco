package it.edu.iisgubbio.servizitotem.circolari;

import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CircolariManager {

    @GetMapping("/circolari/pdf/{filename:.+}")
    @ResponseBody
    // public ResponseEntity<Resource> serveFile(@PathVariable String filename) {
    public ResponseEntity<Resource> serveFile() {
        Resource file = new FileSystemResource("/Volumes/ramdisk/test.pdf");

        if ( !file.exists() ) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok().contentType(MediaType.APPLICATION_PDF).body(file);
    }
}
