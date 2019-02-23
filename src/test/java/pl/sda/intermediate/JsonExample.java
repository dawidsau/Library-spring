package pl.sda.intermediate;

import com.google.gson.Gson;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JsonExample {

    @Test
    void serializeToJson() {
        OtherObject otherObject = new OtherObject(1, "dfDFFDIW");
//        List<OtherObject> otherObjects = new ArrayList<>();
//        otherObjects.add(otherObject); klasyczne podejście do tworzenia listy
        Map<Integer, String> map = new HashMap<>();
        map.put(1, "Adam");
        map.put(4, "Tomek");
        SomeObject someObject = new SomeObject(
                "Adam",
                30,
                BigDecimal.valueOf(3000),
                Lists.newArrayList(otherObject), //podejście z wykorzystaniem guavy
                map,
                true
        );

        Gson gson = new Gson();
        String json = gson.toJson(someObject);
        System.out.println(json);

        SomeObject result = gson.fromJson(json, SomeObject.class);

    }

    @Test
    void nbp() {
        try {
            URL url = new URL("http://api.nbp.pl/api/exchangerates/tables/A/last?format=json");
            URLConnection urlConnection = url.openConnection();
            InputStream inputStream = urlConnection.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader br = new BufferedReader(inputStreamReader);

            String inputLine;
            StringBuilder result = new StringBuilder();
            while ((inputLine = br.readLine()) != null) {
                result.append(inputLine);
            }
            br.close();
            System.out.println(result);
            RatesWrapper[] ratesWrapper = new Gson().fromJson(result.toString(), RatesWrapper[].class);
            System.out.println();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Getter
    @Setter
    private class RatesWrapper {
        private String table;
        private String no;
        private String effectiveDate;
        private List<Rate> rates;
    }
    @Getter
    @Setter
    private class Rate {
        private String currency;
        private String code;
        private double mid;
    }


    @AllArgsConstructor
    private class SomeObject {

        private String name;
        private int age;
        private BigDecimal salary;
        private List<OtherObject> otherObjects;
        private Map<Integer, String> map;
        private boolean isItTrue;
    }

    @AllArgsConstructor
    private class OtherObject {
        private int id;
        private String text;
    }

}
