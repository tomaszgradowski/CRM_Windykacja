package pl.crm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.crm.entity.*;
import pl.crm.repository.*;

import javax.validation.Valid;
import java.security.Principal;

@Controller
@RequestMapping("/tel")
public class TelephoneController {


    @Autowired
    TelephoneRepository telephoneRepository;

    @Autowired
    DebtorRepository debtorRepository;

    @Autowired
    CaseRepository caseRepository;

    @Autowired
    UserRepository userRepository;

    @RequestMapping("/form")
    public String showForm(@RequestParam(required = false) Long debtorId, @RequestParam(required = false) Long telId, Model model) {
        System.out.println("GET Odebrany z listy debtorId: " + debtorId);

        if (telId == null) {
            Debtor debtor = debtorRepository.findOne(debtorId);
            System.out.println("GET debtor: " + debtor.toString());
            Telephone telephone = new Telephone();
            telephone.setDebtor(debtor);
            System.out.println("GET_adress z ustawionym debtor: " + telephone.toString());
            model.addAttribute("tel", telephone);
        } else {
            Telephone telephone = telephoneRepository.findOne(telId);
            System.out.println("Debtor pobrany po debtorId: " + telephone.toString());
            model.addAttribute("tel", telephone);
        }
        return "tel/form";
    }


    @PostMapping("/form")
    public String saveForm(@Valid Telephone telephone, BindingResult result, Model model, Principal principal) {

        System.out.println("POST_Tel przchodzący z geta: " + telephone.toString());
        if (result.hasErrors()) {
            System.out.println("Błędy");
            return "tel/form";
        }


        User logged = userRepository.findUserByUsername(principal.getName());
        telephone.setUser(logged);
        telephoneRepository.save(telephone);
        Debtor debtor = telephone.getDebtor();
        System.out.println("POST_ Debtor wyszukany po adresie" + debtor.toString());
        System.out.println("POST_wysyłąny caseId do debtor/details " + debtor.getaCase().getId());
        System.out.println("POST_wysyłąny debtorId do debtor/details " + debtor.getId());

        model.addAttribute("caseId", debtor.getaCase().getId());
        model.addAttribute("debtorId", debtor.getId());

        return "redirect:/debtor/details";
    }

    @RequestMapping("/delete")
    public String delete(@RequestParam Long telId, Model model) {

        System.out.println("Odebrany z listy telId: " + telId);
        Telephone telephone = telephoneRepository.findOne(telId);
        Debtor debtor = debtorRepository.findOne(telephone.getDebtor().getId());
        model.addAttribute("debtorId", debtor.getId());
        Long caseId = debtor.getaCase().getId();


        System.out.println("Pobrany z Debt caseId: " + caseId);
        //sprawdzam z jakiej case_id są te wierzytelności
        Case aCase = caseRepository.findOne(caseId);
        model.addAttribute("caseId", aCase.getId());


        if (telId != null) {
            telephoneRepository.delete(telId);
        } else {
            return "redirect:/debtor/details";
        }

        return "redirect:/debtor/details";
    }

}
