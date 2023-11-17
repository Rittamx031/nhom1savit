package savit.group2.sockstore.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.RememberMeServices;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import savit.group2.sockstore.security.repo.AccountInfoRepository;
import savit.group2.sockstore.security.repo.EmployeeInfoRepository;
import savit.group2.sockstore.security.service.AccountInforService;
import savit.group2.sockstore.security.service.EmployeInfoService;

@Configuration
@EnableMethodSecurity
@EnableWebSecurity
public class SpringSecurityConfig {
  @Value("${max-age-token-cookie}")
  private int maxAge;

  @Bean
  AuthenticationManager authenticationManager() throws Exception {
    List<AuthenticationProvider> listProviders = new ArrayList<>();
    listProviders.add(authenticationNVProvider());
    listProviders.add(authenticationKHProvider());
    ProviderManager providerManagers = new ProviderManager(listProviders);
    return providerManagers;
  }

  @Autowired
  EmployeeInfoRepository nvifrepository;

  EmployeInfoService nhanVienService() {
    return new EmployeInfoService(nvifrepository);
  }

  @Bean
  AuthenticationProvider authenticationNVProvider() {
    DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
    authenticationProvider.setUserDetailsService(nhanVienService());
    authenticationProvider.setPasswordEncoder(passwordEncoder());
    return authenticationProvider;
  }

  @Autowired
  AccountInfoRepository khifrepository;

  AccountInforService khachHangService() {
    return new AccountInforService(khifrepository);
  }

  @Bean
  JwtAuthenticationFilter jwtAuthenticationFilter() {
    return new JwtAuthenticationFilter();
  }

  @Bean
  AuthenticationProvider authenticationKHProvider() {
    DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
    authenticationProvider.setUserDetailsService(khachHangService());
    authenticationProvider.setPasswordEncoder(passwordEncoder());
    return authenticationProvider;
  }

  @Bean
  LogoutSuccessHandler logoutSuccessHandler() {
    return new CustomLogoutSuccessHandler();
  }

  @Bean
  AccessDeniedHandler accessDeniedHandler() {
    return new CustomAccessDeniedHandler();
  }

  // mã hóa mật khẩu
  @Bean
  PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  SecurityFilterChain filterChain(HttpSecurity http, AuthenticationManager authManager)
      throws Exception {
    http
        .authorizeHttpRequests((authorize) -> {
          authorize
              .requestMatchers("/test/**", "user/signup", "employee/signup", "employee/singin",
                  "/sendresetpasswordcode", "/resetpassword", "/sendresetpassword", "/resetpasswordcode")
              .permitAll();
        })
        .authorizeHttpRequests((authorize) -> {
          authorize.requestMatchers("/sock/**").hasAnyAuthority("ADMIN", "STAFF", "USER");
        })
        // hasRoles  "ROLE_"
        // admin roles "ADMIN,STAFF"
        // staff "STAFF"
        .authorizeHttpRequests((authorize) -> {
          authorize.requestMatchers("/admin/**", "/rest/**").hasAnyAuthority("ADMIN");
        })
        .authorizeHttpRequests((authorize) -> {
          authorize.requestMatchers("/admin/customer").hasAnyAuthority("STAFF");
        })
        .authorizeHttpRequests((authorize) -> {
          authorize.requestMatchers("/cart/**").hasAnyAuthority("ADMIN", "SUPER_ADMIN", "USER");
        })
        .authorizeHttpRequests((authorize) -> {
          authorize.requestMatchers("/admin/**").hasAnyAuthority("ADMIN", "SUPER_ADMIN");
        })
        .authorizeHttpRequests((authorize) -> {
          authorize.requestMatchers("/vertifyemail", "/sendvertifyemail").hasAuthority("NOT_ACCTIVE");
        })
        .authorizeHttpRequests((authorize) -> {
          authorize.anyRequest().permitAll();
        })
        .formLogin(formLogin -> formLogin
            .loginPage("/login")
            .loginProcessingUrl("/singin")
            .failureUrl("/login?error=true")
            .usernameParameter("username")
            .passwordParameter("password")
            .successHandler(new CustomAuthenticationSuccessHandler(khachHangService(), nhanVienService()))
            .permitAll())
        .logout(
            formLogin -> formLogin
                .logoutUrl("/signOut")
                .logoutSuccessHandler(logoutSuccessHandler())
                .permitAll())
        .csrf(AbstractHttpConfigurer::disable)
        .rememberMe((remember) -> remember.key("fefe").tokenValiditySeconds(maxAge)
            .rememberMeCookieName("remember-token")
            .userDetailsService(khachHangService()))
        .rememberMe((remember) -> remember.key("faewfaewf").tokenValiditySeconds(maxAge)
            .rememberMeCookieName("remember-admin-token")
            .userDetailsService(nhanVienService()))
        .httpBasic(Customizer.withDefaults())
        .authenticationManager(authManager);
    return http.build();
  }
}
