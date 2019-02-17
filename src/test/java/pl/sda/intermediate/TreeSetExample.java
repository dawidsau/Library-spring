package pl.sda.intermediate;

import org.junit.jupiter.api.Test;
import pl.sda.intermediate.customers.User;

import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;

public class TreeSetExample {

    @Test
    void name() {
        Comparator<String> c = (a, b) -> Integer.valueOf(b.length()).compareTo(Integer.valueOf(a.length()));
        Set<String> objects = new TreeSet<>(c);
        objects.add("ccc");
        objects.add("bbbb");
        objects.add("ab");
        objects.add("ab");
        System.out.println(objects);


        Set<User> users = new TreeSet<>();
        users.add(new User());

    }
}
