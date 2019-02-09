package pl.sda.intermediate;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

public class CustomerStatistics {
    private Customer[] people = new Customer[]{
            new Customer("Anna", "Nowak   ", 33, "1200"),
            new Customer("Beata", "Kowalska", 22, "1200"),
            new Customer("Marek", " Nowak", 25, "1250"),
            new Customer("Adam", "Twardowski", 33, "4100"),
            new Customer("Monika  ", "Kos", 25, "2500"),
            new Customer("Adam ", "Rudy", 45, "3333"),
            new Customer("Marek", "Rudy", 15, 2210),
            new Customer("Adam", "Madej", 15, 3333)
    };

    //1. Napisz metodę, która zamieni tablicę people na listę people -
    // ta metoda może być potem wykorzystywana przez Was w następnych metodach
    public List<Customer> customerList(Customer[] customers) {
        return Arrays.asList(customers);
    }

    public List<Customer> customerListWithStream(Customer[] customers) {
        return Arrays.stream(customers)
                .collect(Collectors.toList());
    }

    //2. Napisz metodę, która przyjmie tablicę people i zwróci
    // listę Stringów <imię nazwisko> (elementem listy będzie np "Anna Nowak")
    public List<String> populateNamesList(Customer[] customers) {
        List<String> results = new ArrayList<>();
        for (Customer customer : customers) {
            results.add(customer.getName() + " " + customer.getSurname());
        }
        return results;
    }

    public List<String> populateNamesListWithStream(Customer[] customers) {
        return Arrays.stream(customers)
                .map(c -> c.getName() + " " + c.getSurname())
                .collect(Collectors.toList());
    }

    //4. Napisz metodę, która zwróci mapę osób według zarobków
    // <zarobki,osoby_z_zarobkami>
    public Map<BigDecimal, List<Customer>> groupCustomersBySalary(Customer[] customers) {
        Map<BigDecimal, List<Customer>> resultMap = new HashMap<>();
        for (Customer customer : customers) {
            if (resultMap.containsKey(customer.getSalary())) {
                List<Customer> valueList = resultMap.get(customer.getSalary());
                valueList.add(customer);
            } else {
                List<Customer> valueList = new ArrayList<>();
                valueList.add(customer);
                resultMap.put(customer.getSalary(), valueList);
            }
        }
        return resultMap;
    }

    public Map<BigDecimal, List<Customer>> groupCustomersBySalaryWithStream(Customer[] customers) {
        return Arrays.stream(customers)
                .collect(groupingBy(Customer::getSalary));
    }

    //6. Napisz metodę, która zwróci mapę
    // map <imię,<zarobki,liczba_osób_z_takimi_zarobkami>>
    public Map<String, Map<BigDecimal, Integer>> populateSalaryStatisticsMap() {
        Map<String, Map<BigDecimal, Integer>> resultsMap = new HashMap<>();
        for (Customer customer : people) {
            if (resultsMap.containsKey(customer.getName().trim())) {
                Map<BigDecimal, Integer> innerMap = resultsMap.get(customer.getName().trim());
                if (innerMap.containsKey(customer.getSalary())) {
                    Integer counter = innerMap.get(customer.getSalary());
                    innerMap.put(customer.getSalary(), counter + 1);
                } else {
                    innerMap.put(customer.getSalary(), 1);
                }
            } else {
                Map<BigDecimal, Integer> innerMap = new HashMap<>();
                innerMap.put(customer.getSalary(), 1);
                resultsMap.put(customer.getName().trim(), innerMap);
            }
        }
        return resultsMap;
    }

    public Map<String, Map<BigDecimal, Long>> populateSalaryStatisticsMapWithStream() {
        return Arrays.stream(people)
                .collect(groupingBy(s -> s.getName().trim(),
                        groupingBy(Customer::getSalary,
                                counting())));

    }
}
