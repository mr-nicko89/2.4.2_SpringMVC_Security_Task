package my.app.config;

import my.app.config.handler.LoginSuccessHandler;
import my.app.models.Role;
import my.app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

//Содержание полностью скопировано из 2.4.2_Example
//Аутентификация inMemory
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private UserService userService;

    @Autowired
    public SecurityConfig(UserService userService) {
        this.userService = userService;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin()
                // указываем страницу с формой логина
                .loginPage("/login")
                //указываем логику обработки при логине
                .successHandler(new LoginSuccessHandler())
                // указываем action с формы логина
                .loginProcessingUrl("/login")
                // Указываем параметры логина и пароля с формы логина
                .usernameParameter("j_username")
                .passwordParameter("j_password")
                // даем доступ к форме логина всем
                .permitAll();

        http.logout()
                // разрешаем делать логаут всем
                .permitAll()
                // указываем URL логаута
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                // указываем URL при удачном логауте
                .logoutSuccessUrl("/login?logout")
                //выклчаем кроссдоменную секьюрность (на этапе обучения неважна)
                .and().csrf().disable();

        http
                // делаем страницу регистрации недоступной для авторизированных пользователей
                .csrf().disable()
                .authorizeRequests()
                //страницы аутентификаци доступна всем
                .antMatchers("/login").anonymous()
                .antMatchers("/hello").permitAll()

                // защищенные URL
//                .antMatchers("/people").access("hasAnyRole('ADMIN')").anyRequest().authenticated(); //было в 2.4.2 Example
//                Доступ на основе ролей
                .antMatchers(HttpMethod.GET, "/people/**").hasAnyRole("ADMIN", "USER")
                .antMatchers(HttpMethod.POST, "/people/**").hasAnyRole("ADMIN")
                .antMatchers(HttpMethod.DELETE, "/people/**").hasAnyRole("ADMIN")
//Доступ на основе permission
//                .antMatchers(HttpMethod.GET, "/people/**").hasAuthority(Permission.USER_READ.getPermission())
//                .antMatchers(HttpMethod.POST, "/people/**").hasAuthority(Permission.USER_WRITE.getPermission())
//                .antMatchers(HttpMethod.DELETE, "/people/**").hasAuthority(Permission.USER_WRITE.getPermission())

                .anyRequest()
                .authenticated();
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }
}
