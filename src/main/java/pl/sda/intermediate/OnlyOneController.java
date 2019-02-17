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

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String loginEffect(UserLoginDTO userLoginDTO, Model model) {

        boolean logged = userLoginService.loginUser(userLoginDTO);
        if (logged) {
            return "index";
        }
        return "loginForm";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginForm() {


        return "loginForm";
    }


    public String registerEffects(UserRegistrationDTO userRegistrationDTO) {
        Map<String, String> errorsMap = userValidationService.validateUser(userRegistrationDTO);
        if (errorsMap.isEmpty()) {
            userRegistrationService.registerUser(userRegistrationDTO);
            return "Ok";
        } else {
            return "nie ok";
        }
    }

    public String categories(String searchText) {
        List<CategoryDTO> categories = categorySearchService.filterCategories(searchText);

        return "catspage";
    }
}
