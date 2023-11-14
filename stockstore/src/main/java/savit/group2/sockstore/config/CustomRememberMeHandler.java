package savit.group2.sockstore.config;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.rememberme.AbstractRememberMeServices;
import org.springframework.security.web.authentication.rememberme.RememberMeAuthenticationException;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import savit.group2.sockstore.model.entity.Account;
import savit.group2.sockstore.model.entity.Employee;
import savit.group2.sockstore.security.service.AccountInforService;
import savit.group2.sockstore.security.service.EmployeInfoService;

public class CustomRememberMeHandler extends AbstractRememberMeServices {

    public CustomRememberMeHandler(AccountInforService userService, EmployeInfoService employeeService, String key,
            UserDetailsService userDetailsService) {
        super(key, userDetailsService);
        this.userService = userService;
        this.employeeService = employeeService;
    }

    AccountInforService userService;
    EmployeInfoService employeeService;

    public void setSessionAuthenLogin(HttpServletRequest request, HttpServletResponse response,
            Authentication authentication) {
        HttpSession session = request.getSession();
        session.setAttribute("authenusername", authentication.getName());

        Account user = userService.getAccountByEmail(authentication.getName());
        Employee employee = employeeService.getEmployeeByEmail(authentication.getName());

        session.setAttribute("authenuser", user);
        session.setAttribute("authenemployee", employee);
    }

    @Override
    protected void onLoginSuccess(HttpServletRequest request, HttpServletResponse response,
            Authentication successfulAuthentication) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'onLoginSuccess'");
    }

    @Override
    protected UserDetails processAutoLoginCookie(String[] cookieTokens, HttpServletRequest request,
            HttpServletResponse response) throws RememberMeAuthenticationException, UsernameNotFoundException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'processAutoLoginCookie'");
    }
}
