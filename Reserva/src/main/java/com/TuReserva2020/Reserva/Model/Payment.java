package com.TuReserva2020.Reserva.Model;

import com.sun.istack.NotNull;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


/*id: Long
createdAt: Date
booking: Booking
card: String
cardNumber: String*/

@Entity
@Table(name="payment")
public class Payment implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Column(name="created_at")
    private Date createdAt;

    @NotNull
    private Booking booking;

    @NotNull
    private String card;

    @NotNull
    @Column(name="card_number")
    private String cardNumber;

    public Payment(){}


    public Payment(Long id, Date createdAt, Booking booking, String card, String cardNumber) {
        this.id = id;
        this.createdAt = createdAt;
        this.booking = booking;
        this.card = card;
        this.cardNumber = cardNumber;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Booking getBooking() {
        return booking;
    }

    public void setBooking(Booking booking) {
        this.booking = booking;
    }

    public String getCard() {
        return card;
    }

    public void setCard(String card) {
        this.card = card;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }
}
