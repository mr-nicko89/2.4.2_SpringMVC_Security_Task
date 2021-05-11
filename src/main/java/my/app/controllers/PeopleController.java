package my.app.controllers;

import my.app.models.User;
import my.app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;


@Controller
@RequestMapping("/")
public class PeopleController {

    private final UserService userService;

    @Autowired
    public PeopleController(UserService userService)
    //public PeopleController(JpaUserDAOImp userDAO)
    {
        this.userService = userService;
    }

    //Из 2.4.2_Example

    @RequestMapping(value = "hello", method = RequestMethod.GET)
    public String printWelcome(ModelMap model) {
        List<String> messages = new ArrayList<>();
        messages.add("Hello!");
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
//    @RequestMapping("/people")
    @GetMapping("/people")
    public String index(Model model) {
        model.addAttribute("people", userService.getAll());
        return "people/index";
    }

    @GetMapping("/people/{id}")
    public String showUserById(@PathVariable("id") Long id, Model model) {
        model.addAttribute("user", userService.getUserById(id));
        return "people/show";
    }

    @GetMapping("/people/new")
    public String newUser(@ModelAttribute("user") User user) {
        return "people/new";
    }

    @PostMapping("/people")
    public String create(@ModelAttribute("user") @Valid User user,
                         BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "people/new";

        userService.save(user);
        return "redirect:/people";
    }

    @GetMapping("/people/{id}/edit")
    public String edit(Model model, @PathVariable("id") Long id) {
        model.addAttribute("user", userService.getUserById(id));
        return "people/edit";
    }

    @PatchMapping("/people/{id}")
    public String update(@ModelAttribute("user") @Valid User user, BindingResult bindingResult,
                         @PathVariable("id") Long id) {
        if (bindingResult.hasErrors())
            return "people/edit";

        userService.update(id, user);
        return "redirect:/people";
    }

    @DeleteMapping("/people/{id}")
    public String delete(@PathVariable("id") Long id) {
        userService.delete(id);
        return "redirect:/people";
    }

    //    Example
    @GetMapping("/people/authenticated")
    public String pageForAuthenticatedUsers(Model model) {
        return "people/authenticated";
    }
}
