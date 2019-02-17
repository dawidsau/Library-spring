package pl.sda.intermediate.customers;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class UserDAO {
    private static final String USERS_DATA_TXT = "c:/projects/users_data.txt";

    Map<String, User> userMap = new HashMap<>();

    {
        try (FileInputStream fis = new FileInputStream(USERS_DATA_TXT);
             ObjectInputStream ois = new ObjectInputStream(fis);) {
            userMap = (Map<String, User>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public boolean userExist(String email) {
        return userMap.containsKey(email);
    }


    public void saveUser(User user) {
        userMap.put(user.getEMail(), user);
        try (FileOutputStream fos = new FileOutputStream(USERS_DATA_TXT);
             ObjectOutputStream oos = new ObjectOutputStream(fos);) {
            oos.writeObject(userMap);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
