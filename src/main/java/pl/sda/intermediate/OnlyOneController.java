package pl.sda.intermediate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.sda.intermediate.categories.CategoryDTO;
import pl.sda.intermediate.categories.CategorySearchService;
import pl.sda.intermediate.customers.*;

import java.util.List;
import java.util.Map;

@Controller//singleton (do obslugi requestow z www)
public class OnlyOneController {

    @Autowired//tutaj spring doda referencje do obiektu
    private UserValidationService userValidationService;
    @Autowired
    private UserRegistrationService userRegistrationService;
    @Autowired
    private UserLoginService userLoginService;
    @Autowired
    private CategorySearchService categorySearchService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)//oznaczamy w ten sposob metody reagujace na requesty
    public String loginEffect(UserLoginDTO userLoginDTO, Model model) { //model - element przekazujacy dane miedzy frontem i aplikacja

        boolean logged = userLoginService.loginUser(userLoginDTO);
        if (logged) {
            return "index";//tutaj podajemy nazwe htmla, ktory ma sie wyswietlic
        }
        model.addAttribute("form", new UserLoginDTO()); //tu jest wstawainy pusty obiekt pod formularz
        model.addAttribute("error", "Błąd logowania"); //tu jest uszupelniany komunikat bledu
        return "loginForm";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginForm(Model model) {
        model.addAttribute("form", new UserLoginDTO());
        return "loginForm";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String registerEffects(UserRegistrationDTO userRegistrationDTO, Model model) {
        Map<String, String> errorsMap = userValidationService.validateUser(userRegistrationDTO);
        if (errorsMap.isEmpty()) {
            userRegistrationService.registerUser(userRegistrationDTO);
            return "registerEffect";
        } else {
            model.addAllAttributes(errorsMap);
            model.addAttribute("countries", Countries.values());
            model.addAttribute("form", new UserRegistrationDTO());
            return "registerForm";
        }
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String registerForm(Model model) {
        model.addAttribute("countries", Countries.values());
        model.addAttribute("form", new UserRegistrationDTO());
        return "registerForm";
    }

    public String categories(String searchText) {
        List<CategoryDTO> categories = categorySearchService.filterCategories(searchText);

        return "catspage";
    }
}
