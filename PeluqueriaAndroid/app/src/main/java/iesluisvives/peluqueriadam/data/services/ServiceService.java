package iesluisvives.peluqueriadam.data.services;

import java.util.List;

import iesluisvives.peluqueriadam.data.entity.ServiceEntity;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;

public interface ServiceService {

    @GET("/rest/services/all")
    Call<List<ServiceEntity>> getAllServices(@Header("Authorization") String token);
}
