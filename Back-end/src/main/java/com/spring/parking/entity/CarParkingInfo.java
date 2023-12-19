package com.spring.parking.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "car_parking_info")
public class CarParkingInfo {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private long vehicleRegistration;
    private String brand;
    private String model;
    private String color;
    private String type;
    private LocalDateTime entryTime;

    private double totalPrice;



    public CarParkingInfo() {
    }

    public CarParkingInfo(Long vehicleRegistration, String brand, String model, String color, String type, LocalDateTime entryTime, double totalPrice) {
        this.vehicleRegistration = vehicleRegistration;
        this.brand = brand;
        this.model = model;
        this.color = color;
        this.type = type;
        this.entryTime = entryTime;
        this.totalPrice = totalPrice;
    }

    public Long getVehicleRegistration() {
        return vehicleRegistration;
    }

    public void setVehicleRegistration(Long vehicleRegistration) {
        this.vehicleRegistration = vehicleRegistration;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public LocalDateTime getEntryTime() {
        return entryTime;
    }

    public void setEntryTime(LocalDateTime entryTime) {
        this.entryTime = entryTime;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

}
