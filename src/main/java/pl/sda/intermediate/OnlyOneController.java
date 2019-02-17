package pl.sda.intermediate;

import pl.sda.intermediate.customers.*;

import java.util.Map;

public class OnlyOneController {

    UserValidationService userValidationService = new UserValidationService();
    UserDAO userDAO = new UserDAO();
    UserRegistrationService userRegistrationService =
            new UserRegistrationService(userDAO);
    UserLoginService userLoginService = new UserLoginService(userDAO);

    public String registerEffects(UserRegistrationDTO userRegistrationDTO){

        Map<String, String> errorsMap = userValidationService.validateUser(userRegistrationDTO);
        if (errorsMap.isEmpty()){

            return "Ok";
        }else {
            return "nie ok";
        }
    }
}
