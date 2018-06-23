package pl.crm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.crm.entity.*;
import pl.crm.repository.*;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/case")
public class CaseController {

    @Autowired
    private Environment env;

    @Autowired
    UserRepository userRepository;

    @Autowired
    CaseRepository caseRepository;

    @Autowired
    DebtRepository debtRepository;

    @Autowired
    DebtorRepository debtorRepository;

    @Autowired
    TelephoneRepository telephoneRepository;

    @Autowired
    AdressRepository adressRepository;

    @Autowired
    EmailRepository emailRepository;

    @Autowired
    ActionRepository actionRepository;

    @Autowired
    ResultRepository resultRepository;

    @Autowired
    SmsRepository smsRepository;


    @ModelAttribute("ccEmployees")
    public List<User> getAllCcEmployees() {
        return userRepository.findUsersByRole("ROLE_CC");
    }

    @ModelAttribute("lawyers")
    public List<User> getAllLawyers() {
        return userRepository.findUsersByRole("ROLE_LAWYER");
    }

    @ModelAttribute("supervisors")
    public List<User> getAllSupervisors() {
        return userRepository.findUsersByRole("ROLE_SUPERVISOR");
    }

    @ModelAttribute("allCases")
    public List<Case> getCases() {
        return caseRepository.findAll();
    }

    @GetMapping("/form")
    public String showForm(@RequestParam(required = false) Long caseId,
                           Model model) {

        Case case1 = null;
        if (caseId == null) {
            case1 = new Case();
        } else {
            case1 = caseRepository.findOne(caseId);
        }
        model.addAttribute("case", case1);

        return "case/form";
    }

    @PostMapping("/form")
    public String saveForm(@Valid Case case1,
                           BindingResult result,
                           Model model,
                           Principal principal) {
        if (result.hasErrors()) {
            return "case/form";
        }

        User loggedUser = userRepository.findUserByUsername(principal.getName());
        case1.setUser3(loggedUser);

        //ustawiam numer konta

        Long maxId = 1L;
        if (caseRepository.maxIdFromCase() != null && caseRepository.maxIdFromCase() > 0) {
            maxId = caseRepository.maxIdFromCase() + 1;
        }

        System.out.println("Max caseId: " + maxId);
        int maxIdSize = Integer.valueOf(maxId.toString().length());

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < (26 - maxIdSize); i++) {
            sb.append("0");
        }
        sb.append(maxId);
        String newAccountNumber = sb.toString();
        System.out.println("Wyenerowany numer konta: " + newAccountNumber);
        case1.setAccountNumber(newAccountNumber);

        caseRepository.save(case1);

        model.addAttribute("newCaseId", case1.getId());
        System.out.println("///////////////////////////////////Id dodanej sprawy: " + case1.getId());
        return "redirect:allinfo";
    }

    @RequestMapping("/allinfo")
    public String newCase(@RequestParam Long newCaseId, Model model) {

        //pobiera dane sprawy
        System.out.println("Przekazano id = " + newCaseId);
        Case aCase = caseRepository.findOne(newCaseId);
        System.out.println("Case: " + aCase.toString());
        model.addAttribute("newCase", aCase);

        //pobiera listę wierzytelności
        List<Debt> debtList = debtRepository.findAllDebtsByCaseId(newCaseId);
        System.out.println("Znalezione debts: " + debtList.toArray().toString());
        model.addAttribute("debts", debtList);

        //pobiera dłużnika
        List<Debtor> debtors = debtorRepository.findAllByACaseId(newCaseId);
        Debtor debtor = null;
        if (debtors.size() > 0) {
            debtor = debtors.get(0);
            System.out.println("znalenziony pierwszy dłużnik: " + debtor.toString());
            model.addAttribute("debtors", debtor);
        }

        //pobiera telefony dla znalezionego dłużnika
        if (debtor != null) {
            List<Telephone> telephone = telephoneRepository.findAllTelephonesByDebtorId(debtor.getId());
            System.out.println("wielkość listy telefonów: " + telephone.toString());
            model.addAttribute("telephone", telephone);
        }

        //pobiera maile dla znalezionego dłużnika
        if (debtor != null) {
            List<Email> emailList = emailRepository.findAllEmailsByDebtorId(debtor.getId());
            System.out.println("wielkość listy telefonów: " + emailList.toString());
            model.addAttribute("email", emailList);
        }

        //pobiera telefony dla znalezionego dłużnika
        if (debtor != null) {
            Adress adress = adressRepository.findFirstByDebtorId(debtor.getId());
            model.addAttribute("adress", adress);
        }

        //pobiera smsy dla sprawy
        List<Sms> smsList = smsRepository.findAllByACaseId(newCaseId);
        model.addAttribute("sms", smsList);

        //pobiera rezultaty
        List<Result> resultList = resultRepository.findAllByACaseId(newCaseId);
        model.addAttribute("results", resultList);

        //pobiera sumę początkowego kapitału
        BigDecimal sumPrincipal = debtRepository.sumPrincipalByCaseId(newCaseId);
        model.addAttribute("sumPrincipal", sumPrincipal);

        //pobiera sumę początkowych kosztów
        BigDecimal sumCosts = debtRepository.sumCostsByCaseId(newCaseId);
        model.addAttribute("sumCosts", sumCosts);

        //pobiera sumę początkowych odsetek
        BigDecimal sumIntersts = debtRepository.sumInterestsByCaseId(newCaseId);
        model.addAttribute("sumIntersts", sumIntersts);

        //pobiera listę akcji
        List<Action> actionList = actionRepository.findAllByACaseId(newCaseId);
        model.addAttribute("actions", actionList);

        return "case/allinfo";
    }

    @RequestMapping(path = "/list")
    public String listBooks(Model model) {

        model.addAttribute("allCases", caseRepository.findAll());
        return "case/list";
    }
}
