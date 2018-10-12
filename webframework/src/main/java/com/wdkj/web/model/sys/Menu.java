package com.wdkj.web.model.sys;

/**
 * @author twj
 * @date 2018/9/20 20:18
 */
public class Menu {

    private Integer id;
    private String name;
    private Integer parentId;
    private String path;
    private Integer status;  //-1 不可用 不显示， 1 正常，0 显示但不可用
    private Integer sort;

    public Integer getId() {
        return id;
    }

    public Menu setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Menu setName(String name) {
        this.name = name;
        return this;
    }

    public Integer getParentId() {
        return parentId;
    }

    public Menu setParentId(Integer parentId) {
        this.parentId = parentId;
        return this;
    }

    public String getPath() {
        return path;
    }

    public Menu setPath(String path) {
        this.path = path;
        return this;
    }

    public Integer getStatus() {
        return status;
    }

    public Menu setStatus(Integer status) {
        this.status = status;
        return this;
    }

    public Integer getSort() {
        return sort;
    }

    public Menu setSort(Integer sort) {
        this.sort = sort;
        return this;
    }
}
