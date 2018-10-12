package com.wdkj.web.dao.dictionary;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

import com.wdkj.web.model.dictionary.DictionaryValue;

@Mapper
public interface DictionaryValueMapper {

    int insert(DictionaryValue pojo);

    int insertList(List<DictionaryValue> pojo);

    List<DictionaryValue> select(DictionaryValue pojo);

    int update(DictionaryValue pojo);

}
