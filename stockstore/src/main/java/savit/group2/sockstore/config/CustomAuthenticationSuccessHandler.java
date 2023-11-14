package savit.group2.sockstore.config;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.util.StringUtils;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import savit.group2.sockstore.model.entity.Account;
import savit.group2.sockstore.model.entity.Employee;
import savit.group2.sockstore.security.service.AccountInforService;
import savit.group2.sockstore.security.service.EmployeInfoService;

public class CustomAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
    protected final Log logger = LogFactory.getLog(this.getClass());
    private RequestCache requestCache = new HttpSessionRequestCache();

    public CustomAuthenticationSuccessHandler(AccountInforService userService, EmployeInfoService employeeService) {
        this.employeeService = employeeService;
        this.userService = userService;
        setUseReferer(true); // use referer
    }

    AccountInforService userService;
    EmployeInfoService employeeService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
            Authentication authentication) throws ServletException, IOException {
        setSessionAuthenLogin(request, response, authentication);
        SavedRequest savedRequest = this.requestCache.getRequest(request, response);
        if (savedRequest == null) {
            String targetUrl = determineTargetUrl(authentication);
            this.getRedirectStrategy().sendRedirect(request, response, targetUrl);
        } else {
            String targetUrlParameter = this.getTargetUrlParameter();
            if (!this.isAlwaysUseDefaultTargetUrl()
                    && (targetUrlParameter == null || !StringUtils.hasText(request.getParameter(targetUrlParameter)))) {
                this.clearAuthenticationAttributes(request);
                String targetUrl = savedRequest.getRedirectUrl();
                this.getRedirectStrategy().sendRedirect(request, response, targetUrl);
            } else {
                this.requestCache.removeRequest(request, response);
                super.onAuthenticationSuccess(request, response, authentication);
            }
        }
    }

    protected String determineTargetUrl(Authentication authentication) {
        String url = "";
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        List<String> roles = new ArrayList<String>();
        for (GrantedAuthority a : authorities) {
            roles.add(a.getAuthority());
        }
        if (roles.contains("CHANGE_PASSWORD")) {
            url = "/resetpassword";
        } else if (roles.contains("NOT_ACCTIVE")) {
            url = "/sendvertifyemail";
        } else if (roles.contains("ADMIN")) {
            url = "/homepage";
        } else if (roles.contains("USER")) {
            url = "/sock";
        }
        return url;
    }

    public void setSessionAuthenLogin(HttpServletRequest request, HttpServletResponse response,
            Authentication authentication) {
        HttpSession session = request.getSession();
        session.setAttribute("authenusername", authentication.getName());

        Account user = userService.getAccountByEmail(authentication.getName());
        Employee employee = employeeService.getEmployeeByEmail(authentication.getName());

        session.setAttribute("authenuser", user);
        session.setAttribute("authenemployee", employee);
    }
}
