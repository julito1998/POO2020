package com.TuReserva2020.Reserva.Model;

import com.sun.istack.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.*;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.format.annotation.DateTimeFormat;

/**
 *
 * @author Julito
 */
@Entity
@Table(name="bookings")
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @NotNull
    private User user;
    
    @JoinColumn(name = "room_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @NotNull
    private Room room;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull
    @Column (name="check_in")
    private Date checkIn;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column (name="check_out")
    private Date checkOut;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column (name="created_at")
    private Date createdAt;
    
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
       super();
    }

    public Booking(Long id, User user, Room room, Date checkIn, Date checkOut, Date createdAt, boolean breakfastIncluded, boolean parking, boolean freeCancelation, float cost) {
        this.id = id;
        this.user = user;
        this.room = room;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.createdAt = createdAt;
        this.breakfastIncluded = breakfastIncluded;
        this.parking = parking;
        this.freeCancelation = freeCancelation;
        this.cost = cost;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
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

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
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

    public float getCost() {
        return cost;
    }

    public void setCost(float cost) {
        this.cost = cost;
    }
    
    
    
}
