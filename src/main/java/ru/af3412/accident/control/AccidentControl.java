package ru.af3412.accident.control;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.af3412.accident.model.Accident;
import ru.af3412.accident.model.Rule;
import ru.af3412.accident.service.AccidentService;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class AccidentControl {
    private final AccidentService accidentService;

    public AccidentControl(AccidentService accidentService) {
        this.accidentService = accidentService;
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("types", accidentService.findAllAccidentTypes());
        model.addAttribute("rules", accidentService.findAllRules());
        return "accident/create";
    }

    @GetMapping("/edit")
    public String edit(@RequestParam("id") int id, Model model) {
        model.addAttribute("accident", accidentService.findAccidentById(id));
        model.addAttribute("types", accidentService.findAllAccidentTypes());
        model.addAttribute("rules", accidentService.findAllRules());
        return "accident/edit";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Accident accident, HttpServletRequest req) {
        String[] ids = req.getParameterValues("rIds");
        List<Rule> rules = Arrays.stream(ids)
                .map(id -> new Rule(Integer.parseInt(id)))
                .collect(Collectors.toList());
        accident.setRules(rules);
        accidentService.create(accident);

        return "redirect:/";
    }


}
