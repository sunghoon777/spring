package interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/*
preHandle
dispatcher 가 controller(handler)에게 요청을 전달하기 전에 특정 작업을 할 수 있게 해준다. preHandle 이 true 가 되야만 controller 에게 요청이 전달되고 false 이면 전달이 되지 않는다.
postHandle
postHandle 은 컨트롤러가 정상적으로 실행이 되면 실행이 되고 예외 발생시 실행 안된다. 근데 컨트롤러에서 try catch 로 예외를 처리해버리면 예외는 dispatcher 까지 가지 않기 때문에 postHandle
afterCompletion
afterCompletion 는 뷰가 클라이언트에 응답을 전송을 하면 실행이 된다. 파라미터로 exception 을 받을 수 있어 로그 작성에 용이함.
 */

public class AuthCheckInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession httpSession = request.getSession();
        if(httpSession != null){
            Object authInfo = httpSession.getAttribute("authInfo");
            if(authInfo != null){
                return true;
            }
        }
        response.sendRedirect(request.getContextPath()+"/login");
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("exception not occur");
    }
}
