package com.wdkj.web.common.db;

import com.wdkj.web.common.db.DatasourceEnum;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface DestineDataSource {

    /**
     * 要使用的数据源
     * @return
     */
    DatasourceEnum value();
}
