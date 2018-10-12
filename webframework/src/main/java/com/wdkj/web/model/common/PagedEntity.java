package com.wdkj.web.model.common;

/**
 * 包含分页属性的实体
 * @author twj
 * @date 2018/9/20 9:58
 */
public class PagedEntity {

    private Integer offset;

    private Integer limit;

    public Integer getOffset() {
        return offset;
    }

    public PagedEntity setOffset(Integer offset) {
        this.offset = offset;
        return this;
    }

    public Integer getLimit() {
        return limit;
    }

    public PagedEntity setLimit(Integer limit) {
        this.limit = limit;
        return this;
    }
}
