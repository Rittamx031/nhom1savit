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

import savit.group2.sockstore.security.repo.AccountInforRepository;
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
  AccountInforRepository khifrepository;

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

  // mã hóa mật khẩu
  @Bean
  PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  SecurityFilterChain filterChain(HttpSecurity http, AuthenticationManager authManager)
      throws Exception {
    http.authorizeHttpRequests((authorize) -> {
      authorize.requestMatchers("/sock/**").hasAnyAuthority("ADMIN", "STAFF", "USER");
    })
        .authorizeHttpRequests((authorize) -> {
          authorize.requestMatchers("/admin/**", "/rest/**").hasAnyAuthority("ADMIN");
        })
            .authorizeHttpRequests((authorize) -> {
              authorize.requestMatchers("/admin/customer").hasAnyAuthority("STAFF");
            })
        .authorizeHttpRequests((authorize) -> {
          authorize.requestMatchers("user/singin", "employee/signin").permitAll();
        })
        .authorizeHttpRequests((authorize) -> {
          authorize.requestMatchers("employee/singin").permitAll();
        })
        .authorizeHttpRequests((authorize) -> {
          authorize.anyRequest().permitAll();
        })
        .formLogin(formLogin -> formLogin
            .loginPage("/login")
            .loginProcessingUrl("/singin")
            .failureUrl("/login?error")
            .successHandler(new CustomAuthenticationSuccessHandler())
            .usernameParameter("username")
            .passwordParameter("password")
            .permitAll())
        .logout(
            formLogin -> formLogin
                .logoutUrl("/signOut")
                .logoutSuccessUrl("/login")
                .permitAll())
        .csrf(AbstractHttpConfigurer::disable)
        .rememberMe((remember) -> remember.key("fefe").tokenValiditySeconds(maxAge)
            .userDetailsService(khachHangService()))
        .rememberMe((remember) -> remember.key("faewfaewf").tokenValiditySeconds(maxAge)
            .userDetailsService(nhanVienService()))
        .httpBasic(Customizer.withDefaults())
        .authenticationManager(authManager);
    return http.build();
  }
}
