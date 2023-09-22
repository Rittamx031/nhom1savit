package savit.group2.sockstore.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import savit.group2.sockstore.security.repo.AccountInforRepository;
import savit.group2.sockstore.security.repo.EmployeeInfoRepository;
import savit.group2.sockstore.security.service.AccountInforService;
import savit.group2.sockstore.security.service.EmployeInfoService;

@Configuration
@EnableMethodSecurity
@EnableWebSecurity
public class SpringSecurityConfig {
  @Bean
  AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
    return http.getSharedObject(AuthenticationManagerBuilder.class)
        .authenticationProvider(authenticationNVProvider())
        .authenticationProvider(authenticationKHProvider())
        .build();
  }

  @Autowired
  EmployeeInfoRepository nvifrepository;

  EmployeInfoService nhanVienServer() {
    return new EmployeInfoService(nvifrepository);
  }

  @Bean
  AuthenticationProvider authenticationNVProvider() {
    DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
    authenticationProvider.setUserDetailsService(nhanVienServer());
    authenticationProvider.setPasswordEncoder(passwordEncoder());
    return authenticationProvider;
  }

  @Autowired
  AccountInforRepository khifrepository;

  AccountInforService KhachHangServer() {
    return new AccountInforService(khifrepository);
  }

  @Bean
  JwtAuthenticationFilter jwtAuthenticationFilter() {
    return new JwtAuthenticationFilter();
  }

  @Bean
  AuthenticationProvider authenticationKHProvider() {
    DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
    authenticationProvider.setUserDetailsService(KhachHangServer());
    authenticationProvider.setPasswordEncoder(passwordEncoder());
    return authenticationProvider;
  }

  // mã hóa mật khẩu
  @Bean
  PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  SecurityFilterChain filterChain(HttpSecurity http, AuthenticationManager authManager)
      throws Exception {
    http.

        authorizeHttpRequests((authorize) -> {
          authorize.requestMatchers("khach-hang/singin").permitAll();
        })
        .authorizeHttpRequests((authorize) -> {
          authorize.anyRequest().permitAll();
        })
        .formLogin(formLogin -> formLogin
            .loginPage("/login")
            .loginProcessingUrl("/singin")
            .failureUrl("/login?error")
            .defaultSuccessUrl("/homepage")
            .usernameParameter("username")
            .passwordParameter("password")
            .permitAll())
        .logout(
            formLogin -> formLogin
                .logoutUrl("/signOut")
                .logoutSuccessUrl("/login")

                .permitAll())
        .csrf(AbstractHttpConfigurer::disable)
        // .httpBasic(Customizer.withDefaults())
        .authenticationManager(authManager);
    return http.build();
  }

}
