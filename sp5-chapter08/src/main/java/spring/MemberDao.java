package spring;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.sql.*;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//Member 객체 저장소
public class MemberDao {

    private JdbcTemplate jdbcTemplate;

    public MemberDao(DataSource dataSource){
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public Member selectByEmail(String email){
        List<Member> list = jdbcTemplate.query("SELECT * FROM MEMBER WHERE EMAIL = ?", new RowMapper<Member>() {
            @Override
            public Member mapRow(ResultSet rs, int rowNum) throws SQLException {
                Member member = new Member(rs.getString("EMAIL"),rs.getString("PASSWORD"),
                        rs.getString("NAME"),rs.getTimestamp("REGDATE").toLocalDateTime());
                member.setId(rs.getLong("ID"));
                return member;
            }
        }, email);
        //jdbcTemplate.query 는 값이 존재하지 않으면 길이가 0 인 리스트 반환
        return list.isEmpty()?null:list.get(0);
    }

    public Member selectById(Long id){
        //queryForObject 는 쿼리 결과가 1행일 때 사용가능하다.
        Member member = jdbcTemplate.queryForObject("SELECT * FROM MEMBER WHERE ID = ?", new RowMapper<Member>() {
            @Override
            public Member mapRow(ResultSet rs, int rowNum) throws SQLException {
                Member member = new Member(rs.getString("EMAIL"),rs.getString("PASSWORD"),
                        rs.getString("NAME"),rs.getTimestamp("REGDATE").toLocalDateTime());
                member.setId(rs.getLong("ID"));
                return member;
            }
        }, id);
        return member;
    }

    public Collection<Member> selectAll(){
        List<Member> list = jdbcTemplate.query("SELECT * FROM MEMBER", new RowMapper<Member>() {
            @Override
            public Member mapRow(ResultSet rs, int rowNum) throws SQLException {
                Member member = new Member(rs.getString("EMAIL"),rs.getString("PASSWORD"),
                        rs.getString("NAME"),rs.getTimestamp("REGDATE").toLocalDateTime());
                member.setId(rs.getLong("ID"));
                return member;
            }
        });
        return list;
    }


    public int count(){
        //queryForObject 는 쿼리 결과가 1행일 때 사용가능하다.
        int count = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM MEMBER",Integer.class);
        return count;
    }

    public void insert(Member member){
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                PreparedStatement preparedStatement = con.prepareStatement("INSERT INTO MEMBER(EMAIL,PASSWORD,NAME,REGDATE) VALUES (?,?,?,?)",new String[]{"ID"});
                preparedStatement.setString(1, member.getEmail());
                preparedStatement.setString(2, member.getPassword());
                preparedStatement.setString(3, member.getName());
                preparedStatement.setTimestamp(4, Timestamp.valueOf(member.getRegisterDateTime()));
                return preparedStatement;
            }
        },keyHolder);
        Number keyValue = keyHolder.getKey();
        member.setId(keyValue.longValue());
    }

    //update
    public void update(Member member){
        jdbcTemplate.update("UPDATE MEMBER SET NAME = ? , PASSWORD = ? WHERE EMAIL = ?",member.getName(),member.getPassword(),member.getEmail());
    }

    /*
        다음과 같이 PreparedStatement 사용하여 update 도 가능하다.
        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                PreparedStatement preparedStatement = con.prepareStatement("UPDATE MEMBER SET NAME = ? , PASSWORD = ? WHERE EMAIL = ?");
                preparedStatement.setString(1, member.getName());
                preparedStatement.setString(1, member.getPassword());
                preparedStatement.setString(1, member.getEmail());
                return preparedStatement;
            }
        });
     */



}

