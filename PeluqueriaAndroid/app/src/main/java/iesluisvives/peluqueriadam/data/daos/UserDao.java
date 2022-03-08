package iesluisvives.peluqueriadam.data.daos;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import iesluisvives.peluqueriadam.data.entity.CreateUserEntity;
import iesluisvives.peluqueriadam.data.entity.UserEntity;

@Dao
public interface UserDao {

    @Insert
    public void insert(UserEntity userEntity);

    @Query("SELECT * FROM users where username = :username LIMIT 1")
    public UserEntity getUserByUsername(String username);
}
