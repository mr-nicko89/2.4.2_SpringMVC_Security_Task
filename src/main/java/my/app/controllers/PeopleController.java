package my.app.controllers;

import my.app.models.User;
import my.app.service.RoleService;
import my.app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;


@Controller
@RequestMapping("/")
public class PeopleController {

    private final UserService userService;
    private RoleService roleService;

    @Autowired
    public PeopleController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;


    }



    @RequestMapping(value = "hello", method = RequestMethod.GET)
    public String printWelcome(Principal principal, ModelMap model) {
// чтобы посмотреть аутентифицированного пользователя через дебаггер
        String str = "You are anonymous";
        if (principal != null) {
            Authentication a = SecurityContextHolder.getContext().getAuthentication();
            str = "You are logged in as a user: " + principal.getName();
        }
        List<String> messages = new ArrayList<>();
        messages.add("Hello!");
        messages.add(str);
        messages.add("I'm Spring MVC-SECURITY application");
        messages.add("5.2.0 version by sep'19 ");
        model.addAttribute("messages", messages);
        return "hello";
    }

    @RequestMapping(value = "login", method = RequestMethod.GET)
    public String loginPage() {
        return "login";
    }


    //Из 2.3.1
    @GetMapping("/admin")
    public String index(Model model) {
        model.addAttribute("people", userService.getAllUsers());
        return "admin/index";
    }

    @GetMapping("/admin/{id}")
    public String showUserByIdForAdmin(@PathVariable("id") Long id, Model model) {
        model.addAttribute("user", userService.getUserById(id));
        return "admin/show";
    }

    @GetMapping("/admin/new")
    public String newUser(@ModelAttribute("user") User user,Model model) {
        model.addAttribute("roles", roleService.getAllRoles());
        return "admin/new";
    }

    @PostMapping("/admin")
    public String create(@ModelAttribute("user") @Valid User user, @ModelAttribute("adminId") String adminId,
                         BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "admin/new";

        if (adminId.isEmpty()) {
            user.getRoleSet().add(roleService.getDefaultRole());
        } else {
            user.getRoleSet().add(roleService.getAdminRole());
        }
        userService.addUser(user);
        return "redirect:/admin";
    }

    @GetMapping("/admin/{id}/edit")
    public String edit(Model model, @PathVariable("id") Long id) {
        model.addAttribute("user", userService.getUserById(id));
        return "admin/edit";
    }

    @PatchMapping("/admin/{id}")
    public String update(@ModelAttribute("user") @Valid User user, BindingResult bindingResult,
                         @PathVariable("id") Long id) {
        if (bindingResult.hasErrors())
            return "admin/edit";

        userService.updateUser(id, user);
        return "redirect:/admin";
    }

    @DeleteMapping("/admin/{id}")
    public String delete(@PathVariable("id") Long id) {
        userService.deleteUser(id);
        return "redirect:/admin";
    }

    //    Example
    @GetMapping("/admin/authenticated")
    public String pageForAuthenticatedUsers(Model model) {
        return "admin/authenticated";
    }

    //Создаем пользователей по умолчанию admin, user
    @GetMapping("/creatDefaultUsers")
    public String creatDefaultUsers() {

        roleService.setRolesDefault();

        User admin = new User();
        admin.setAge(33);
        admin.setEmail("admin@email.com");
        admin.setName("admin");
        admin.setPassword("admin");
        admin.getRoleSet().add(roleService.getAdminRole());

        userService.addUser(admin);

        return "redirect:/admin";
    }

    @GetMapping("/user/show")
    public String showUserByIdForUser(Principal principal, Model model) {
        User user = userService.loadUserByUsername(principal);
        model.addAttribute("user", user);
        return "user/showUser";
    }


}
