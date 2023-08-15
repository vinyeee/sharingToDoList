package dev.namiga.sharingToDoList.config;

import dev.namiga.sharingToDoList.infra.NaverOAuth2Service;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.SecurityFilterChain;
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig {

    private static final String[] PERMIT_URL_ARRAY = {
            "/css/**", // 예시로 public 경로에 대해 인증 없이 접근을 허용하는 경우
            "/img/**" // 추가적인 public 경로
    };

    private final NaverOAuth2Service naverOAuth2Service;
    @Bean
    protected SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/user/**")
                .anonymous()
                .antMatchers(PERMIT_URL_ARRAY)
                .permitAll()
                .antMatchers("/sharingToDo/**")
                .permitAll()
                .anyRequest()
                .permitAll()
                .and()
                .formLogin()
                .loginPage("/user/login")
                .and()
                .oauth2Login()
                .userInfoEndpoint()
                .userService(this.naverOAuth2Service)
                .and()
                .defaultSuccessUrl("/sharingToDo/main")
                .permitAll()
                .and()
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/main")
                .deleteCookies("JSEESIONID")
                .invalidateHttpSession(true)
                .permitAll()
        ;

        return http.build();
    }
}