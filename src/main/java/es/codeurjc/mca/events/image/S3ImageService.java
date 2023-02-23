package es.codeurjc.mca.events.image;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service("storageService")
@Profile("production")
public class S3ImageService implements ImageService {

    @Override
    public String createImage(MultipartFile multiPartFile) {
       return null;
    }

    @Override
    public void deleteImage(String image) {

    }

}
