package iesluisvives.peluqueriadam.data.services;

import java.util.List;

import iesluisvives.peluqueriadam.data.entity.ServiceEntity;
import retrofit2.Call;
import retrofit2.http.GET;

public interface ServiceService {

    @GET("/services")
    Call<List<ServiceEntity>> getAllServices();
}
