package com.wdkj.web.service.file.impl;

import com.wdkj.web.service.file.FileStoreUtils;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Value;

import java.io.*;

/**
 * @author : TianWenjian
 */
public class DiskFileStoreImpl implements FileStoreUtils {

    @Value("${local.directory.path}")
    String directoryPath;

    @Override
    public byte[] getFile(String fileId) {

        String filepath = directoryPath + File.separator + fileId;
        File file = new File(filepath);
        if (file.exists() && file.isFile()) {
            try {
                return FileUtils.readFileToByteArray(file);
            } catch (IOException e) {
                throw new RuntimeException("获取文件失败：", e);
            }
        } else {
            throw new RuntimeException("文件不存在：" + filepath);
        }
    }

    @Override
    public void fileToOutputStream(String fileId, OutputStream outputStream) {
        String filepath = directoryPath + File.separator + fileId;
        File file = new File(filepath);
        if (file.exists() && file.isFile()) {
            try {
                FileInputStream inputStream = new FileInputStream(filepath);
                byte[] temp = new byte[1024];
                int len = 0;
                while ((len = inputStream.read(temp)) > 0) {
                    outputStream.write(temp, 0, len);
                }
                outputStream.flush();
                outputStream.close();
                inputStream.close();
            } catch (IOException e) {
                throw new RuntimeException("获取文件失败：", e);
            }
        } else {
            throw new RuntimeException("文件不存在：" + filepath);
        }
    }

    @Override
    public void storeFile(String fileId, byte[] fileData) {
        File directory = new File(directoryPath);
        if (directory.exists() && directory.isDirectory()) {
            try {
                String filepath = directoryPath + File.separator + fileId;
                File file = new File(filepath);
                file.createNewFile();
                FileUtils.writeByteArrayToFile(file, fileData, false);
            } catch (IOException ie) {
                throw new RuntimeException("保存文件失败：" + directoryPath + fileId, ie);
            }
        } else {
            throw new RuntimeException("保存的文件夹不存在：" + directoryPath);
        }
    }

    @Override
    public void storeFile(String fileId, InputStream inputStream) {
        File directory = new File(directoryPath);
        if (directory.exists() && directory.isDirectory()) {
            try {
                String filepath = directoryPath + File.separator + fileId;
                File file = new File(filepath);
                file.createNewFile();

                FileOutputStream outputStream = new FileOutputStream(file, false);
                byte[] temp = new byte[1024];
                int len = 0;
                while ((len = inputStream.read(temp)) > 0) {
                    outputStream.write(temp, 0, len);
                }

                outputStream.flush();
                outputStream.close();
                inputStream.close();
            } catch (IOException ie) {
                throw new RuntimeException("保存文件失败：" + directoryPath + fileId, ie);
            }
        } else {
            throw new RuntimeException("保存的文件夹不存在：" + directoryPath);
        }
    }
}
