package ru.af3412.accident.control;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.af3412.accident.repository.AccidentJpaRepository;

@Controller
public class IndexControl {

    private final AccidentJpaRepository accidentJpaRepository;

    public IndexControl(AccidentJpaRepository accidentJpaRepository) {
        this.accidentJpaRepository = accidentJpaRepository;
    }


    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("accidents", accidentJpaRepository.findAll());
        return "index";
    }
}
