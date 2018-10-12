package com.wdkj.web.dao.sys;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;
import com.wdkj.web.model.sys.UserGroup;

@Mapper
public interface UserGroupMapper {

    int insert(@Param("pojo") UserGroup pojo);

    int insertList(@Param("pojos") List< UserGroup> pojo);

    List<UserGroup> select(@Param("pojo") UserGroup pojo);

    int update(@Param("pojo") UserGroup pojo);

}
