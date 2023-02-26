package es.codeurjc.mca.events.image;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.DeleteObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectResponse;

import java.io.File;
import java.io.IOException;

@Service("storageService")
@Profile("production")
public class S3ImageService implements ImageService {

    public static S3Client s3;

    @Value("${amazon.s3.bucket-name}")
    private String bucketName;

    public S3ImageService() {
        s3 = S3Client.builder()
                .region(Region.US_EAST_1)
                .build();
    }

    @Override
    public String createImage(MultipartFile multiPartFile) throws IOException {
        String fileName = multiPartFile.getOriginalFilename();
        File file = new File(System.getProperty("java.io.tmpdir")+"/"+fileName);
        multiPartFile.transferTo(file);
        PutObjectResponse putObjectResponse = s3.putObject(
                PutObjectRequest.builder()
                        .bucket(this.bucketName)
                        .key(fileName)
                        .build(),
                RequestBody.fromFile(file)
        );
        return putObjectResponse.toString();
    }

    @Override
    public void deleteImage(String image) {
        s3.deleteObject(
                DeleteObjectRequest.builder()
                        .bucket(bucketName)
                        .key(image)
                        .build()
        );
    }

}
