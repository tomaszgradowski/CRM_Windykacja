package pl.crm.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.crm.entity.*;
import pl.crm.repository.ActionTypeRepository;
import pl.crm.repository.ActionTypeResultTypeRepository;
import pl.crm.repository.ResultTypeRepository;
import pl.crm.repository.UserRepository;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/acttyperestype")
public class ActionTypeResultTypeController {

    @Autowired
    ActionTypeResultTypeRepository actionTypeResultTypeRepository;

    @Autowired
    ActionTypeRepository actionTypeRepository;

    @Autowired
    ResultTypeRepository resultTypeRepository;

    @Autowired
    UserRepository userRepository;

    @ModelAttribute("actions")
    public List<ActionType> getActions() {
        return actionTypeRepository.findAll();
    }

    @ModelAttribute("results")
    public List<ResultType> getResults() {
        return resultTypeRepository.findAll();
    }


    @RequestMapping("/form")
    public String showForm(@RequestParam(required = false) Long actionTypeResultTypeId, Model model) {

        if (actionTypeResultTypeId == null) {
            ActionTypeResultType actionTypeResultType = new ActionTypeResultType();
            model.addAttribute("actionTypeResultType", actionTypeResultType);
        } else {
            ActionTypeResultType actionTypeResultType = actionTypeResultTypeRepository.findOne(actionTypeResultTypeId);
            model.addAttribute("actionTypeResultType", actionTypeResultType);
        }
        return "actiontyperesulttype/form";
    }

    @PostMapping("/form")
    public String saveForm(@Valid ActionTypeResultType actionTypeResultType,
                           BindingResult result,
                           Model model,
                           Principal principal) {

        System.out.println("POST actionType przchodzący z geta: " + actionTypeResultType.toString());
        if (result.hasErrors()) {
            System.out.println("Błędy");
            return "actiontyperesulttype/form";
        }

        User logged = userRepository.findUserByUsername(principal.getName());
        actionTypeResultType.setUser(logged);
        actionTypeResultTypeRepository.save(actionTypeResultType);
        return "redirect:list";

    }

    @RequestMapping("/list")
    public String showList(Model model) {

        List<ActionTypeResultType> actionTypeResultTypes = actionTypeResultTypeRepository.findAll();
        model.addAttribute("actionresultstypes", actionTypeResultTypes);
        return "actiontyperesulttype/list";
    }

}
