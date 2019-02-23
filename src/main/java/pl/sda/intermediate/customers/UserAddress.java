package pl.sda.intermediate.customers;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class UserAddress implements Serializable {
    private static final long serialVersionUID = 7831357211150823185L;
    private String city;
    private String country;
    private String zipCode;
    private String street;
}
