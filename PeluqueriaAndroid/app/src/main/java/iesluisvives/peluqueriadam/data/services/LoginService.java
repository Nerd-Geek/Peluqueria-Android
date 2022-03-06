package iesluisvives.peluqueriadam.data.services;

import iesluisvives.peluqueriadam.data.entity.LoginEntity;
import iesluisvives.peluqueriadam.data.entity.UserEntity;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface LoginService {

    @POST("/rest/users/login")
    Call<UserEntity> login(@Body LoginEntity login);
}
