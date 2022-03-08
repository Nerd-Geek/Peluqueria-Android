package iesluisvives.peluqueriadam.data.services;

import java.util.List;

import iesluisvives.peluqueriadam.data.entity.AppoinmentEntity;
import iesluisvives.peluqueriadam.data.entity.AppoinmentEntityWithUser;
import iesluisvives.peluqueriadam.data.entity.CreateAppoinmentEntity;
import iesluisvives.peluqueriadam.data.entity.ServiceEntity;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface AppoinmentService {

    @GET("/rest/appointments/mobile")
    Call<List<AppoinmentEntity>> getAllAppoinmentsByDateAndServiceId(@Query("date")String date, @Query("service_id")String service_id,@Header("Authorization") String token);

    @GET("/rest/appointments/mobile/")
    Call<List<AppoinmentEntity>> getAllAppoinmentsByUserName(@Query("searchQuery")String username,@Header("Authorization") String token);

    @POST("/rest/appointments/")
    Call<AppoinmentEntityWithUser> createAppointment(@Body CreateAppoinmentEntity createAppoinmentEntity, @Header("Authorization") String token);

}
