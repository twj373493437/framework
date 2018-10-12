package com.wdkj.web.dao.file;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

import com.wdkj.web.model.file.FileInfo;

@Mapper
public interface FileInfoMapper {

    int insert(FileInfo pojo);

    int insertList(List<FileInfo> pojo);

    List<FileInfo> select(FileInfo pojo);

    int update(FileInfo pojo);

}
