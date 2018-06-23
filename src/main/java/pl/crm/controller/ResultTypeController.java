package pl.crm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.crm.entity.ResultType;
import pl.crm.entity.User;
import pl.crm.repository.ResultTypeRepository;
import pl.crm.repository.UserRepository;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@Controller()
@RequestMapping("/resulttype")
public class ResultTypeController {


    @Autowired
    ResultTypeRepository resultTypeRepository;

    @Autowired
    UserRepository userRepository;


    @RequestMapping("/form")
    public String showForm(@RequestParam(required = false) Long resultTypeId, Model model) {

        if (resultTypeId == null) {
            ResultType resultType = new ResultType();
            model.addAttribute("resulttype", resultType);
        } else {
            ResultType resultType = resultTypeRepository.findOne(resultTypeId);
            model.addAttribute("resulttype", resultType);
        }
        return "resulttype/form";
    }

    @PostMapping("/form")
    public String saveForm(@Valid ResultType resultType, BindingResult result, Model model, Principal principal) {

        System.out.println("POST actionType przchodzący z geta: " + resultType.toString());
        if (result.hasErrors()) {
            System.out.println("Błędy");
            return "actionType/form";
        }

        User logged = userRepository.findUserByUsername(principal.getName());
        resultType.setUser(logged);
        resultTypeRepository.save(resultType);
        return "redirect:list";

    }

    @RequestMapping("/list")
    public String showList(Model model) {

        List<ResultType> resultTypeList = resultTypeRepository.findAll();
        model.addAttribute("resulttypes", resultTypeList);
        return "resulttype/list";
    }

    @RequestMapping("/delete")
    public String delete(@RequestParam Long resultTypeId) {

        resultTypeRepository.delete(resultTypeId);

        return "redirect:list";
    }

}
