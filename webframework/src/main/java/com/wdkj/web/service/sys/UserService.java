package com.wdkj.web.service.sys;

import com.wdkj.web.model.sys.User;

import java.util.List;

/**
 * @author twj
 * @date 2018/9/19 17:22
 */
public interface UserService {

    List<User> list(User user);

    User getOne(User user);
}
