package RestApiController;

import member.ExistingMemberException;
import member.Member;
import member.MemberDao;
import member.MemberRegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import register.RegisterRequest;
import register.RegisterRequestValidator;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

// @RestController 를 적용하면 메서드가 리턴한 객체를 알맞은 형식으로 변환함. 클래스 패스에 jackson 이 존재하면 json 을 반환한다.
// * json 으로 들어오는 날짜값을 담은 문자열 데이터는 기본으로 다음 패턴으로 날짜형으로 바꾸어준다(yyyy-MM-ddTHH:mm:ss)
@RestController
public class RestMemberController {

    @Autowired
    private MemberDao memberDao;

    @Autowired
    private MemberRegisterService memberRegisterService;

    @GetMapping("/api/members")
    public List<Member> members(){
        List<Member> list = (List<Member>) memberDao.selectAll();
        return list;
    }

    //@RequestBody 어노테이션을 적용하면 json 값을 커맨더객처로 변환하여 받을 수 있다.
    //참고로 여기선 리턴형이 void 이기 때문에 Http response body 에는 json 데이터가 들어가지 않는다.
    @PostMapping("/api/members")
    public ResponseEntity<Object> newMember(@RequestBody RegisterRequest registerRequest, Errors errors) throws IOException {
        try {
            new RegisterRequestValidator().validate(registerRequest,errors);
            if(errors.hasErrors()){
                return ResponseEntity.status(HttpServletResponse.SC_BAD_REQUEST).body(new ErrorResponse("bad request"));
            }
            Long memberId = memberRegisterService.regist(registerRequest);
            String locationValue = "/api/members/"+memberId;
            return ResponseEntity.status(HttpServletResponse.SC_CREATED).header("location",locationValue).build();
        }catch (ExistingMemberException e){
            return ResponseEntity.status(HttpServletResponse.SC_CONFLICT).body(new ErrorResponse("existing member"));
        }
    }

    // response.sendError 를 통해 에러를 넣으면 서버에서 기본으로 제공해주는 html 을 전송한다. 그래서 ResponseEntity 를 통하여 error 도 json 으로 보내게 함.
    @GetMapping("/api/members/{id}")
    public ResponseEntity<Object> member(@PathVariable("id") Long id, HttpServletResponse response) throws IOException {
        Member member = memberDao.selectById(id);
        if(member == null){
            return ResponseEntity.status(HttpServletResponse.SC_NOT_FOUND).body(new ErrorResponse("no member"));
        }
        return ResponseEntity.status(HttpStatus.OK).body(member);
    }


}
