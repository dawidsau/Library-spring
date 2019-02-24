package pl.sda.intermediate.weather;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.sda.intermediate.customers.User;
import pl.sda.intermediate.customers.UserContextHolder;
import pl.sda.intermediate.customers.UserDAO;
import retrofit2.Retrofit;
import retrofit2.adapter.java8.Java8CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@Service
public class WeatherService {
    @Autowired
    private UserContextHolder userContextHolder;
    @Autowired
    private UserDAO userDAO;

    private String apiKey = "ea900b66f547fd7b23625544873a4200"; //klucz do serwisu (z konta w serwisie)
    private String baseURL = "http://api.openweathermap.org/"; //podstawowa czesc adresu url API serwisu


    public String prepareWeather() {
        Retrofit retrofit = new Retrofit.Builder() //biblioteka do zayptan http
                .baseUrl(baseURL)
                .addConverterFactory(GsonConverterFactory.create()) //mowimy ze chcemy uzyc gsona
                .addCallAdapterFactory(Java8CallAdapterFactory.create()) //pokazujemy ze bedziemy uzywac rozwiazan z J8 (CompletableFuture)
                .build();
        OpenWeatherMapJ8 openWeatherMapJ8 = retrofit.create(OpenWeatherMapJ8.class);
        CompletableFuture<WeatherWrapper> weatherByCity = openWeatherMapJ8.getWeatherByCity(
                currentUserCity(),
                apiKey,
                "metric",
                "pl");
        WeatherWrapper weatherWrapper = weatherByCity.join();
        Gson gson = new Gson();
        return gson.toJson(weatherWrapper);
    }

    private String currentUserCity() {
        return userDAO.findUserByEmail(userContextHolder.getUserLoggedIn())
                .map(e -> e.getUserAddress().getCity())
                .orElse("");
    }
}
