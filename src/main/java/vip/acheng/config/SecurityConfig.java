package vip.acheng.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    
    @Bean
    public PasswordEncoder passwordEncoder() {
        // 不给密码进行加密
        return NoOpPasswordEncoder.getInstance();
    }

    /**
     * 认证相关配置
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // 此处定义了 会覆盖配置文件中定义的用户，配置文件中定义的用户不失效
        auth.inMemoryAuthentication()
            .withUser("huzc1")
            .password("123456").roles("admin");
    }

    /**
     * 网站相关配置
     */
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/js/**", "/css/**","/images/**");
    }

    /**
     * 请求相关配置
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
            .anyRequest().authenticated()
            .and()
            .formLogin()
            .loginPage("/login.html")
            .permitAll()
            .and()
            .csrf().disable();
    }
}
