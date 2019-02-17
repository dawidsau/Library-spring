package pl.sda.intermediate.customers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserContextHolder {

    private String login;

    public void logUserIn(String eMail){
        login=eMail;
    }
}
