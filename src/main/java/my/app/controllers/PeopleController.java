package my.app.controllers;

import my.app.models.Person;
import my.app.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
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

    private final PersonService personService;

    @Autowired
    public PeopleController(PersonService personService)
    //public PeopleController(JpaPersonDAOImp personDAO)
    {
        this.personService = personService;
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
    public String getLoginPage() {
        return "login";
    }

    //Из 2.3.1
    @GetMapping("/people")
    @PreAuthorize("hasAuthority('person:read')")
    public String index(Model model) {
        model.addAttribute("people", personService.getAll());
        return "people/index";
    }

    @GetMapping("/people/{id}")
    @PreAuthorize("hasAuthority('person:read')")
    public String showUserById(@PathVariable("id") int id, Model model) {
        model.addAttribute("person", personService.getUserById(id));
        return "people/show";
    }

    @GetMapping("/people/new")
    @PreAuthorize("hasAuthority('person:read')")
    public String newPerson(@ModelAttribute("person") Person person) {
        return "people/new";
    }

    @PostMapping("/people")
    @PreAuthorize("hasAuthority('person:write')")
    public String create(@ModelAttribute("person") @Valid Person person,
                         BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "people/new";

        personService.save(person);
        return "redirect:/people";
    }

    @GetMapping("/people/{id}/edit")
    @PreAuthorize("hasAuthority('person:read')")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("person", personService.getUserById(id));
        return "people/edit";
    }

    @PatchMapping("/people/{id}")
    @PreAuthorize("hasAuthority('person:write')")
    public String update(@ModelAttribute("person") @Valid Person person, BindingResult bindingResult,
                         @PathVariable("id") int id) {
        if (bindingResult.hasErrors())
            return "people/edit";

        personService.update(id, person);
        return "redirect:/people";
    }

    @DeleteMapping("/people/{id}")
    @PreAuthorize("hasAuthority('person:write')")
    public String delete(@PathVariable("id") int id) {
        personService.delete(id);
        return "redirect:/people";
    }

    //    Example
    @GetMapping("/people/authenticated")
    public String pageForAuthenticatedUsers(Model model) {
        return "people/authenticated";
    }
}
