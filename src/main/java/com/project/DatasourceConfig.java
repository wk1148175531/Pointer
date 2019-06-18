package com.project;

import java.io.IOException;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

//扫描 Mapper接口并容器管理
@Configuration
@EnableTransactionManagement
@MapperScan(basePackages = "com.project.mapper", sqlSessionFactoryRef = "sqlSessionFactory")
public class DatasourceConfig {

	@Value("${spring.datasource.dbcp2.driver}")
	private String driver;
	@Value("${spring.datasource.dbcp2.url}")
	private String url;
	@Value("${spring.datasource.dbcp2.username}")
	private String username;
	@Value("${spring.datasource.dbcp2.password}")
	private String password;
	@Value("${mybatis.config-location}")
	private String mybatis_conf_path;
	@Value("${mybatis.mapper-locations}")
    private String mapperPackage;
   @Bean(name="dataSource",destroyMethod="close")
   @Primary
   public BasicDataSource createDataSource()
   {
	   BasicDataSource dataSource=new BasicDataSource();
	   dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
	   dataSource.setUrl(url);
	   dataSource.setUsername(username);
	   dataSource.setPassword(password);
	   return dataSource;
   }

    @Bean(name = "TransactionManager")
    @Primary
    public DataSourceTransactionManager createTransactionManager() {
        return new DataSourceTransactionManager(createDataSource());
    }
 
    @Bean("sqlSessionFactory")
    @Primary
    @Autowired
	public SqlSessionFactoryBean createSessionFactoryBean(@Qualifier("dataSource") DataSource dataSource) throws IOException
	{
		SqlSessionFactoryBean sessionFactoryBean=new SqlSessionFactoryBean();
		sessionFactoryBean.setConfigLocation(new ClassPathResource(mybatis_conf_path));
		PathMatchingResourcePatternResolver resolver=new PathMatchingResourcePatternResolver();
		String packagePath=PathMatchingResourcePatternResolver.CLASSPATH_ALL_URL_PREFIX+mapperPackage;
		sessionFactoryBean.setMapperLocations(resolver.getResources(packagePath));
		sessionFactoryBean.setDataSource(dataSource);
		return sessionFactoryBean;
	}
}
