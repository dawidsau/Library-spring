package pl.sda.intermediate;

import pl.sda.intermediate.customers.UserRegistrationDTO;
import pl.sda.intermediate.customers.UserValidationService;

import java.util.Map;

public class OnlyOneController {

    UserValidationService userValidationService = new UserValidationService();

    public String registerEffects(UserRegistrationDTO userRegistrationDTO){

        Map<String, String> errorsMap = userValidationService.validateUser(userRegistrationDTO);
        if (errorsMap.isEmpty()){

            return "Ok";
        }else {
            return "nie ok";
        }
    }
}
