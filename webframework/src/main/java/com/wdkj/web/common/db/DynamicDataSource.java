package com.wdkj.web.common.db;

import com.wdkj.web.common.db.DatasourceEnum;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * @author twj
 * @date 2018/6/21 11:46
 */
public class DynamicDataSource extends AbstractRoutingDataSource {

    private static final ThreadLocal<DatasourceEnum> contextHolder = new ThreadLocal<>();

    public static void setDatabaseType(DatasourceEnum type) {
        contextHolder.set(type);
    }

    public static DatasourceEnum getDatabaseType() {
        return contextHolder.get();
    }

    @Override
    protected Object determineCurrentLookupKey() {
        return DynamicDataSource.getDatabaseType();
    }
}
