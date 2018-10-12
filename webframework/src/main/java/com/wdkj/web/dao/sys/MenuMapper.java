package com.wdkj.web.dao.sys;

import org.apache.ibatis.annotations.Param;
import java.util.List;
import com.wdkj.web.model.sys.Menu;

public interface MenuMapper {

    int insert(@Param("pojo") Menu pojo);

    int insertList(@Param("pojos") List< Menu> pojo);

    List<Menu> select(@Param("pojo") Menu pojo);

    int update(@Param("pojo") Menu pojo);

}
