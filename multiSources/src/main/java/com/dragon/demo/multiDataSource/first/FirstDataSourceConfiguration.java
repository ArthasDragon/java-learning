package com.dragon.demo.multiDataSource.first;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        basePackages = {"com.dragon.demo.multiDataSource.first"},
        entityManagerFactoryRef = "entityManagerFactoryFirst",
        transactionManagerRef = "transactionManagerFirst"
)
public class FirstDataSourceConfiguration {
    /**
     * 数据源一
     */
    private final DataSource firstDataSource;


    /**
     * 构造方式注入依赖
     */
    @Autowired
    public FirstDataSourceConfiguration(@Qualifier("firstDataSource") DataSource firstDataSource) {
        this.firstDataSource = firstDataSource;
    }


    /**
     * 配置entityManager工厂
     */
    @Primary
    @Bean(name = "entityManagerFactoryFirst")
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryFirst(EntityManagerFactoryBuilder builder) {
        return builder
                //设置数据源
                .dataSource(firstDataSource)
                //设置entity的包路径
                .packages("com.dragon.demo.multiDataSource.first")
                .build();
    }

    /**
     * 配置事务管理器
     */
    @Primary
    @Bean(name = "transactionManagerFirst")
    public PlatformTransactionManager transactionManagerFirst(EntityManagerFactoryBuilder builder) {
        //返回jpa事务管理器
        return new JpaTransactionManager(entityManagerFactoryFirst(builder).getObject());
    }
}
