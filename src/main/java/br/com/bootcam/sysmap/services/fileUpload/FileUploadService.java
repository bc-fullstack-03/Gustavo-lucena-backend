package br.com.bootcam.sysmap.services.fileUpload;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@RequiredArgsConstructor
@Service
public class FileUploadService implements IFileUploadService{

    private final AwsService awsService;

    @Override
    public String upload(MultipartFile file, String fileName){
        String fileUri = "";

        try {
            fileUri = awsService.upload(file, fileName);
        }catch (Exception ex){
            throw new RuntimeException(ex.getMessage());
        }

        return fileUri;
    }
}
