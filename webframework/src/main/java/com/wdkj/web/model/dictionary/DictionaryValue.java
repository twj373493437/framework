package com.wdkj.web.model.dictionary;

/**
 * @author twj
 * @date 2018/9/20 19:35
 */
public class DictionaryValue {

    private String key;
    private String value;
    private Integer id;
    private String showText;
    private String description;
    private Integer dictHeadId;
    private Integer sort;

    public String getKey() {
        return key;
    }

    public DictionaryValue setKey(String key) {
        this.key = key;
        return this;
    }

    public String getValue() {
        return value;
    }

    public DictionaryValue setValue(String value) {
        this.value = value;
        return this;
    }

    public Integer getId() {
        return id;
    }

    public DictionaryValue setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getShowText() {
        return showText;
    }

    public DictionaryValue setShowText(String showText) {
        this.showText = showText;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public DictionaryValue setDescription(String description) {
        this.description = description;
        return this;
    }

    public Integer getDictHeadId() {
        return dictHeadId;
    }

    public DictionaryValue setDictHeadId(Integer dictHeadId) {
        this.dictHeadId = dictHeadId;
        return this;
    }

    public Integer getSort() {
        return sort;
    }

    public DictionaryValue setSort(Integer sort) {
        this.sort = sort;
        return this;
    }
}
