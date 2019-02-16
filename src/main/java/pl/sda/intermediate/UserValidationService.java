package pl.sda.intermediate;

import java.util.HashMap;
import java.util.Map;

public class UserValidationService {

    public Map<String,String> validateUser (UserRegistrationDTO dto){
        Map<String,String> errorMap = new HashMap<>();

        if (!dto.getFirstName().matches("[A-Z][a-z]{2,}")){
            errorMap.put("firstNameValRes","Przynajmniej 3 znaki.");
        }
        if (!dto.getLastName().matches("[A-Z][a-z]{2,}(-[A-Z][a-z]{2,})?")){
            errorMap.put("lastNameValRes","Przynajmniej 3 znaki.");
        }
        if (!dto.getZipCode().matches("[0-9]{2}-[0-9]{3}")){
            errorMap.put("zipCodeValRes","Błędny kod pocztowy");
        }
        if (!dto.getBirthDate().matches("(19|20)[0-9]{2}-(0[1-9]|1[0-2])-(0[1-9]|[1-2][0-9]|3[0-1])")){
            errorMap.put("birthDateValRes","Błędna data urodzin");
        }
        if (!dto.getLogin().matches("^[\\w\\.]+@([A-Za-z0-9]+\\.){1,2}[A-Za-z]{2,4}$")){
            errorMap.put("emailValRes","Błędny email");
        }
        if (!dto.getPhone().matches("^(\\+[0-9]{1,3} )?([0-9]{9}|[0-9]{3}-[0-9]{3}-[0-9]{3})$")){
            errorMap.put("phoneValRes","Błędny numer telefonu");
        }



        return errorMap;
    }
}
