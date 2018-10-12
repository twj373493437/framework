package com.wdkj.web.service.dictionary;

import com.wdkj.web.model.dictionary.Dictionary;

import java.util.List;

/**
 * @author twj
 * @date 2018/9/20 19:55
 */
public interface DictionaryService {

    int insert(Dictionary pojo);

    int insertList(List<Dictionary> pojos);

    List<Dictionary> selectList(Dictionary pojo);

    int update(Dictionary pojo);
}
