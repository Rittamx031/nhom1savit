package savit.group2.sockstore.config;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import savit.group2.sockstore.security.service.AccountInforService;
import savit.group2.sockstore.security.service.EmployeInfoService;

@Slf4j
public class JwtAuthenticationFilter extends OncePerRequestFilter {
  @Autowired
  private JwtTokenProvider tokenProvider;
  @Autowired
  private EmployeInfoService employeInfoService;
  @Autowired
  private AccountInforService accountInforService;

  @Override
  protected void doFilterInternal(HttpServletRequest request,
      HttpServletResponse response, FilterChain filterChain)
      throws ServletException, IOException {
    try {

      // Lấy jwt từ request
      String jwt = getJwtFromRequest(request);

      if (StringUtils.hasText(jwt) && tokenProvider.validateToken(jwt)) {
        // Lấyemail từ chuỗi jwt
        String userId = tokenProvider.getUsernameFromJWT(jwt);
        // Lấy thông tin người dùng từ email

        if (request.getRequestURI().contains("admin")) {
          UserDetails userDetails = employeInfoService.loadUserByUsername(userId);
          if (userDetails != null) {
            // Nếu người dùng hợp lệ, set thông tin cho Seturity Context
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails,
                null, userDetails.getAuthorities());
            authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(authentication);
          }
        }
        if (request.getRequestURI().contains("user")) {
          UserDetails userDetails2 = accountInforService.loadUserByUsername(userId);
          if (userDetails2 != null) {
            // Nếu người dùng hợp lệ, set thông tin cho Seturity Context
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails2,
                null, userDetails2.getAuthorities());
            authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(authentication);
          }
        }
      }
    } catch (Exception ex) {
      log.error("failed on set user authentication", ex);
    }
    filterChain.doFilter(request, response);
  }

  private String getJwtFromRequest(HttpServletRequest request) {
    String bearerToken = request.getHeader("Authorization");
    // Kiểm tra xem header Authorization có chứa thông tin jwt không
    if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
      return bearerToken.substring(7);
    }
    return null;
  }
}