package iesluisvives.peluqueriadam.data.entity;

import java.time.LocalDate;
import java.time.LocalTime;

public class AppoinmentEntity {
    private String id;
    private LocalDate date;
    private LocalTime time;
    private ServiceEntity service;

    public AppoinmentEntity() {
    }

    public AppoinmentEntity(String id, LocalDate date, LocalTime time, ServiceEntity service) {
        this.id = id;
        this.date = date;
        this.time = time;
        this.service = service;
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
}
