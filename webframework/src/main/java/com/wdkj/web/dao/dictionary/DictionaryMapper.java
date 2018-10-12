package com.wdkj.web.dao.dictionary;

import org.apache.ibatis.annotations.Param;
import java.util.List;
import com.wdkj.web.model.dictionary.Dictionary;

public interface DictionaryMapper {

    int insert(@Param("pojo") Dictionary pojo);

    int insertList(@Param("pojos") List< Dictionary> pojo);

    List<Dictionary> select(@Param("pojo") Dictionary pojo);

    int update(@Param("pojo") Dictionary pojo);

}
