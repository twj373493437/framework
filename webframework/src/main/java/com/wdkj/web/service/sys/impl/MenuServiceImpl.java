package com.wdkj.web.service.sys.impl;

import com.wdkj.web.dao.sys.MenuMapper;
import com.wdkj.web.model.sys.Menu;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;

@Service
public class MenuServiceImpl {

    @Resource
    private MenuMapper menuMapper;

    public int insert(Menu pojo){
        return menuMapper.insert(pojo);
    }

    public int insertList(List< Menu> pojos){
        return menuMapper.insertList(pojos);
    }

    public List<Menu> select(Menu pojo){
        return menuMapper.select(pojo);
    }

    public int update(Menu pojo){
        return menuMapper.update(pojo);
    }

}
