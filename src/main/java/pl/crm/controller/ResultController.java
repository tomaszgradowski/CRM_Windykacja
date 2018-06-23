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
@RequestMapping("/result")

public class ResultController {

    @Autowired
    ResultTypeRepository resultTypeRepository;

    @Autowired
    CaseRepository caseRepository;

    @Autowired
    ResultRepository resultRepository;

    @Autowired
    ActionRepository actionRepository;

    @Autowired
    ActionTypeResultTypeRepository actionTypeResultTypeRepository;

    @Autowired
    UserRepository userRepository;

    @ModelAttribute("results")
    public List<ResultType> getResults() {
        return resultTypeRepository.findAll();
    }


    @RequestMapping("/form")
    public String showForm(@RequestParam(required = false) Long caseId,
                           @RequestParam(required = false) Long resultId,
                           @RequestParam Long actionId
            , Model model) {
        System.out.println("ShowFormGet Odebrany z listy resultId: " + resultId);
        System.out.println("ShowFormGet z listy caseId: " + caseId);
        System.out.println("ShowFormGet z listy actionId: " + actionId);

        if (resultId == null) {
            Case aCase = caseRepository.findOne(caseId);
            System.out.println("Case: " + aCase.toString());
            Action action = actionRepository.findOne(actionId);
            Result result = new Result();
            result.setaCase(aCase);
            result.setAction(action);
            System.out.println(result.toString());
            model.addAttribute("result", result);

            //dodaję listę rezultatów przypisanych do danej akcji
            Long actionTypeId = action.getActionType().getId();
            List<ActionTypeResultType> actionTypeResultTypes =
                    actionTypeResultTypeRepository.findAllByActionType_Id(actionTypeId);
            model.addAttribute("actionTypeResultTypes", actionTypeResultTypes);
        } else {
            Result result = resultRepository.findOne(resultId);
            System.out.println("action pobrany po debtorId: " + result.toString());
            model.addAttribute("result", result);
        }
        return "result/form";
    }


    @PostMapping("/form")
    public String saveForm(@Valid Result aResult, BindingResult result, Model model, Principal principal) {

        System.out.println("Result przchodzący z geta: " + aResult.toString());
        if (result.hasErrors()) {
            return "result/form";
        }

        User logged = userRepository.findUserByUsername(principal.getName());
        aResult.setUser(logged);
        resultRepository.save(aResult);
        Case aCase = aResult.getaCase();
        model.addAttribute("newCaseId", aCase.getId());
        System.out.println("///////////////////////////////////Id dodanej sprawy: " + aCase.getId());
        return "redirect:/case/allinfo";

    }

    @RequestMapping("/delete")
    public String delete(@RequestParam Long resultId, Model model) {


        Result result = resultRepository.findOne(resultId);
        Action action = actionRepository.findOne(result.getAction().getId());
        // model.addAttribute("actionId", action.getId());
        Case aCase = caseRepository.findOne(action.getaCase().getId());
        model.addAttribute("caseId", aCase.getId());
        model.addAttribute("newCaseId", aCase.getId());
        if (resultId != null) {
            resultRepository.delete(resultId);
        } else {
            return "redirect:/case/allinfo";
        }

        return "redirect:/case/allinfo";
    }
}
