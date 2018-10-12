package com.wdkj.web.service.sys.impl;

import com.wdkj.web.dao.sys.UserMapper;
import com.wdkj.web.model.sys.User;
import com.wdkj.web.service.sys.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author twj
 * @date 2018/9/19 17:24
 */
@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    @Override
    public List<User> list(User user) {
        return userMapper.selectList(user);
    }

    @Override
    @Transactional
    public User getOne(User user) {
        return userMapper.selectOne(user);
    }
}
