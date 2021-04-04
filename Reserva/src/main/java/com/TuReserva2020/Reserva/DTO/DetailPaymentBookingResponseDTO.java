package com.TuReserva2020.Reserva.DTO;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DetailPaymentBookingResponseDTO implements Serializable {
    private static final SimpleDateFormat dateFormat
            = new SimpleDateFormat("yyyy-MM-dd");

    private Long id;
    private BookingDTO booking;
    private String cheatedAt;
    private String card;
    private String cardNumber;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BookingDTO getBooking() {
        return booking;
    }

    public void setBooking(BookingDTO booking) {
        this.booking = booking;
    }

    public String getCheatedAt() {
        return cheatedAt;
    }

    public void setCheatedAt(String cheatedAt) {
        this.cheatedAt = cheatedAt;
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

    public Date getCheckInDateConverted() throws ParseException {
        return dateFormat.parse(this.cheatedAt);
    }

    public void setCheckInDate(Date date) {
        this.cheatedAt = dateFormat.format(date);
    }

}
