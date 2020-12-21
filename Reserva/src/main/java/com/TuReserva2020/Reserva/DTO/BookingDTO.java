package com.TuReserva2020.Reserva.DTO;

import java.io.Serializable;
import java.util.Date;

public class BookingDTO implements Serializable {
    private Long id;
    private Long roomId;
    private Date checkIn;
    private Date checkOut;
    private Date reatedAt;
    private boolean breakfastIncluded;
    private boolean parking;
    private boolean freeCancelation;
    private double cost;

    public BookingDTO() {
        super();
    }

    public BookingDTO(Long id, Long roomId, Date checkIn, Date checkOut, Date reatedAt, boolean breakfastIncluded, boolean parking, boolean freeCancelation, double cost) {
        super();
        this.id = id;
        this.roomId = roomId;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.reatedAt = reatedAt;
        this.breakfastIncluded = breakfastIncluded;
        this.parking = parking;
        this.freeCancelation = freeCancelation;
        this.cost = cost;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getRoomId() {
        return roomId;
    }

    public void setRoomId(Long roomId) {
        this.roomId = roomId;
    }

    public Date getCheckIn() {
        return checkIn;
    }

    public void setCheckIn(Date checkIn) {
        this.checkIn = checkIn;
    }

    public Date getCheckOut() {
        return checkOut;
    }

    public void setCheckOut(Date checkOut) {
        this.checkOut = checkOut;
    }

    public Date getReatedAt() {
        return reatedAt;
    }

    public void setReatedAt(Date reatedAt) {
        this.reatedAt = reatedAt;
    }

    public boolean isBreakfastIncluded() {
        return breakfastIncluded;
    }

    public void setBreakfastIncluded(boolean breakfastIncluded) {
        this.breakfastIncluded = breakfastIncluded;
    }

    public boolean isParking() {
        return parking;
    }

    public void setParking(boolean parking) {
        this.parking = parking;
    }

    public boolean isFreeCancelation() {
        return freeCancelation;
    }

    public void setFreeCancelation(boolean freeCancelation) {
        this.freeCancelation = freeCancelation;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }
}
