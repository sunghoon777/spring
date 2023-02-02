package config;

import login.AuthService;
import member.ChangePasswordService;
import member.MemberDao;
import member.MemberRegisterService;
import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
public class MemberConfig {

    //커낵션풀 빈객체
    @Bean(destroyMethod = "close")
    public DataSource dataSource(){
        DataSource dataSource = new DataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost" +
                "/spring5?characterEncoding=utf8&useSSL=false");
        dataSource.setUsername("sunghoon");
        dataSource.setPassword("qkqxld12!@");
        //초기 커넥션풀 사이즈는 2개
        dataSource.setInitialSize(2);
        //최대 활성되는 커낵션은 10개
        dataSource.setMaxActive(10);
        //유효 검사할 쿼리 지정
        dataSource.setValidationQuery("SELECT 1");
        //유휴 상태일 때 커낵션이 유효한지 검사함.
        dataSource.setTestWhileIdle(true);
        //유휴 시간이 5분 지나면 이 커넥션을 커넥션풀에서 제거, DBMS 에서도 커넥션이 한동한 쿼리가 일어나지 않으면 DBMS 자체에서 커넥션을 끊는 설정을 할 수 있다.
        dataSource.setMinEvictableIdleTimeMillis(1000*60*5);
        //10초마다 유휴 상태인 커넥션들을 검사한다. 이 값을 1초 이하로 설정하면 안된다.
        dataSource.setTimeBetweenEvictionRunsMillis(1000*10);
        return dataSource;
    }

    //플랫폼 트랜잭션 매니저 빈객체
    @Bean
    public PlatformTransactionManager transactionManager(){
        return new DataSourceTransactionManager(dataSource());
    }

    @Bean
    public MemberDao memberDao(){
        return new MemberDao(dataSource());
    }

    @Bean
    public MemberRegisterService memberRegisterService(){
        return new MemberRegisterService(memberDao());
    }

    @Bean
    public ChangePasswordService changePasswordService(){
        return new ChangePasswordService(memberDao());
    }

    @Bean
    public AuthService authService(){
        return new AuthService(memberDao());
    }


}
