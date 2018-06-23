package pl.crm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.crm.entity.Adress;
import pl.crm.entity.Case;
import pl.crm.entity.Debtor;
import pl.crm.entity.User;
import pl.crm.repository.AdressRepository;
import pl.crm.repository.CaseRepository;
import pl.crm.repository.DebtorRepository;
import pl.crm.repository.UserRepository;

import javax.validation.Valid;
import java.security.Principal;

@Controller
@RequestMapping("/adress")
public class AdressController {


    @Autowired
    AdressRepository adressRepository;

    @Autowired
    DebtorRepository debtorRepository;

    @Autowired
    CaseRepository caseRepository;

    @Autowired
    UserRepository userRepository;

    @RequestMapping("/form")
    public String showForm(@RequestParam(required = false) Long debtorId,
                           @RequestParam(required = false) Long adressId, Model model) {
        System.out.println("GET Odebrany z listy debtorId: " + debtorId);

        if (adressId == null) {
            Debtor debtor = debtorRepository.findOne(debtorId);
            System.out.println("GET debtor: " + debtor.toString());
            Adress adress = new Adress();
            adress.setDebtor(debtor);
            System.out.println("GET_adress z ustawionym debtor: " + adress.toString());
            model.addAttribute("adress", adress);
        } else {
            Adress adress = adressRepository.findOne(adressId);
            System.out.println("Debtor pobrany po debtorId: " + adress.toString());
            model.addAttribute("adress", adress);
        }
        return "adress/form";
    }


    @PostMapping("/form")
    public String saveForm(@Valid Adress adress, BindingResult result, Model model, Principal principal) {

        System.out.println("POST_Adress przchodzący z geta: " + adress.toString());
        if (result.hasErrors()) {
            System.out.println("Błędy");
            return "adress/form";
        }

        User logged = userRepository.findUserByUsername(principal.getName());
        adress.setUser(logged);
        adressRepository.save(adress);
        Debtor debtor = adress.getDebtor();
        System.out.println("POST_ Debtor wyszukany po adresie" + debtor.toString());
        System.out.println("POST_wysyłąny caseId do debtor/details " + debtor.getaCase().getId());
        System.out.println("POST_wysyłąny debtorId do debtor/details " + debtor.getId());

        model.addAttribute("caseId", debtor.getaCase().getId());
        model.addAttribute("debtorId", debtor.getId());

        return "redirect:/debtor/details";
    }

    @RequestMapping("/delete")
    public String delete(@RequestParam Long adressId, Model model) {

        System.out.println("Odebrany z listy adressId: " + adressId);
        Adress adress = adressRepository.findOne(adressId);
        Debtor debtor = debtorRepository.findOne(adress.getDebtor().getId());
        model.addAttribute("debtorId", debtor.getId());
        Long caseId = debtor.getaCase().getId();


        System.out.println("Pobrany z Debt caseId: " + caseId);
        //sprawdzam z jakiej case_id są te wierzytelności
        Case aCase = caseRepository.findOne(caseId);
        model.addAttribute("caseId", aCase.getId());


        if (adressId != null) {
            adressRepository.delete(adressId);
        } else {
            return "redirect:/debtor/details";
        }

        return "redirect:/debtor/details";
    }

}
