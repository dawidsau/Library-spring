package pl.sda.intermediate.customers;

import org.apache.commons.codec.digest.DigestUtils;

public class UserRegistrationService {

    UserDAO userDAO;

    public UserRegistrationService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }


    public void registerUser(UserRegistrationDTO userRegistrationDTO) {

        if (userDAO.userExist(userRegistrationDTO.getEMail())) {
            throw new UserExistsException("User exist");
        }
        User user = new User();
        user.setFirstName(userRegistrationDTO.getFirstName());
        user.setLastName(userRegistrationDTO.getLastName());
        user.setEMail(userRegistrationDTO.getEMail());
        user.setBirthDate(userRegistrationDTO.getBirthDate());
        user.setPesel(userRegistrationDTO.getPesel());
        user.setPhone(userRegistrationDTO.getPhone());
        user.setPasswordHash(DigestUtils.sha512Hex(userRegistrationDTO.getPassword()));
        UserAddress userAddress = new UserAddress();
        userAddress.setCity(userRegistrationDTO.getCity());
        userAddress.setCountry(userRegistrationDTO.getCountry());
        userAddress.setStreet(userRegistrationDTO.getStreet());
        userAddress.setZipCode(userRegistrationDTO.getZipCode());
        user.setUserAdress(userAddress);
        userDAO.saveUser(user);
    }
}
