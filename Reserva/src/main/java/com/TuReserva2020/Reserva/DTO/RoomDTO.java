package com.TuReserva2020.Reserva.DTO;


import java.io.Serializable;

public class RoomDTO implements Serializable {
    private Long id;
    private String name;
    private float price;
    private int occupancy;
    private int availability;
    private String facilities;

    public RoomDTO(Long id, String name, float price, int occupancy, int availability, String facilities) {
        super();
        this.id = id;
        this.name = name;
        this.price = price;
        this.occupancy = occupancy;
        this.availability = availability;
        this.facilities = facilities;
    }

    public RoomDTO() {
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getOccupancy() {
        return occupancy;
    }

    public void setOccupancy(int occupancy) {
        this.occupancy = occupancy;
    }

    public int getAvailability() {
        return availability;
    }

    public void setAvailability(int availability) {
        this.availability = availability;
    }

    public String getFacilities() {
        return facilities;
    }

    public void setFacilities(String facilities) {
        this.facilities = facilities;
    }
}
