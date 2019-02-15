package com.hrabbit.admin.config.datasource;

import com.alibaba.druid.pool.DruidDataSource;
import com.baomidou.mybatisplus.enums.DBType;
import com.baomidou.mybatisplus.plugins.PaginationInterceptor;
import com.hrabbit.admin.config.properties.DruidProperties;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * 数据源配置
 *
 * @Auther: hrabbit
 * @Date: 2018-12-19 11:18 AM
 * @Description:
 */
@Configuration
@EnableTransactionManagement
@MapperScan({"com.hrabbit.admin.modual.system.mapper"})
public class SingleDataSourceConfig {

    /**
     * Druid配置
     */
    @Bean
    public DruidProperties druidProperties() {
        return new DruidProperties();
    }

    /**
     * 单数据源连接池配置
     */
    @Bean
    public DruidDataSource dataSource(DruidProperties druidProperties) {
        DruidDataSource dataSource = new DruidDataSource();
        druidProperties.config(dataSource);
        return dataSource;
    }

    /**
     * mybatis-plus分页插件
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        PaginationInterceptor paginationInterceptor = new PaginationInterceptor();
        paginationInterceptor.setDialectType(DBType.MYSQL.getDb());
        return paginationInterceptor;
    }
}
