package br.com.bootcam.sysmap.services.fileupload;

import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

public interface IFileUploadService {

    String upload(MultipartFile file, UUID id);
}
