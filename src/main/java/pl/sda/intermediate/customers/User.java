package pl.sda.intermediate.customers;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class User implements Serializable {
    private static final long serialVersionUID = 1719635491561854221L;
    private String firstName;
    private String lastName;
    private String eMail;
    private String birthDate;
    private String pesel;
    private String phone;
    private String passwordHash;
    private UserAddress userAddress;
}
