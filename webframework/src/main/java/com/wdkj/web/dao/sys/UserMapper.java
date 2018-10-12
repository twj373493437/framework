package com.wdkj.web.dao.sys;

import com.wdkj.web.model.sys.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author twj
 * @date 2018/9/19 18:33
 */
@Mapper
public interface UserMapper {

    List<User> selectList(User user);

    User selectOne(User user);
}
