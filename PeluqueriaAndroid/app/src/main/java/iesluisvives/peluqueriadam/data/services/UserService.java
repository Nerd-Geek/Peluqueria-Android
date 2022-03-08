package iesluisvives.peluqueriadam.data.services;

import iesluisvives.peluqueriadam.data.entity.CreateUserEntity;
import iesluisvives.peluqueriadam.data.entity.UserEntity;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface UserService {

    @POST("/rest/users/")
    Call<CreateUserEntity> createUser(@Body CreateUserEntity createUserEntity);

    @PUT("/rest/users/{id}")
    Call<UserEntity> updateUserById(@Path("id")String id,@Header("Authorization") String token);
}
