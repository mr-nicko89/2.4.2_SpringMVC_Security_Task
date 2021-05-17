package my.app.config.handler;

import my.app.models.User;
import my.app.service.UserService;
import my.app.service.UserServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.Principal;
import java.util.Optional;

@Component
public class LoginSuccessHandler implements AuthenticationSuccessHandler {



    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest,
                                        HttpServletResponse httpServletResponse,
                                        Authentication authentication) throws IOException, ServletException {
//        authentication.getAuthorities().stream().filter(authentication.getName().toString().equals(String s = "ROLE_ADMIN")).findFirst();
//        if (principal.getName() == "admin") {
        String role = authentication.getAuthorities().toString();
//        String userName = authentication.getName();
//        Optional<User> user = userService.getAllUsers().stream().filter(u -> u.getName()==userName).findFirst();


        if(role.contains("ROLE_ADMIN")){
            httpServletResponse.sendRedirect("/admin");
        }
        else if(role.contains("ROLE_USER")){
            httpServletResponse.sendRedirect("/user/show");
        }
    }
}