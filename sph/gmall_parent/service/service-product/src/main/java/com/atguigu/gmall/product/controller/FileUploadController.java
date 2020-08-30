package com.atguigu.gmall.product.controller;

import com.atguigu.gmall.common.result.Result;
import io.minio.*;
import io.minio.errors.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

/**
 * @author: y
 * @date: 2023/7/28 18:19
 * @description:
 */
@RestController
@RequestMapping("/admin/product")
public class FileUploadController {
    //  获取文件上传对应的地址
    @Value("${minio.endpointUrl}")
    public String endpointUrl;

    @Value("${minio.accessKey}")
    public String accessKey;

    @Value("${minio.secreKey}")
    public String secreKey;

    @Value("${minio.bucketName}")
    public String bucketName;

    @PostMapping("/fileUpload")
    public Result fileUpload(MultipartFile file) {
        String fileUrl;
        try {
            // Create a minioClient with the MinIO server playground, its access key and secret key.
            MinioClient minioClient =
                    MinioClient.builder()
                            .endpoint(endpointUrl)
                            .credentials(accessKey, secreKey)
                            .build();

            // Make 'asiatrip' bucket if not exist.
            boolean found =
                    minioClient.bucketExists(BucketExistsArgs.builder().bucket(bucketName).build());
            if (!found) {
                // Make a new bucket called 'asiatrip'.
                minioClient.makeBucket(MakeBucketArgs.builder().bucket(bucketName).build());
            } else {
                System.out.println("Bucket"+bucketName+"already exists.");
            }
            //定制文件名
            String fileName=System.currentTimeMillis()+ UUID.randomUUID().toString().replaceAll("-","").substring(0,3)+file.getOriginalFilename();
            //上传文件
          minioClient.putObject(
                    PutObjectArgs.builder().bucket(bucketName).object(fileName).stream(
                                    file.getInputStream(), file.getSize(), -1)
                            .contentType("video/mp4")
                            .build());
          //编辑文件访问路径，用于上传图片回显

           fileUrl=endpointUrl+"/"+bucketName+"/"+fileName;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


        return Result.ok(fileUrl);
    }
}


