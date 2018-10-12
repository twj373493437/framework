package com.wdkj.web.model.dictionary;

import com.wdkj.web.model.common.PagedEntity;

import java.util.List;

/**
 * @author twj
 * @date 2018/9/20 19:32
 */
public class Dictionary extends PagedEntity {

    private Integer id;
    private String name;
    private Integer userEdit;
    private Integer sort;
    private List<DictionaryValue> valueList;

    public Integer getId() {
        return id;
    }

    public Dictionary setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Dictionary setName(String name) {
        this.name = name;
        return this;
    }

    public Integer getUserEdit() {
        return userEdit;
    }

    public Dictionary setUserEdit(Integer userEdit) {
        this.userEdit = userEdit;
        return this;
    }

    public Integer getSort() {
        return sort;
    }

    public Dictionary setSort(Integer sort) {
        this.sort = sort;
        return this;
    }

    public List<DictionaryValue> getValueList() {
        return valueList;
    }

    public Dictionary setValueList(List<DictionaryValue> valueList) {
        this.valueList = valueList;
        return this;
    }
}
