package com.sparta.palpaleats.domain.s3;

import com.amazonaws.services.s3.model.DeleteObjectRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

// file 이름 관련 메소드 집합
@Component
public class S3Util {
    private static final String FILE_EXTENSION_SEPARATOR = ".";
    private static final String CATEGORY_PREFIX = "/";

    private static final String TIME_SEPARATOR = "_";

    public String buildFileName(String category, String originalFileName) {
        int fileExtensionIndex = originalFileName.lastIndexOf(FILE_EXTENSION_SEPARATOR);
        String fileExtension = originalFileName.substring(fileExtensionIndex);
        String fileName = originalFileName.substring(0, fileExtensionIndex);
        String now = String.valueOf(System.currentTimeMillis());

        return category + CATEGORY_PREFIX + fileName + TIME_SEPARATOR + now + fileExtension;
    }

    public boolean validateFileExists(MultipartFile multipartFile) {
        return !multipartFile.isEmpty();
    }

    // Get S3 pathUrl
    public String getUrlPath(String fileUrl){
        String splitStr = ".com/";
        String fileName = fileUrl.substring(fileUrl.lastIndexOf(splitStr) + splitStr.length());
        return fileName;
    }
}