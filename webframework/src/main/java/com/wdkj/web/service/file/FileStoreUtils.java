package com.wdkj.web.service.file;

import java.io.InputStream;
import java.io.OutputStream;

/**
 * @author : TianWenjian
 */
public interface FileStoreUtils {

    byte[] getFile(String fileId);

    void fileToOutputStream(String fileId, OutputStream outputStream);

    void storeFile(String fileId, byte[] fileData);

    void storeFile(String fileId, InputStream inputStream);
}
