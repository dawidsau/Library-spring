package pl.sda.intermediate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.sda.intermediate.categories.CategoryDTO;
import pl.sda.intermediate.categories.CategorySearchService;
import pl.sda.intermediate.customers.*;
import pl.sda.intermediate.weather.WeatherService;

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
    @Autowired
    private WeatherService weatherService;

    @RequestMapping(value = "/")
    public String home() {
        return "index";
    }

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
        model.addAttribute("form", userRegistrationDTO);
        if (errorsMap.isEmpty()) {
            userRegistrationService.registerUser(userRegistrationDTO);
            return "registerEffect";
        } else {
            model.addAllAttributes(errorsMap);
            model.addAttribute("countries", Countries.values());
            return "registerForm";
        }
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String registerForm(Model model) {
        model.addAttribute("countries", Countries.values());
        model.addAttribute("form", new UserRegistrationDTO());
        return "registerForm";
    }

    @RequestMapping(value = "/categories")
    public String categories(String searchText, Model model) {
        List<CategoryDTO> categories = categorySearchService.filterCategories(searchText);
        model.addAttribute("catsdata",categories);
        return "catspage"; //takiego htmla chcemy otworzyc
    }

    @RequestMapping(value = "/weather")
    @ResponseBody //tym mowimy, że serwis ma wyslac dane a nie przekierowac na htmla
    public ResponseEntity<String> weather(){
        String weather = weatherService.prepareWeather();
        return ResponseEntity.ok(weather);
    }
}
