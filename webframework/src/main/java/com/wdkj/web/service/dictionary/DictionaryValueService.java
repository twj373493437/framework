package com.wdkj.web.service.dictionary;

import com.wdkj.web.model.dictionary.DictionaryValue;

import java.util.List;

/**
 * @author twj
 * @date 2018/9/20 19:44
 */
public interface DictionaryValueService {

    int insert(DictionaryValue pojo);

    int insertList(List<DictionaryValue> pojos);

    List<DictionaryValue> selectList(DictionaryValue pojo);

    int update(DictionaryValue pojo);
}