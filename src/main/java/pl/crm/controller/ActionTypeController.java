package pl.crm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.crm.entity.ActionType;
import pl.crm.entity.User;
import pl.crm.repository.ActionTypeRepository;
import pl.crm.repository.UserRepository;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/actiontype")
public class ActionTypeController {

    @Autowired
    ActionTypeRepository actionTypeRepository;

    @Autowired
    UserRepository userRepository;

    @RequestMapping("/form")
    public String showForm(@RequestParam(required = false) Long actionTypeId, Model model) {

        if (actionTypeId == null) {
            ActionType actionType = new ActionType();
            model.addAttribute("actiontype", actionType);
        } else {
            ActionType actionType = actionTypeRepository.findOne(actionTypeId);
            model.addAttribute("actiontype", actionType);
        }
        return "actiontype/form";
    }

    @PostMapping("/form")
    public String saveForm(@Valid ActionType actionType, BindingResult result, Model model, Principal principal) {

        System.out.println("POST actionType przchodzący z geta: " + actionType.toString());
        if (result.hasErrors()) {
            System.out.println("Błędy");
            return "actionType/form";
        }

        User logged = userRepository.findUserByUsername(principal.getName());
        actionType.setUser(logged);
        actionTypeRepository.save(actionType);
        return "redirect:list";

    }

    @RequestMapping("/list")
    public String showList(Model model) {

        List<ActionType> actionTypeList = actionTypeRepository.findAll();
        model.addAttribute("actiontypes", actionTypeList);
        return "actiontype/list";
    }

    @RequestMapping("/delete")
    public String delete(@RequestParam Long actionTypeId) {

        actionTypeRepository.delete(actionTypeId);
        return "redirect:list";
    }
}
