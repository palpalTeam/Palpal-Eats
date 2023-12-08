package com.sparta.palpaleats.domain.s3;


import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLDecoder;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class S3Service {

    private final AmazonS3 amazonS3;
    private final S3Util s3Util;
    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    public String[] saveFile(String category, MultipartFile multipartFile) throws UnsupportedEncodingException {
        String fileName = s3Util.buildFileName(category, Objects.requireNonNull(multipartFile.getOriginalFilename()));
        String[] fileUrlArr = new String[2];
        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentType(multipartFile.getContentType());

        try (InputStream inputStream = multipartFile.getInputStream()) {
            amazonS3.putObject(new PutObjectRequest(bucket, fileName, inputStream, objectMetadata)
                    .withCannedAcl(CannedAccessControlList.PublicRead));
        } catch (IOException e) {throw new IllegalArgumentException();
        }
        String url = amazonS3.getUrl(bucket,fileName).toString();
        String decodeUrl = URLDecoder.decode(url, "utf-8");
        fileUrlArr[0] = url;
        fileUrlArr[1] = s3Util.getUrlPath(decodeUrl);
        return fileUrlArr;
    }

    // URL Path만을 입력값으로 받는다 -> 즉 imageUrL이 아닌 버킷에 존재하는 객체의 Key값을 입력받는다.
    public void deleteImage(String filePath) {
        amazonS3.deleteObject(bucket, filePath);
    }
}
