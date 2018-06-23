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
import pl.crm.repository.SmsRepository;
import pl.crm.repository.TelephoneRepository;
import pl.crm.repository.UserRepository;

import javax.validation.Valid;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/sms")
public class SmsController {

    @Autowired
    CaseRepository caseRepository;

    @Autowired
    SmsRepository smsRepository;

    @Autowired
    TelephoneRepository telephoneRepository;

    @Autowired
    UserRepository userRepository;

    @RequestMapping("/form")
    public String showForm(@RequestParam(required = false) Long caseId
            , Model model) {
        //  System.out.println("ShowFormGet Odebrany z listy dbtId: " + debtId);
        System.out.println("ShowFormGet z listy caseId: " + caseId);


        Case aCase = caseRepository.findOne(caseId);
        System.out.println("Case: " + aCase.toString());
        Sms sms = new Sms();
        sms.setaCase(aCase);
        System.out.println(sms.toString());
        model.addAttribute("sms", sms);

        List<Telephone> telephoneList = telephoneRepository.findAllByCaseId(caseId);
        model.addAttribute("telephones", telephoneList);


        return "sms/form";
    }

    @PostMapping("/form")
    public String saveForm(@Valid Sms sms, BindingResult result, Model model, Principal principal) {

        System.out.println("SMS przchodzący z geta: " + sms.toString());
        if (result.hasErrors()) {
            return "sms/form";
        }
        User logged = userRepository.findUserByUsername(principal.getName());
        sms.setUser(logged);
        smsRepository.save(sms);
        Case aCase = sms.getaCase();
        model.addAttribute("newCaseId", aCase.getId());
        System.out.println("///////////////////////////////////Id dodanej sprawy: " + aCase.getId());


        /////WYSYŁKA SMS
        //https://api.smsapi.pl/sms.do?to=665872197&message=test&test=1&access_token=FrdBslodVhAE9sedO2UZxfCHOlaW6QG4mPEH6ePg

        Long telId = sms.getTelephone().getId();
        Telephone telephone = telephoneRepository.findOne(telId);

        String url = "https://api.smsapi.pl/sms.do?";
        String toParam = telephone.getTelNumber();
        String messageParam = sms.getMessage();
        String tokenParam = sms.getToken();
        int testParam = 0;
        if (sms.isTest() == true) {
            testParam = 1;
        } else {
            testParam = 0;
        }


        //generowanie adresu URL
        StringBuilder sb = new StringBuilder();
        sb.append(url)
                .append("to=")
                .append(toParam)
                .append("&message=")
                .append(messageParam)
                .append("&access_token=")
                .append(tokenParam)
                .append("&test=")
                .append(testParam);

        url = sb.toString();
        System.out.println("URL do wywołania: " + url);

        //wywołanie urla i odebranie odpowiedzi
        try {
            URL obj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            con.setRequestMethod("GET");

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            //odpowiedź z api
            System.out.println(response.toString());
            String[] params = response.toString().split(":");

            //ustawiam atrybuty sms do update informacjami odebranymi z api
            sms.setCost(new BigDecimal(params[2]));
            boolean success = false;
            if (params[0].equalsIgnoreCase("OK")) {
                success = true;
            }
            sms.setSuccess(success);
            smsRepository.save(sms);


        } catch (Exception e) {
            e.printStackTrace();
        }

        return "redirect:/case/allinfo";
    }


}
