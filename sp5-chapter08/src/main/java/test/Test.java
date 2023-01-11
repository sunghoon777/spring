package test;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

//Transactional 테스트용 클래스
public class Test {

    private JdbcTemplate jdbcTemplate;
    private List<Integer> list = new ArrayList<>();

    public Test(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void a(){
        //DataSourceTransactionManager 는 jdbcTemplate 을 이용해서 적용된 내용만 rollback 해주고 나머지는 jdbcTemplate 를 이용하지 않는 것은 rollback 해주지 않는다.
        list.add(1);
        jdbcTemplate.update("INSERT INTO MEMBER(EMAIL,PASSWORD,NAME) VALUES ('test355','test355','test5355')");
        b();
    }

    public void b(){
        c();
    }

    @Transactional(propagation = Propagation.REQUIRED)
    //Propagation.REQUIRED 는 부모가 트랜잭션이 아니더라도 조상 중에 트랜잭션이 있으면 그것을 그대로 이어받는다.
    public void c(){
        throw new RuntimeException();
    }

    public int count(){
        return list.size();
    }

}
