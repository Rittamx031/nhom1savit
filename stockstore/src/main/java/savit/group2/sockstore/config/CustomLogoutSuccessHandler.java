package savit.group2.sockstore.config;

import java.io.IOException;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class CustomLogoutSuccessHandler extends SimpleUrlLogoutSuccessHandler implements LogoutSuccessHandler {

  @Override
  public void onLogoutSuccess(final HttpServletRequest request, final HttpServletResponse response,
      final Authentication authentication) throws IOException, ServletException {
    final String refererUrl = request.getHeader("Referer");
    super.onLogoutSuccess(request, response, authentication);
  }

}
