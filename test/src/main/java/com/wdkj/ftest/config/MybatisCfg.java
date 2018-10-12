package com.wdkj.ftest.config;

import com.wdkj.web.common.db.DatasourceEnum;
import com.wdkj.web.common.db.DynamicDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
//@MapperScan(basePackages = {"com.wdkj.web.dao"})
@EnableTransactionManagement
public class MybatisCfg implements TransactionManagementConfigurer {

    @Autowired
    private Environment env;

    @Resource(name = "mainTransactionManager")
    private PlatformTransactionManager defaultTransactionManager;

    /**
     * 创建数据源(数据源的名称：方法名可以取为XXXDataSource(),XXX为数据库名称,该名称也就是数据源的名称)
     */
    @Bean(name = "mainDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.primary")
    public DataSource mainDataSource() {
        return DataSourceBuilder.create().build();
    }

    /**
     * 配置事务管理器,需要每个数据源分别控制事务（否则需要配置分布式事务）
     */
    @Bean(name = "mainTransactionManager")
    public DataSourceTransactionManager mainTransactionManager(@Qualifier("mainDataSource") DataSource dataSource) throws Exception {
        DataSourceTransactionManager transactionManager = new DataSourceTransactionManager(dataSource);
        return transactionManager;
    }

//    @Bean
//    public DataSource myTestDb2DataSource() throws Exception {
//        Properties props = new Properties();
//        props.put("driverClassName", env.getProperty("jdbc2.driverClassName"));
//        props.put("url", env.getProperty("jdbc2.url"));
//        props.put("username", env.getProperty("jdbc2.username"));
//        props.put("password", env.getProperty("jdbc2.password"));
//        return DruidDataSourceFactory.createDataSource(props);
//
//    }

    /**
     * @Primary 该注解表示在同一个接口有多个实现类可以注入的时候，默认选择哪一个，而不是让@autowire注解报错
     * @Qualifier 根据名称进行注入，通常是在具有相同的多个类型的实例的一个注入（例如有多个DataSource类型的实例）
     */
    @Bean
    @Primary
    public DynamicDataSource dataSource(@Qualifier("mainDataSource") DataSource mainDataSource) {
        Map<Object, Object> targetDataSources = new HashMap<>();
        targetDataSources.put(DatasourceEnum.DATASOURCE_1, mainDataSource);
        //targetDataSources.put(DataBaseEnum.DATA_BASE_2, myTestDb2DataSource);

        DynamicDataSource dataSource = new DynamicDataSource();
        dataSource.setTargetDataSources(targetDataSources);// 该方法是AbstractRoutingDataSource的方法
        dataSource.setDefaultTargetDataSource(mainDataSource);// 默认的datasource设置为myTestDbDataSource
        return dataSource;
    }

    /**
     * 根据数据源创建SqlSessionFactory
     */
    //@Bean
//    public SqlSessionFactory sqlSessionFactory(com.wdkj.common.config.database.DynamicDataSource ds) throws Exception {
//        SqlSessionFactoryBean fb = new SqlSessionFactoryBean();
//        fb.setDataSource(ds);// 指定数据源(这个必须有，否则报错)
//        // 下边两句仅仅用于*.xml文件，如果整个持久层操作不需要使用到xml文件的话（只用注解就可以搞定），则不加
//        //fb.setTypeAliasesPackage(env.getProperty("mybatis.typeAliasesPackage"));// 指定基包
//        fb.setMapperLocations(
//                new PathMatchingResourcePatternResolver().getResources(env.getProperty("mybatis.mapperLocations")));
//        //其他配置
//        fb.setConfigLocation(
//                new DefaultResourceLoader().getResource("classpath:mybatis-config.xml"));
//        return fb.getObject();
//    }

    @Override
    public PlatformTransactionManager annotationDrivenTransactionManager() {
        return defaultTransactionManager;
    }
}
