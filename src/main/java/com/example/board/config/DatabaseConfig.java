package com.example.board.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import javax.sql.DataSource;

@Configuration
@PropertySource("classpath:/application.yml")
public class DatabaseConfig {

    @Autowired
    ApplicationContext applicationContext;

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.hikari")
    public HikariConfig hikariConfig(){
        return new HikariConfig();
    }

    @Bean
    public DataSource dataSource(){
        return new HikariDataSource(hikariConfig());
    }


    // SqlSessionFactoryBean은 FactoryBean 인터페이스의 구현 클래스로, 마이바티스(MyBatis)와 스프링의 연동 모듈로 사용됩니다.
    //쉽게 말해 factoryBean 객체는 데이터 소스를 참조하며, XML Mapper(SQL 쿼리 작성 파일)의 경로와 설정 파일 경로 등의 정보를 갖는 객체입니다.
    @Bean
    public SqlSessionFactory sqlSessionFactory() throws Exception {
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(dataSource());
//		factoryBean.setMapperLocations(context.getResources("classpath:/mappers/**/*Mapper.xml"));
        return factoryBean.getObject();
    }


    // SqlSessionFactory를 통해 생성되고, 공식 문서의 내용과 같이 DB의 커밋, 롤백 등 SQL의 실행에 필요한 모든 메서드를 갖는 객체
    @Bean
    public SqlSessionTemplate sqlSession() throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory());
    }
}
