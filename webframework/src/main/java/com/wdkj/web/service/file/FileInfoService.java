package com.wdkj.web.service.file;

import com.wdkj.web.model.file.FileInfo;

import java.util.List;

/**
 * @author twj
 * @date 2018/9/20 20:04
 */
public interface FileInfoService {

    int insert(FileInfo pojo);

    int insertList(List<FileInfo> pojos);

    List<FileInfo> select(FileInfo pojo);

    int update(FileInfo pojo);
}
