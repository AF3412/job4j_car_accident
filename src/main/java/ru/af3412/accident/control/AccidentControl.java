package ru.af3412.accident.control;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.af3412.accident.model.Accident;
import ru.af3412.accident.model.Rule;
import ru.af3412.accident.repository.AccidentJpaRepository;
import ru.af3412.accident.repository.AccidentTypeJpaRepository;
import ru.af3412.accident.repository.RulesJpaRepository;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class AccidentControl {

    private final AccidentJpaRepository accidentJpaRepository;
    private final AccidentTypeJpaRepository accidentTypeJpaRepository;
    private final RulesJpaRepository rulesJpaRepository;

    public AccidentControl(AccidentJpaRepository accidentJpaRepository, AccidentTypeJpaRepository accidentTypeJpaRepository, RulesJpaRepository rulesJpaRepository) {
        this.accidentJpaRepository = accidentJpaRepository;
        this.accidentTypeJpaRepository = accidentTypeJpaRepository;
        this.rulesJpaRepository = rulesJpaRepository;
    }


    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("types", accidentTypeJpaRepository.findAll());
        model.addAttribute("rules", rulesJpaRepository.findAll());
        return "accident/create";
    }

    @GetMapping("/edit")
    public String edit(@RequestParam("id") int id, Model model) {
        model.addAttribute("accident", accidentJpaRepository.findById(id).get());
        model.addAttribute("types", accidentTypeJpaRepository.findAll());
        model.addAttribute("rules", rulesJpaRepository.findAll());
        return "accident/edit";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Accident accident, HttpServletRequest req) {
        String[] ids = req.getParameterValues("rIds");
        List<Rule> rules = Arrays.stream(ids)
                .map(id -> new Rule(Integer.parseInt(id)))
                .collect(Collectors.toList());
        accident.setRules(rules);
        accidentJpaRepository.save(accident);

        return "redirect:/";
    }


}
