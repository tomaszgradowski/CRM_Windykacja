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
import pl.crm.repository.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/action")
public class ActionController {

    @Autowired
    CaseRepository caseRepository;

    @Autowired
    ActionRepository actionRepository;

    @Autowired
    ResultRepository resultRepository;

    @Autowired
    ActionTypeRepository actionTypeRepository;

    @Autowired
    UserRepository userRepository;

    @ModelAttribute("actions")
    public List<ActionType> actionTypeList() {
        return actionTypeRepository.findAll();
    }

    @RequestMapping("/form")
    public String showForm(@RequestParam(required = false) Long caseId, @RequestParam(required = false) Long actionId
            , Model model) {
        System.out.println("ShowFormGet Odebrany z listy debtorId: " + actionId);
        System.out.println("ShowFormGet z listy caseId: " + caseId);

        if (actionId == null) {
            Case aCase = caseRepository.findOne(caseId);
            System.out.println("Case: " + aCase.toString());
            Action action = new Action();
            action.setaCase(aCase);
            System.out.println(action.toString());
            model.addAttribute("action", action);
        } else {
            Action action = actionRepository.findOne(actionId);
            System.out.println("action pobrany po debtorId: " + action.toString());
            model.addAttribute("action", action);
        }
        return "action/form";
    }


    @PostMapping("/form")
    public String saveForm(@Valid Action action, BindingResult result, Model model, Principal principal) {

        System.out.println("Debtor przchodzący z geta: " + action.toString());
        if (result.hasErrors()) {
            return "action/form";
        }

        User logged = userRepository.findUserByUsername(principal.getName());
        action.setUser(logged);
        actionRepository.save(action);
        Case aCase = action.getaCase();
        model.addAttribute("newCaseId", aCase.getId());
        System.out.println("///////////////////////////////////Id dodanej sprawy: " + aCase.getId());
        return "redirect:/case/allinfo";

    }

    @RequestMapping("/list")
    public String showList(@RequestParam Long caseId, Model model, Principal principal) {

        List<Action> actionList = actionRepository.findAllByACaseId(caseId);
        model.addAttribute("actionsByCaseId", actionList);
        model.addAttribute("caseId", caseId);

        return "action/list";
    }


    @RequestMapping("/delete")
    public String delete(@RequestParam Long actionId, Model model) {


        //znajduje wszystkie razultaty dla danego actionId
        List<Result> resultList = resultRepository.findAllByActionId(actionId);

        //kasuje przypisane rezultaty
        for (int i = 0; i < resultList.size(); i++) {
            resultRepository.delete(resultList.get(i).getId());
        }

        Action action = actionRepository.findOne(actionId);
        model.addAttribute("actionId", action.getId());
        Case aCase = caseRepository.findOne(action.getaCase().getId());
        model.addAttribute("caseId", aCase.getId());
        model.addAttribute("newCaseId", aCase.getId());
        if (actionId != null) {
            actionRepository.delete(actionId);
        } else {
            return "redirect:/case/allinfo";
        }

        return "redirect:/case/allinfo";
    }

    @RequestMapping("/details")
    public String showDetails(@RequestParam Long actionId, @RequestParam Long caseId, Model model, Principal principal) {

        Action action = actionRepository.findOne(actionId);

        List<Result> resultList = resultRepository.findAllByActionId(actionId);
        System.out.println("Wielkość listy adresów: " + resultList.size());

        model.addAttribute("action", action);
        model.addAttribute("results", resultList);
        model.addAttribute("caseId", caseId);

        return "action/details";
    }

}
