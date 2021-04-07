package com.TuReserva2020.Reserva.DTO;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PaymentDTO implements Serializable {
    private static final SimpleDateFormat dateFormat
            = new SimpleDateFormat("yyyy-MM-dd");

    private Long id;
    private BookingDTO bookingDTO;
    private String createdAt;
    private String card;
    private String cardNumber;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BookingDTO getBooking() {
        return bookingDTO;
    }

    public void setBooking(BookingDTO bookingDTO) {
        this.bookingDTO = bookingDTO;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
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

    public Date getCreatedAtDateConverted() throws ParseException {
        return dateFormat.parse(this.createdAt);
    }


}
