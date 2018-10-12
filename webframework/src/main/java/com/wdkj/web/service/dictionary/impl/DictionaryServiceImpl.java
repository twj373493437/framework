package com.wdkj.web.service.dictionary.impl;

import com.wdkj.web.dao.dictionary.DictionaryMapper;
import com.wdkj.web.model.dictionary.Dictionary;
import com.wdkj.web.service.dictionary.DictionaryService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;

@Service
public class DictionaryServiceImpl implements DictionaryService {

    @Resource
    private DictionaryMapper dictionaryMapper;

    public int insert(Dictionary pojo){
        return dictionaryMapper.insert(pojo);
    }

    public int insertList(List< Dictionary> pojos){
        return dictionaryMapper.insertList(pojos);
    }

    public List<Dictionary> selectList(Dictionary pojo){
        return dictionaryMapper.select(pojo);
    }

    public int update(Dictionary pojo){
        return dictionaryMapper.update(pojo);
    }

}
