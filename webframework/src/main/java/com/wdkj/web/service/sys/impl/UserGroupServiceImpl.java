package com.wdkj.web.service.sys.impl;

import com.wdkj.web.dao.sys.UserGroupMapper;
import com.wdkj.web.model.sys.UserGroup;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
@Service
public class UserGroupServiceImpl {

    @Resource
    private UserGroupMapper userGroupMapper;

    public int insert(UserGroup pojo){
        return userGroupMapper.insert(pojo);
    }

    public int insertList(List< UserGroup> pojos){
        return userGroupMapper.insertList(pojos);
    }

    public List<UserGroup> select(UserGroup pojo){
        return userGroupMapper.select(pojo);
    }

    public int update(UserGroup pojo){
        return userGroupMapper.update(pojo);
    }

}
