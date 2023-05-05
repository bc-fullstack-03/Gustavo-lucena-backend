package br.com.bootcam.sysmap.services.fileupload;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@RequiredArgsConstructor
@Service
public class FileUploadService implements IFileUploadService{

    private final AwsService awsService;

    @Override
    public String upload(MultipartFile file, UUID id){
        String fileUri = "";

        String fileName = createNameFile(file, id);

        try {
            fileUri = awsService.upload(file, fileName);
        }catch (Exception ex){
            throw new RuntimeException(ex.getMessage());
        }

        return fileUri;
    }

    private String createNameFile(MultipartFile file, UUID id){
        return id + "." + file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".")+ 1);
    }
}
