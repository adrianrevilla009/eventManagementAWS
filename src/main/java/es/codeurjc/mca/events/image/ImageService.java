package es.codeurjc.mca.events.image;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ImageService {

    public String createImage(MultipartFile multiPartFile) throws IOException;

    public void deleteImage(String image);
}
