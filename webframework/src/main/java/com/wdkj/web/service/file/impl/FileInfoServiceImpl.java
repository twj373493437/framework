package com.wdkj.web.service.file.impl;

import com.wdkj.web.model.file.FileInfo;
import com.wdkj.web.dao.file.FileInfoMapper;
import com.wdkj.web.service.file.FileInfoService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;

@Service
public class FileInfoServiceImpl implements FileInfoService {

    @Resource
    private FileInfoMapper fileInfoMapper;

    public int insert(FileInfo pojo){
        return fileInfoMapper.insert(pojo);
    }

    public int insertList(List< FileInfo> pojos){
        return fileInfoMapper.insertList(pojos);
    }

    public List<FileInfo> select(FileInfo pojo){
        return fileInfoMapper.select(pojo);
    }

    public int update(FileInfo pojo){
        return fileInfoMapper.update(pojo);
    }

}
