package com.sanbon.backend.config;

import com.sanbon.backend.component.advice.handler.ExceptionHandlerFilter;
import com.sanbon.backend.component.utils.JwtUtil;
import com.sanbon.backend.component.utils.RedisUtil;
import com.sanbon.backend.config.jwt.JwtAuthenticationFilter;
import com.sanbon.backend.config.jwt.JwtAuthorizationFilter;
import com.sanbon.backend.repository.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity // 필터 체인 관리 시작 어노테이션
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private CorsConfig corsConfig;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ExceptionHandlerFilter exceptionHandlerFilter;

    @Bean
    public BCryptPasswordEncoder encodePwd() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .addFilter(corsConfig.corsFilter())
                .csrf().disable()
                // 세션 사용 안 함
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .formLogin().disable()
                // Basic: Authorization에 id, pw 넣어서 보냄
                // Bearer: Authorization에 token 넣어서 보냄
                .httpBasic().disable()
                .addFilter(new JwtAuthenticationFilter(authenticationManager(), jwtUtil, redisUtil))
                .addFilter(new JwtAuthorizationFilter(authenticationManager(), jwtUtil, userRepository, redisUtil))
                .authorizeRequests()
                .antMatchers("/user/**")
                .access("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
                .antMatchers("/admin/**")
                .access("hasRole('ROLE_ADMIN')")
                .anyRequest().permitAll();
        http.addFilterBefore(exceptionHandlerFilter, JwtAuthorizationFilter.class);
    }
}
