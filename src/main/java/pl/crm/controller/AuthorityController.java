package pl.crm.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.crm.entity.Authority;
import pl.crm.entity.Role;
import pl.crm.entity.User;
import pl.crm.repository.AuthorityRepository;
import pl.crm.repository.RoleRepository;
import pl.crm.repository.UserRepository;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/authority")
public class AuthorityController {

    @Autowired
    AuthorityRepository authorityRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @ModelAttribute("roles")
    List<Role> getAllRoles(){
        return roleRepository.findAll();
    }

    @GetMapping("/form")
    public String showForm(@RequestParam(required = false) Long userId,
                           @RequestParam(required = false) Long authorityId,
                           Model model) {

        //znajduje usera jeśli został przekazany userId
        User user = null;
        if(userId != null){
            user = userRepository.findOne(userId);
        }
        Authority authority = null;
        if (authorityId == null) {
            authority = new Authority();
            if(userId != null){
                authority.setUserName(user.getUsername());
            }
        } else {
            authority = authorityRepository.findOne(userId);
        }
        authority.setUserName(user.getUsername());
        model.addAttribute("authority", authority);
        model.addAttribute("user", user);
        return "authority/form";
    }

    @PostMapping("/form")
    public String saveForm(@Valid Authority authority, BindingResult result, Model model, Principal principal) {

        System.out.println("POST authority przchodzący z geta: " + authority.toString());
        if (result.hasErrors()) {
            System.out.println("Błędy");
            return "authority/form";
        }
//        User logged = userRepository.findUserByUsername(principal.getName());
//        authority.setUser(logged);
        ;
        User user = userRepository.findUserByUsername(authority.getUserName());
        authority.setUser(user);
        authorityRepository.save(authority);
        return "redirect:/user/list";
    }


}
