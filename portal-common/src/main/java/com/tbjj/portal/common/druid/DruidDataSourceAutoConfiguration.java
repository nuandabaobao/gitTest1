package com.tbjj.portal.common.druid;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * @author Fu JinHui
 */
@ConditionalOnProperty(prefix = "spring.datasource", name = "type", havingValue = "com.alibaba.druid.pool.DruidDataSource")
@Configuration
public class DruidDataSourceAutoConfiguration {

    @ConditionalOnProperty(prefix = "spring.datasource", name = "filters")
    @Configuration
    public static class FilterConfig {
        @Autowired
        public void configDruidDataSource(DataSource dataSource,
                                          @Value("${spring.datasource.maxIdle}") Integer maxIdle,
                                          @Value("${spring.datasource.minIdle}") Integer minIdle,
                                          @Value("${spring.datasource.maxWait}") Long maxWait,
                                          @Value("${spring.datasource.maxActive}") Integer maxActive,
                                          @Value("${spring.datasource.initalSize}") Integer initalSize,
                                          @Value("${spring.datasource.filters}") String filters) throws SQLException {
            DruidDataSource druidDataSource = (DruidDataSource) dataSource;

            druidDataSource.setFilters(filters);
            druidDataSource.setMaxActive(maxActive);
            druidDataSource.setMinIdle(minIdle);
            druidDataSource.setMaxIdle(maxIdle);
            druidDataSource.setMaxWait(maxWait);
            druidDataSource.setInitialSize(initalSize);
        }
    }

}
