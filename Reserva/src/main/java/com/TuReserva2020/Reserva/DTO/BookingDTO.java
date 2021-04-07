package com.TuReserva2020.Reserva.DTO;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BookingDTO implements Serializable {
    private static final SimpleDateFormat dateFormat
            = new SimpleDateFormat("yyyy-MM-dd");

    private Long id;
    private RoomDTO room;
    private UserNewDTO user;
    private String checkIn;
    private String checkOut;
    private String createdAt;
    private boolean breakfastIncluded;
    private boolean parking;
    private boolean freeCancelation;
    private double cost;

    public BookingDTO() {
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public RoomDTO getRoom() {
        return room;
    }

    public void setRoom(RoomDTO room) {
        this.room = room;
    }

    public String getCheckIn() {
        return checkIn;
    }

    public void setCheckIn(String checkIn) {
        this.checkIn = checkIn;
    }

    public String getCheckOut() {
        return checkOut;
    }

    public void setCheckOut(String checkOut) {
        this.checkOut = checkOut;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
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

    public Date getCheckInDateConverted() throws ParseException {
        return dateFormat.parse(this.checkIn);
    }

    public void setCheckInDate(Date date) {
        this.checkIn = dateFormat.format(date);
    }

    public Date getCheckOutDateConverted() throws ParseException {
        return dateFormat.parse(this.checkOut);
    }

    public void setCheckOutInDate(Date date) {
        this.checkOut = dateFormat.format(date);
    }

    public Date getCreatedAtDateConverted() throws ParseException {
        return dateFormat.parse(this.createdAt);
    }

    public void setCreatedAtInDate(Date date) {
        this.createdAt = dateFormat.format(date);
    }

    public static SimpleDateFormat getDateFormat() {
        return dateFormat;
    }

    public UserNewDTO getUser() {
        return user;
    }

    public void setUser(UserNewDTO user) {
        this.user = user;
    }
}
