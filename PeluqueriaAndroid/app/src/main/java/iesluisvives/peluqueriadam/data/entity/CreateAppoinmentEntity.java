package iesluisvives.peluqueriadam.data.entity;

import java.time.LocalDate;
import java.time.LocalTime;

public class CreateAppoinmentEntity {
    private String id;
    private LocalDate date;
    private LocalTime time;
    private String userId;
    private String serviceId;

    public CreateAppoinmentEntity() {
    }

    public CreateAppoinmentEntity(String id, LocalDate date, LocalTime time, String userId, String serviceId) {
        this.id = id;
        this.date = date;
        this.time = time;
        this.userId = userId;
        this.serviceId = serviceId;
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

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }
}
