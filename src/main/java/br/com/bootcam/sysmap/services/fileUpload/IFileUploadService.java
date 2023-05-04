package br.com.bootcam.sysmap.services.fileUpload;

import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

public interface IFileUploadService {

    String upload(MultipartFile file, UUID id);
}
