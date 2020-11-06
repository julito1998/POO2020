package com.TuReserva2020.Reserva.Model;

import com.sun.istack.NotNull;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;


/*id: Long
createdAt: Date
booking: Booking
card: String
cardNumber: String*/

@Entity
@Table(name="payments")
public class Payment implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Column(name="created_at")
    private Date createdAt;
    
    @JoinColumn(name = "booking_id", referencedColumnName = "id")
    @OneToOne(optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @NotNull
    private Booking booking;

    @NotNull
    private String card;

    @NotNull
    @Column(name="card_number")
    private String cardNumber;

    public Payment(){}


    public Payment(@NotNull Long id,@NotNull Date createdAt,@NotNull String card,@NotNull String cardNumber) {
        this.id = id;
        this.createdAt = createdAt;
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
