package br.com.bootcam.sysmap.services.fileUpload;

import org.springframework.web.multipart.MultipartFile;

public interface IFileUploadService {

    String upload(MultipartFile file, String fileName);
}
