package com.wdkj.web.model.file;

import java.util.Date;

/**
 * @author twj
 * @date 2018/9/20 19:58
 */
public class FileInfo {

    private Integer id;
    private String md5;
    private String name;
    private String storePath;
    private Integer archived;
    private Date ctime;

    public Integer getId() {
        return id;
    }

    public FileInfo setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getMd5() {
        return md5;
    }

    public FileInfo setMd5(String md5) {
        this.md5 = md5;
        return this;
    }

    public String getName() {
        return name;
    }

    public FileInfo setName(String name) {
        this.name = name;
        return this;
    }

    public String getStorePath() {
        return storePath;
    }

    public FileInfo setStorePath(String storePath) {
        this.storePath = storePath;
        return this;
    }

    public Integer getArchived() {
        return archived;
    }

    public FileInfo setArchived(Integer archived) {
        this.archived = archived;
        return this;
    }

    public Date getCtime() {
        return ctime;
    }

    public FileInfo setCtime(Date ctime) {
        this.ctime = ctime;
        return this;
    }
}
