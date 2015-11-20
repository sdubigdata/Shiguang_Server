package team.ideart.server.config;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.util.Properties;

/**
 * JPAConfig
 *
 * @author Allen Jin
 * @date 2015-11-06
 */

@Configuration
@EnableTransactionManagement
@PropertySource(value = "classpath:jdbc.properties")
@ComponentScan({"team.ideart.server.persist","team.ideart.server.service"})
@EnableJpaRepositories("team.ideart.server.persist")
public class JPAConfig {

    @Resource
    @Autowired
    private Environment env;

    /**
     * 配置数据源
     * @return
     */
    @Bean(name = "dataSource")
    public DataSource dataSource() {
        BasicDataSource datasource = new BasicDataSource();
        datasource.setDriverClassName(env.getProperty("jdbc.driver"));
        datasource.setUrl(env.getProperty("jdbc.url"));
        datasource.setUsername(env.getProperty("jdbc.username"));
        datasource.setPassword(env.getProperty("jdbc.password"));
        datasource.setInitialSize(Integer.valueOf(env.getProperty("dbcp.initialSize")));
        datasource.setMaxActive(Integer.valueOf(env.getProperty("dbcp.maxActive")));
        datasource.setMaxIdle(Integer.valueOf(env.getProperty("dbcp.maxIdle")));
        datasource.setMinIdle(Integer.valueOf(env.getProperty("dbcp.minIdle")));

        return datasource;
    }

    /**
     * Hibernate相关属性配置
     * @return
     */
    private Properties hibProperties() {
        Properties properties = new Properties();
        properties.put("hibernate.dialect", env.getProperty("hibernate.dialect"));
        properties.put("hibernate.show_sql", env.getProperty("hibernate.show_sql"));
        properties.put("hibernate.format_sql", env.getProperty("hibernate.format_sql"));
        properties.put("hibernate.hbm2ddl.auto", env.getProperty("hibernate.hbm2ddl.auto"));
        return properties;
    }

    @Bean(name = "entityManagerFactory")
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean() {

        LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
        HibernateJpaVendorAdapter jpaVendorAdapter = new HibernateJpaVendorAdapter();
        factory.setJpaVendorAdapter(jpaVendorAdapter);
        factory.setDataSource(dataSource());
        factory.setPackagesToScan("team.ideart.server.persist");
        factory.setJpaProperties(hibProperties());
        return factory;
    }

    @Bean(name = "transactionManager")
    public JpaTransactionManager jpaTransactionManager() {
        JpaTransactionManager jpaTransactionManager = new JpaTransactionManager();
        jpaTransactionManager.setEntityManagerFactory(entityManagerFactoryBean().getObject());
        return jpaTransactionManager;
    }

}
