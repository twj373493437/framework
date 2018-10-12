package com.wdkj.web.service.file.impl;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.OSSObject;
import com.wdkj.web.service.file.FileStoreUtils;
import org.springframework.beans.factory.annotation.Value;

import javax.annotation.PostConstruct;
import java.io.*;

/**
 * @author : TianWenjian
 */
public class OssFileStoreImpl implements FileStoreUtils {

    @Value("${oss.endpoint}")
    private String endpoint;
    // 阿里云主账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM账号进行API访问或日常运维，请登录 https://ram.console.aliyun.com 创
    @Value("${oss.accessKeyId}")
    private String accessKeyId;
    @Value("${oss.accessKeySecret}")
    private String accessKeySecret;
    @Value("${oss.bucketName}")
    private String bucketName;

    private OSSClient ossClient;

    @PostConstruct
    public void init() {
        ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
    }

    @Override
    public byte[] getFile(String fileId) {
        // 创建OSSClient实例。
        //OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
        // ossObject包含文件所在的存储空间名称、文件名称、文件元信息以及一个输入流。
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        OSSObject ossObject = ossClient.getObject(bucketName, fileId);
        InputStream inputStream = ossObject.getObjectContent();
        try {

            byte[] temp = new byte[1024];
            int len = 0;
            while ((len = inputStream.read(temp)) > 0) {
                outputStream.write(temp, 0, len);
            }
        } catch (IOException e) {
            throw new RuntimeException("获取文件失败");
        } finally {
            try {
                // 数据读取完成后，获取的流必须关闭，否则会造成连接泄漏，导致请求无连接可用，程序无法正常工作。
                inputStream.close();
                outputStream.close();
            } catch (Exception e) {
                throw new RuntimeException("读取失败：关闭流失败", e);
            }
        }

        return outputStream.toByteArray();
    }

    @Override
    public void fileToOutputStream(String fileId, OutputStream outputStream) {

        // 创建OSSClient实例。
        //OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
        // ossObject包含文件所在的存储空间名称、文件名称、文件元信息以及一个输入流。
        OSSObject ossObject = ossClient.getObject(bucketName, fileId);
        InputStream inputStream = ossObject.getObjectContent();
        try {

            byte[] temp = new byte[1024];
            int len = 0;
            while ((len = inputStream.read(temp)) > 0) {
                outputStream.write(temp, 0, len);
            }

        } catch (IOException e) {
            throw new RuntimeException("获取文件失败");
        } finally {
            try {
                // 数据读取完成后，获取的流必须关闭，否则会造成连接泄漏，导致请求无连接可用，程序无法正常工作。
                inputStream.close();
                outputStream.close();
            } catch (Exception e) {
                throw new RuntimeException("读取失败：关闭流失败", e);
            }
        }
    }

    @Override
    public void storeFile(String fileId, byte[] fileData) {

        // 创建OSSClient实例。
        //OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
        // 上传Byte数组。
        ossClient.putObject(bucketName, fileId, new ByteArrayInputStream(fileData));

    }

    @Override
    public void storeFile(String fileId, InputStream inputStream) {
        // 创建OSSClient实例。
        //OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
        // 上传Byte数组。
        ossClient.putObject(bucketName, fileId, inputStream);

    }
}