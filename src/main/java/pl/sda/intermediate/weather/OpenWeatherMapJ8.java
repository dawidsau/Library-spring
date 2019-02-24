package pl.sda.intermediate.weather;

import retrofit2.http.GET;
import retrofit2.http.Query;

import java.util.concurrent.CompletableFuture;

public interface OpenWeatherMapJ8 {

    @GET("data/2.5/weather") //czesc adresu url
    CompletableFuture<WeatherWrapper> getWeatherByCity (
            @Query("q")String cityNameWithCountry, //parametry przekazywane w urlu
            @Query("appid") String apiKey,
            @Query("units")String units,
            @Query("lang") String language
    );
}
