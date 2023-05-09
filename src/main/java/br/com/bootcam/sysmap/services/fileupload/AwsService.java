package br.com.bootcam.sysmap.services.fileupload;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.PutObjectRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Objects;

@RequiredArgsConstructor
@Service
public class AwsService {

    private final AmazonS3 amazonS3;

    @Value("${cloud.aws.s3.endpoint}")
    private String urlS3;

    @Value("${aws.s3.bucket.name}")
    private String bucket;

    public String upload(MultipartFile file, String fileName){
        String fileUri = "";

        try{

            var fileConverted = convertMultipartToFile(file);
            amazonS3.putObject(new PutObjectRequest(bucket, fileName, fileConverted).withCannedAcl(CannedAccessControlList.PublicRead));

            fileUri = urlS3+"/"+bucket+"/"+fileName;
            fileConverted.delete();

        }catch (Exception ex){
            System.out.println(ex.getMessage());
            throw new RuntimeException(ex.getMessage());
        }

        return fileUri;
    }

    private File convertMultipartToFile(MultipartFile file) throws IOException {
        var convFile = new File(Objects.requireNonNull(file.getOriginalFilename()));

        var fos = new FileOutputStream(convFile);

        fos.write(file.getBytes());
        fos.close();

        return convFile;
    }
}
