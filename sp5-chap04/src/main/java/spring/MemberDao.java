package spring;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

//Member 객체 저장소
public class MemberDao {

    private static long nextId = 0;
    //키는 이메일
    private Map<String,Member> map = new HashMap<>();

    public Member selectByEmail(String email){
        return map.get(email);
    }

    public void insert(Member member){
        member.setId(++nextId);
        map.put(member.getEmail(),member);
    }

    public void update(Member member){
        map.put(member.getEmail(),member);
    }

    public Collection<Member> selectAll(){
        return map.values();
    }

}

