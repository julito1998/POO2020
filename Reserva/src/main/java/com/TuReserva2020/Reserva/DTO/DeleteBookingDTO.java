package com.TuReserva2020.Reserva.DTO;

import javax.sql.rowset.serial.SerialArray;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DeleteBookingDTO implements Serializable {
    private static final SimpleDateFormat dateFormat
            = new SimpleDateFormat("yyyy-MM-dd");

    private long id;
    private String checkIn;

    public Date getCheckInDateConverted() throws ParseException {
        return dateFormat.parse(this.checkIn);
    }

    public void setCheckInDate(Date date) {
        this.checkIn = dateFormat.format(date);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCheckIn() {
        return checkIn;
    }

    public void setCheckIn(String checkIn) {
        this.checkIn = checkIn;
    }
}
