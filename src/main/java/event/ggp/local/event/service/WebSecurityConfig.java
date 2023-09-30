package event.ggp.local.event.service;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import lombok.RequiredArgsConstructor;
 
/**
 * Spring Securityの設定を行うクラス。
 * 1,ConfigurationとEnableWebSecurityアノテーションを付ける
 * 2,SecurityFilterChainを返すメソッドにBeanアノテーションを付ける
 * の2つが必要。
 * パスワードをハッシュ化する場合は
 * 3.PasswordEncoderを返すメソッドにBeanアノテーションを付ける
 * も行う必要あり。
 * 
 */
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig {
    /**
     * 基本的な設定はここで行う。
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        // @formatter:off
  
        // アクセス権限に関する設定
        http
            .authorizeHttpRequests(
                // アクセス制限をかけないページを指定
                (requests) -> requests.requestMatchers("/management/initialize", "/management/login", "/management/logout","/css/**","/js/**","/img/**").permitAll()
                .anyRequest().authenticated())
            .formLogin(form -> form
                .loginPage("/management/login")
                .defaultSuccessUrl("/management/index")
            )
            .logout((logout) -> logout
                .logoutRequestMatcher(new AntPathRequestMatcher("/management/logout")) 
                .logoutUrl("/management/logout")
                .logoutSuccessUrl("/management/login?logout")
            );
        // @formatter:on
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
      
}