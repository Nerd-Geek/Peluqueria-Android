package iesluisvives.peluqueriadam.data.services;

import iesluisvives.peluqueriadam.data.entity.CreateUserEntity;
import iesluisvives.peluqueriadam.data.entity.UserEntity;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface UserService {

    @GET("/users/{username}")
    Call<UserEntity> getUserByUsername(@Path("username")String username);

    @POST("/users")
    Call<CreateUserEntity> createUser(@Body CreateUserEntity createUserEntity);

    @PUT("/users/{id}")
    Call<UserEntity> updateUserById(@Path("id")String id);
}
