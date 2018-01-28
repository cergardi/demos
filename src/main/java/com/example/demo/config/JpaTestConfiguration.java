//package com.example.demo.config;
//
//
//import java.util.Properties;
//
//import javax.naming.NamingException;
//import javax.persistence.EntityManagerFactory;
//import javax.sql.DataSource;
//
//import org.apache.commons.lang3.StringUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
//import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Primary;
//import org.springframework.context.annotation.Profile;
//import org.springframework.core.env.Environment;
//import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
//import org.springframework.jdbc.datasource.DriverManagerDataSource;
//import org.springframework.orm.jpa.JpaTransactionManager;
//import org.springframework.orm.jpa.JpaVendorAdapter;
//import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
//import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
//import org.springframework.transaction.PlatformTransactionManager;
//import org.springframework.transaction.annotation.EnableTransactionManagement;
//
//import com.zaxxer.hikari.HikariDataSource;
//
//@Configuration
//@EnableJpaRepositories(basePackages = "com.example.demo.repositories",
//        entityManagerFactoryRef = "entityManagerFactory",
//        transactionManagerRef = "transactionManager")
//@EnableTransactionManagement
//public class JpaTestConfiguration {
//	
//	@Value("${datasource.sampleapp.maxPoolSize:10}")
//    private int maxPoolSize;
//	
//	@Autowired
//    private Environment environment;
//	
//	/*
//     * Populate SpringBoot DataSourceProperties object directly from application.yml 
//     * based on prefix.Thanks to .yml, Hierachical data is mapped out of the box with matching-name
//     * properties of DataSourceProperties object].
//     */
//    @Bean
//    @Primary
//    @ConfigurationProperties(prefix = "datasource.sampleapp")
//    public DataSourceProperties dataSourceProperties(){
//        return new DataSourceProperties();
//    }
//    
//	@Bean
//    @Profile("test")
//    public DataSource dataSource() {
//		DataSourceProperties dataSourceProperties = dataSourceProperties();
//        HikariDataSource dataSource = (HikariDataSource) DataSourceBuilder
//                .create(dataSourceProperties.getClassLoader())
//                .driverClassName("com.mysql.jdbc.Driver")
//                .url("jdbc:mysql://localhost/demousuariosext?useSSL=false")
//                .username("cergardi")
//                .password("password")
//                .type(HikariDataSource.class)
//                .build();
//        
//        dataSource.setMaximumPoolSize(maxPoolSize);
//        return dataSource;
////        	
////        dataSource.setMaximumPoolSize(maxPoolSize);
////        return dataSource;
////        DriverManagerDataSource dataSource = new DriverManagerDataSource();
////        dataSource.setDriverClassName("org.h2.Driver");
////        dataSource.setUrl("jdbc:h2:mem:db;DB_CLOSE_DELAY=-1");
////        dataSource.setUsername("sa");
////        dataSource.setPassword("sa");
//// 
////        return dataSource;
//    }
//	
//	/*
//     * Entity Manager Factory setup.
//     */
//    @Bean
//    public LocalContainerEntityManagerFactoryBean entityManagerFactory() throws NamingException {
//        LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();
//        factoryBean.setDataSource(dataSource());
//        factoryBean.setPackagesToScan(new String[] { "com.example.demo.dto" });
//        factoryBean.setJpaVendorAdapter(jpaVendorAdapter());
//        factoryBean.setJpaProperties(jpaProperties());
//        return factoryBean;
//    }
// 
//    /*
//     * Provider specific adapter.
//     */
//    @Bean
//    public JpaVendorAdapter jpaVendorAdapter() {
//        HibernateJpaVendorAdapter hibernateJpaVendorAdapter = new HibernateJpaVendorAdapter();
//        return hibernateJpaVendorAdapter;
//    }
// 
//    /*
//     * Here you can specify any provider specific properties.
//     */
//    private Properties jpaProperties() {
//        Properties properties = new Properties();
//        properties.put("hibernate.dialect", environment.getRequiredProperty("datasource.sampleapp.hibernate.dialect"));
//        properties.put("hibernate.hbm2ddl.auto", environment.getRequiredProperty("datasource.sampleapp.hibernate.hbm2ddl.method"));
//        properties.put("hibernate.show_sql", environment.getRequiredProperty("datasource.sampleapp.hibernate.show_sql"));
//        properties.put("hibernate.format_sql", environment.getRequiredProperty("datasource.sampleapp.hibernate.format_sql"));
//        if(StringUtils.isNotEmpty(environment.getRequiredProperty("datasource.sampleapp.defaultSchema"))){
//            properties.put("hibernate.default_schema", environment.getRequiredProperty("datasource.sampleapp.defaultSchema"));
//        }
//        return properties;
//    }
// 
//    @Bean
//    @Autowired
//    public PlatformTransactionManager transactionManager(EntityManagerFactory emf) {
//        JpaTransactionManager txManager = new JpaTransactionManager();
//        txManager.setEntityManagerFactory(emf);
//        return txManager;
//    }
//
//}
