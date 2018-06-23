package pl.crm.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.crm.entity.Case;
import pl.crm.entity.Debt;
import pl.crm.entity.User;
import pl.crm.repository.CaseRepository;
import pl.crm.repository.DebtRepository;
import pl.crm.repository.UserRepository;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/debt")
public class DebtController {


    @Autowired
    DebtRepository debtRepository;

    @Autowired
    CaseRepository caseRepository;

    @Autowired
    UserRepository userRepository;


    @RequestMapping("/form")
    public String showForm(@RequestParam(required = false) Long caseId, @RequestParam(required = false) Long debtId
            , Model model) {
        System.out.println("ShowFormGet Odebrany z listy dbtId: " + debtId);
        System.out.println("ShowFormGet z listy caseId: " + caseId);

        if (debtId == null) {
            Case aCase = caseRepository.findOne(caseId);
            System.out.println("Case: " + aCase.toString());
            Debt debt = new Debt();
            debt.setaCase(aCase);
            System.out.println(debt.toString());
            model.addAttribute("debt", debt);
        } else {
            Debt debt = debtRepository.findOne(debtId);
            System.out.println(debt.toString());
            model.addAttribute("debt", debt);
        }

        return "debt/form";
    }


    @PostMapping("/form")
    public String saveForm(@Valid Debt debt, BindingResult result, Model model, Principal principal) {

        System.out.println("Debt przchodzący z geta: " + debt.toString());
        if (result.hasErrors()) {
            return "debt/form";
        }


        User logged = userRepository.findUserByUsername(principal.getName());
        debt.setUser(logged);
        debtRepository.save(debt);
        Case aCase = debt.getaCase();
        model.addAttribute("newCaseId", aCase.getId());
        System.out.println("///////////////////////////////////Id dodanej sprawy: " + aCase.getId());
        return "redirect:/case/allinfo";

    }


    @RequestMapping("/delete")
    public String delete(@RequestParam Long debtId, Model model) {

        System.out.println("Odebrany z listy dbtId: " + debtId);
        Debt debt = debtRepository.findOne(debtId);
        Long caseId = debt.getaCase().getId();


        System.out.println("Pobrany z Debt caseId: " + caseId);
        //sprawdzam z jakiej case_id są te wierzytelności
        Case aCase = caseRepository.findOne(caseId);
        model.addAttribute("caseId", aCase.getId());

        model.addAttribute("newCaseId", aCase.getId());
        if (debtId != null) {
            debtRepository.delete(debtId);
        } else {
            return "redirect:/case/allinfo";
        }

        return "redirect:/case/allinfo";
    }

    @RequestMapping("/list")
    public String showList(@RequestParam Long caseId, Model model) {
        List<Debt> debtList = debtRepository.findAllDebtsByCaseId(caseId);

        model.addAttribute("debtsByCaseId", debtList);
        model.addAttribute("caseId", caseId);

        return "debt/list";
    }
}
