package iesluisvives.peluqueriadam.data.entity;

import java.time.LocalDate;
import java.time.LocalTime;

public class AppoinmentEntityWithUser {
    private String id;
    private LocalDate date;
    private LocalTime time;
    private ServiceEntity service;
    private UserEntity user;

    public AppoinmentEntityWithUser() {
    }

    public AppoinmentEntityWithUser(String id, LocalDate date, LocalTime time, ServiceEntity service, UserEntity user) {
        this.id = id;
        this.date = date;
        this.time = time;
        this.service = service;
        this.user = user;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public ServiceEntity getService() {
        return service;
    }

    public void setService(ServiceEntity service) {
        this.service = service;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "AppoinmentEntity{" +
                "id='" + id + '\'' +
                ", date=" + date +
                ", time=" + time +
                ", service=" + service +
                '}';
    }
}
