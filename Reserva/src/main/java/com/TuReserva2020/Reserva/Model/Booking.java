/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.TuReserva2020.Reserva.Model;

import com.sun.istack.NotNull;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

/**
 *
 * @author Julito
 */
@Entity
@Table(name="booking")
public class Booking implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @NotNull
    private List<User> user;
    
    @JoinColumn(name = "room_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @NotNull
    private List<Room> room;
    
    @NotNull
    @Column (name="check_in")
    private Date checkIn;
    
    @Column (name="check_out")
    private Date checkOut;
    
    @NotNull
    @Column(name="breakfast_included")
    private boolean breakfastIncluded;
    
    @NotNull
    private boolean parking;
    
    @NotNull
    @Column(name="free_cancelation")
    private boolean freeCancelation;
    
    @NotNull
    private float cost;

    public Booking() {
    }

    public Booking(@NotNull Long id,@NotNull Date checkIn,@NotNull Date checkOut,@NotNull boolean breakfastIncluded,@NotNull boolean parking,@NotNull boolean freeCancelation,@NotNull float cost) {
        this.id = id;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
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

    public float getCost() {
        return cost;
    }

    public void setCost(float cost) {
        this.cost = cost;
    }
    
}
