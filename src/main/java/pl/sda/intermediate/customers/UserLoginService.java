package pl.sda.intermediate.customers;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static org.apache.commons.codec.digest.DigestUtils.*;

@Service//singleton
public class UserLoginService {
    @Autowired
    private UserDAO userDAO;
    @Autowired
    private UserContextHolder userContextHolder;

    public boolean loginUser(UserLoginDTO userLoginDTO){
// podobne rozwiaanie jak nizej - tylko mniej funcyjne podejscie
//        Optional<User> userByEmail = userDAO.findUserByEmail(userLoginDTO.getLogin());
//        if (userByEmail.isPresent()) {
//            /*sprawdzanie hasla*/
//            if(haslo ok -> true)
//        }else {
//            return false
//        }

        boolean isAbletoLogIn = userDAO.findUserByEmail(userLoginDTO.getLogin())
                .map(user -> compareHashes(userLoginDTO, user))
                .orElse(false);
        if(isAbletoLogIn) {
            userContextHolder.logUserIn(userLoginDTO.getLogin());
        }
        return isAbletoLogIn;
    }

    private boolean compareHashes(UserLoginDTO userLoginDTO, User user) {
        return sha512Hex(userLoginDTO.getPassword())
                .equals(user.getPasswordHash());
    }
}
