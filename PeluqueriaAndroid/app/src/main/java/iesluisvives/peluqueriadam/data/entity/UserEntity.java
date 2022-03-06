package iesluisvives.peluqueriadam.data.entity;

import androidx.annotation.NonNull;
import androidx.annotation.Size;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.Set;
@Entity(tableName = "users")
public class UserEntity {
    @PrimaryKey
    @NonNull
    private String id;
    private String image;
    @NonNull
    private String username;
    @NonNull
    private String name;
    @NonNull
    private String surname;
    @NonNull
    @Size(9)
    private String phoneNumber;
    @NonNull
    private String email;
    @NonNull
    private UserGender gender;

    private String token;

    @Ignore
    public UserEntity() {
    }

    public UserEntity(@NonNull String id, String image, @NonNull String username,
                      @NonNull String name, @NonNull String surname, @NonNull String phoneNumber,
                      @NonNull String email, @NonNull UserGender gender, String token) {
        this.id = id;
        this.image = image;
        this.username = username;
        this.name = name;
        this.surname = surname;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.gender = gender;
        this.token = token;
    }

    @NonNull
    public String getId() {
        return id;
    }

    public void setId(@NonNull String id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @NonNull
    public String getUsername() {
        return username;
    }

    public void setUsername(@NonNull String username) {
        this.username = username;
    }

    @NonNull
    public String getName() {
        return name;
    }

    public void setName(@NonNull String name) {
        this.name = name;
    }

    @NonNull
    public String getSurname() {
        return surname;
    }

    public void setSurname(@NonNull String surname) {
        this.surname = surname;
    }

    @NonNull
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(@NonNull String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @NonNull
    public String getEmail() {
        return email;
    }

    public void setEmail(@NonNull String email) {
        this.email = email;
    }

    @NonNull
    public UserGender getGender() {
        return gender;
    }

    public void setGender(@NonNull UserGender gender) {
        this.gender = gender;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
