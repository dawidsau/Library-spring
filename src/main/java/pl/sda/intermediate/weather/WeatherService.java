package pl.sda.intermediate.weather;

import retrofit2.Retrofit;
import retrofit2.adapter.java8.Java8CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class WeatherService {

    private String apiKey = "ea900b66f547fd7b23625544873a4200";
    private String baseURL = "http://api.openweathermap.org/";


    public String prepareWeather(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseURL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(Java8CallAdapterFactory.create())
                .build();


    }
}
