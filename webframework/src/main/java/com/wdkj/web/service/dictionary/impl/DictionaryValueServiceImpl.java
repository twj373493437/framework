package com.wdkj.web.service.dictionary.impl;

import com.wdkj.web.model.dictionary.DictionaryValue;
import com.wdkj.web.service.dictionary.DictionaryValueService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;

import com.wdkj.web.dao.dictionary.DictionaryValueDao;

@Service
public class DictionaryValueServiceImpl implements DictionaryValueService {

    @Resource
    private DictionaryValueDao dictionaryValueDao;

    public int insert(DictionaryValue pojo){
        return dictionaryValueDao.insert(pojo);
    }

    public int insertList(List< DictionaryValue> pojos){
        return dictionaryValueDao.insertList(pojos);
    }

    public List<DictionaryValue> selectList(DictionaryValue pojo){
        return dictionaryValueDao.select(pojo);
    }

    public int update(DictionaryValue pojo){
        return dictionaryValueDao.update(pojo);
    }

}
