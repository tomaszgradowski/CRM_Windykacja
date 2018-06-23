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
import java.util.List;

@Controller
@RequestMapping("/debtor")
public class DebtorController {

    @Autowired
    EmailRepository emailRepository;

    @Autowired
    DebtorRepository debtorRepository;

    @Autowired
    CaseRepository caseRepository;


    @Autowired
    AdressRepository adressRepository;

    @Autowired
    TelephoneRepository telephoneRepository;

    @Autowired
    UserRepository userRepository;

    @RequestMapping("/form")
    public String showForm(@RequestParam(required = false) Long caseId,
                           @RequestParam(required = false) Long debtorId
            , Model model) {
        System.out.println("ShowFormGet Odebrany z listy debtorId: " + debtorId);
        System.out.println("ShowFormGet z listy caseId: " + caseId);

        if (debtorId == null) {
            Case aCase = caseRepository.findOne(caseId);
            System.out.println("Case: " + aCase.toString());
            Debtor debtor = new Debtor();
            debtor.setaCase(aCase);
            System.out.println(debtor.toString());
            model.addAttribute("debtor", debtor);
        } else {
            Debtor debtor = debtorRepository.findOne(debtorId);
            System.out.println("Debtor pobrany po debtorId: " + debtor.toString());
            model.addAttribute("debtor", debtor);
        }
        return "debtor/form";
    }


    @PostMapping("/form")
    public String saveForm(@Valid Debtor debtor,
                           BindingResult result,
                           Model model,
                           Principal principal) {

        System.out.println("Debtor przchodzący z geta: " + debtor.toString());
        if (result.hasErrors()) {
            return "debtor/form";
        }
        User logged = userRepository.findUserByUsername(principal.getName());
        debtor.setUser(logged);
        debtorRepository.save(debtor);
        Case aCase = debtor.getaCase();
        model.addAttribute("newCaseId", aCase.getId());
        System.out.println("///////////////////////////////////Id dodanej sprawy: " + aCase.getId());
        return "redirect:/case/allinfo";
    }

    @RequestMapping("/delete")
    public String delete(@RequestParam Long debtorId, Model model) {

        System.out.println("Odebrany z listy debtorId: " + debtorId);
        Debtor debtor = debtorRepository.findOne(debtorId);
        Long caseId = debtor.getaCase().getId();


        System.out.println("Pobrany z Debt caseId: " + caseId);
        //sprawdzam z jakiej case_id są te wierzytelności
        Case aCase = caseRepository.findOne(caseId);
        model.addAttribute("caseId", aCase.getId());

        //znajduję wszystkie maile dłużnika
        List<Email> emailList = emailRepository.findAllEmailsByDebtorId(debtorId);
        for (int i = 0; i < emailList.size(); i++) {
            emailRepository.delete(emailList.get(i).getId());
        }
        //znajduję wszystkie telefony dłużnika
        List<Telephone> telephoneList = telephoneRepository.findAllTelephonesByDebtorId(debtorId);
        for (int i = 0; i < telephoneList.size(); i++) {
            telephoneRepository.delete(telephoneList.get(i).getId());
        }
        //znajduję wszystkie adresy dłużnika
        List<Adress> adressList = adressRepository.findAllAdressesByDebtorId(debtorId);
        for (int i = 0; i < adressList.size(); i++) {
            adressRepository.delete(adressList.get(i).getId());
        }

        if (debtorId != null) {

            debtorRepository.delete(debtorId);
        } else {
            return "redirect:list";
        }

        return "redirect:list";
    }

    @RequestMapping("/list")
    public String showList(@RequestParam Long caseId,
                           Model model,
                           Principal principal) {


        List<Debtor> debtorList = debtorRepository.findAllDeborsByCaseId(caseId);

        model.addAttribute("debtorsByCaseId", debtorList);
        model.addAttribute("caseId", caseId);

        return "debtor/list";
    }

    @RequestMapping("/details")
    public String showDetails(@RequestParam Long caseId,
                              @RequestParam Long debtorId,
                              Model model,
                              Principal principal) {

        Debtor debtor = debtorRepository.findOne(debtorId);

        List<Adress> adressList = adressRepository.findAllAdressesByDebtorId(debtorId);
        System.out.println("Wielkość listy adresów: " + adressList.size());

        List<Telephone> telephoneList = telephoneRepository.findAllTelephonesByDebtorId(debtorId);
        System.out.println("Wielkość listy telefonów: " + telephoneList.size());

        List<Email> emailList = emailRepository.findAllEmailsByDebtorId(debtorId);

        model.addAttribute("tel", telephoneList);
        model.addAttribute("adresses", adressList);
        model.addAttribute("debtor", debtor);
        model.addAttribute("emails", emailList);
        model.addAttribute("caseId", caseId);

        return "debtor/details";
    }


}
