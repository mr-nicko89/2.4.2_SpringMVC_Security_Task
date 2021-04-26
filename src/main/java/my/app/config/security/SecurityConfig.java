package my.app.config.security;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/people/authenticated**").authenticated()
//                .antMatchers("/people").authenticated() // в /edit могут заходить только зарегистрированные пользователи
//                .antMatchers("/admin").hasAnyRole("ADMIN","SUPERADMIN") // в /admin могут заходить только пользователи с ролями ADMIN и SUPERADMIN
                .and()
//                .httpBasic() или formLogin //httpBasic - при заходе неаутентифицированным пользователем перебросит на стандартную форму аутентификации
                //formLogin - тоже самое, но перебросит на указанную нами форму Login
                .formLogin()
                .and()
                .logout().logoutSuccessUrl("/");

    }

}
