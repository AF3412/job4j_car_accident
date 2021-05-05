package ru.af3412.accident.control;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.af3412.accident.model.User;
import ru.af3412.accident.repository.AuthorityRepository;
import ru.af3412.accident.repository.UserRepository;

import java.text.MessageFormat;
import java.util.Optional;

@Controller
public class RegControl {

    private final PasswordEncoder encoder;
    private final UserRepository users;
    private final AuthorityRepository authorities;

    public RegControl(PasswordEncoder encoder, UserRepository users, AuthorityRepository authorities) {
        this.encoder = encoder;
        this.users = users;
        this.authorities = authorities;
    }

    @PostMapping("/reg")
    public String save(@ModelAttribute User user) {
        Optional<User> findUser = users.findByUsername(user.getUsername());
        if (findUser.isPresent()) {
            String error = MessageFormat.format("User with name \"{0}\" is exists!", user.getUsername());
            return "redirect:/reg?error=true&errorMessage=" + error;
        }
        user.setEnabled(true);
        user.setPassword(encoder.encode(user.getPassword()));
        user.setAuthority(authorities.findByAuthority("ROLE_USER"));
        users.save(user);
        return "redirect:/login";
    }

    @GetMapping("/reg")
    public String reg(@RequestParam(value = "error", required = false) String error,
                      @RequestParam(value = "errorMessage", required = false) String errorMessage,
                      Model model) {
        if (error != null) {
            model.addAttribute("errorMessage", errorMessage);
        }
        return "reg";
    }
}
