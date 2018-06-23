package pl.crm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.crm.entity.Authority;

import pl.crm.entity.User;
import pl.crm.repository.AuthorityRepository;
import pl.crm.repository.UserRepository;


import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    AuthorityRepository authorityRepository;


    @GetMapping("/form")
    public String showForm(@RequestParam(required = false) Long userId, Model model) {

        User user = null;
        if (userId == null) {
            user = new User();
        } else {
            user = userRepository.findOne(userId);
        }
        model.addAttribute("user", user);
        return "user/form";
    }

    @PostMapping("/form")
    public String saveForm(@Valid User user, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "user/form";
        }

        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPass = passwordEncoder.encode(user.getPassword());
        System.out.println("zakodowane hasło: " + encodedPass);
        user.setPassword(encodedPass);
        userRepository.save(user);
        return "redirect:list";
    }

    @RequestMapping(path = "/list")
    public String listBooks(Model model) {

        model.addAttribute("users", userRepository.findAll());

        return "user/list";
    }

    @RequestMapping("/delete")
    public String delete(@RequestParam Long userId) {
        User user = userRepository.findOne(userId);
        List<Authority> authorityList = authorityRepository.findAllByUserName(user.getUsername());
        for (int i = 0; i < authorityList.size(); i++) {
            authorityRepository.delete(authorityList.get(i).getId());
        }
        userRepository.delete(userId);

        return "redirect:list";
    }

}
