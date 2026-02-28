package com.example.Nucleus.service.impl;

import com.example.Nucleus.exception.S3UploadException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.model.S3Exception;

import java.io.IOException;
import java.util.UUID;

@Service
public class S3ServiceImpl {
    @Value("${aws.s3.bucketName}")
    private String bucketName;

    @Value("${aws.s3.region}")
    private String region;

    @Autowired
    private S3Client s3Client;

    public String uploadImg(MultipartFile file, String folder){
        System.out.println("in s3 upload");
        try {
            String key = folder + "/" + UUID.randomUUID() + "_" + file.getOriginalFilename();

            PutObjectRequest request = PutObjectRequest.builder()
                    .bucket(bucketName)
                    .key(key)
                    .contentType(file.getContentType())
                    .build();

            s3Client.putObject(request, RequestBody.fromBytes(file.getBytes()));

            System.out.println("s3 upload done");
            return key;

        }catch (IOException e) {
            throw new RuntimeException("File processing failed", e);
        }catch (S3Exception e) {
            throw new S3UploadException(e.getMessage());
        }
    }

    public String getImgUrl(String key){
        return "https://" + bucketName +
                ".s3." + region +
                ".amazonaws.com/" + key;
    }
}
