package edu.poly.Service;


import edu.poly.model.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Service
public class AuthInterceptor  implements HandlerInterceptor {
    @Autowired
    SessionService session;
    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) throws Exception {
        String uri = request.getRequestURI();
        Account user = session.get("user"); // lấy từ session
        String error = "";
        if(user == null) { // chưa đăng nhập
            error = "Please login!";
            response.sendRedirect("/account/login?error=" + error);
        }
// không đúng vai tròx`
        else if(!user.isAdmin() && uri.startsWith("/Admin/**")) {
            error = "Access denied!";
            response.sendRedirect("/account/login?error=" + error);
        }
//        if(error.length() > 0) { // có lỗi
//            session.set("security-uri", uri);
//            response.sendRedirect("/account/login?error=" + error);
//            return false;
//        }
        return true;
//        if(request.getSession().getAttribute("user") ==null){
//            request.getSession().setAttribute("secureUri",request.getRequestURI());
//            response.sendRedirect("/account/login");
//        }
    }
}

