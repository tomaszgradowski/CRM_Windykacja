package pl.crm.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.crm.entity.*;
import pl.crm.repository.CaseRepository;
import pl.crm.repository.DebtorRepository;
import pl.crm.repository.EmailRepository;
import pl.crm.repository.UserRepository;

import javax.validation.Valid;
import java.security.Principal;

@Controller
@RequestMapping("/email")
public class EmailController {

    @Autowired
    CaseRepository caseRepository;

    @Autowired
    DebtorRepository debtorRepository;

    @Autowired
    EmailRepository emailRepository;

    @Autowired
    UserRepository userRepository;

    @RequestMapping("/form")
    public String showForm(@RequestParam(required = false) Long debtorId,
                           @RequestParam(required = false) Long emailId, Model model) {
        System.out.println("GET Odebrany z listy debtorId: " + debtorId);

        if (emailId == null) {
            Debtor debtor = debtorRepository.findOne(debtorId);
            System.out.println("GET debtor: " + debtor.toString());
            Email email = new Email();
            email.setDebtor(debtor);
            System.out.println("email z ustawionym debtor: " + email.toString());
            model.addAttribute("email", email);
        } else {
            Email email = emailRepository.findOne(emailId);
            System.out.println("Debtor pobrany po debtorId: " + email.toString());
            model.addAttribute("adress", email);
        }
        return "email/form";
    }

    @PostMapping("/form")
    public String saveForm(@Valid Email email, BindingResult result, Model model, Principal principal) {

        System.out.println("POST_Email przchodzący z geta: " + email.toString());
        if (result.hasErrors()) {
            System.out.println("Błędy");
            return "email/form";
        }

        User logged = userRepository.findUserByUsername(principal.getName());
        email.setUser(logged);
        emailRepository.save(email);
        Debtor debtor = email.getDebtor();
        System.out.println("POST_ Debtor wyszukany po adresie" + debtor.toString());
        System.out.println("POST_wysyłąny caseId do debtor/details " + debtor.getaCase().getId());
        System.out.println("POST_wysyłąny debtorId do debtor/details " + debtor.getId());

        model.addAttribute("caseId", debtor.getaCase().getId());
        model.addAttribute("debtorId", debtor.getId());

        return "redirect:/debtor/details";
    }

    @RequestMapping("/delete")
    public String delete(@RequestParam Long emailId, Model model) {

        System.out.println("Odebrany z listy emailId: " + emailId);
        Email email = emailRepository.findOne(emailId);
        Debtor debtor = debtorRepository.findOne(email.getDebtor().getId());
        model.addAttribute("debtorId", debtor.getId());
        Long caseId = debtor.getaCase().getId();


        System.out.println("Pobrany z Debt caseId: " + caseId);
        //sprawdzam z jakiej case_id są te wierzytelności
        Case aCase = caseRepository.findOne(caseId);
        model.addAttribute("caseId", aCase.getId());


        if (email != null) {
            emailRepository.delete(emailId);
        } else {
            return "redirect:/debtor/details";
        }

        return "redirect:/debtor/details";
    }

}
