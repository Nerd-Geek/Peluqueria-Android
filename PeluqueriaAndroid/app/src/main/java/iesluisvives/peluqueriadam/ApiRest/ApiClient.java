package iesluisvives.peluqueriadam.ApiRest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.time.LocalDate;
import java.time.LocalTime;

import iesluisvives.peluqueriadam.utils.LocalDateConversor;
import iesluisvives.peluqueriadam.utils.LocalTimeConversor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
    public  static  final String URL_001 = "http://10.0.2.2:13169/";

    public static Retrofit getClient(){

        //interceptar la comunicacion
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .build();

        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(LocalDate.class, new LocalDateConversor());
        gsonBuilder.registerTypeAdapter(LocalTime.class, new LocalTimeConversor());
        Gson gson = gsonBuilder.setPrettyPrinting().create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL_001)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                //.addConverterFactory(JacksonConverterFactory.create())
                .client(okHttpClient)
                .build();

        return retrofit;
    }
}
