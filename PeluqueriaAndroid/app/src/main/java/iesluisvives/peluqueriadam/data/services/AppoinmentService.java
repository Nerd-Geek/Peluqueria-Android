package iesluisvives.peluqueriadam.data.services;

import java.util.List;

import iesluisvives.peluqueriadam.data.entity.AppoinmentEntity;
import iesluisvives.peluqueriadam.data.entity.ServiceEntity;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface AppoinmentService {

    @GET("/appointments/mobile")
    Call<List<AppoinmentEntity>> getAllAppoinmentsByDateAndServiceId(@Query("date")String date, @Query("service_id")String service_id);

    @GET("/appointments/mobile")
    Call<List<AppoinmentEntity>> getAllAppoinmentsByUserName(@Query("searchQuery")String username);

    @POST("/appointments")
    Call<AppoinmentEntity> createAppointment(@Body ServiceEntity serviceEntity);

}
