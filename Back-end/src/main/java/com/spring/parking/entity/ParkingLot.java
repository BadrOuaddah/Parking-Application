package com.spring.parking.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;

@Entity
@Table(name="parking_lot")
public class ParkingLot {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private long parkingLotNumber;

    @OneToOne(cascade = CascadeType.ALL)
    private CarParkingInfo carParkingInfo;

    @ManyToOne
    @JoinColumn(name="parking_id", nullable=false)
    @JsonIgnore
    private Parking parking;

    private double price;

    public ParkingLot() {

    }

    public ParkingLot(long parkingLotNumber, CarParkingInfo carParkingInfo, Parking parking, double price) {
        this.parkingLotNumber = parkingLotNumber;
        this.carParkingInfo = carParkingInfo;
        this.parking = parking;
        this.price = price;
    }

    public long getParkingLotNumber() {
        return parkingLotNumber;
    }

    public void setParkingLotNumber(long parkingLotNumber) {
        this.parkingLotNumber = parkingLotNumber;
    }

    public CarParkingInfo getCarParkingInfo() {
        return carParkingInfo;
    }

    public void setCarParkingInfo(CarParkingInfo carParkingInfo) {
        this.carParkingInfo = carParkingInfo;
    }

    public Parking getParking() {
        return parking;
    }

    public void setParking(Parking parking) {
        this.parking = parking;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
