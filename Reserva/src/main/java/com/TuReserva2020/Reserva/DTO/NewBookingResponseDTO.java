package com.TuReserva2020.Reserva.DTO;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class NewBookingResponseDTO implements Serializable {

    private static final SimpleDateFormat dateFormat
                = new SimpleDateFormat("yyyy-MM-dd");

        private RoomDTO room;
        private String checkIn;
        private String checkOut;
        private int occupancy;
        private boolean parkingIncluded;
        private boolean freeCancelationIncluded;
        private boolean breakfastIncluded;

    //tener en cuenta final: desayuno, cochera, cancelación ...

    public boolean isParkingIncluded() {
        return parkingIncluded;
    }

    public void setParkingIncluded(boolean parkingIncluded) {
        this.parkingIncluded = parkingIncluded;
    }

    public boolean isFreeCancelationIncluded() {
        return freeCancelationIncluded;
    }

    public void setFreeCancelationIncluded(boolean freeCancelationIncluded) {
        this.freeCancelationIncluded = freeCancelationIncluded;
    }

    public boolean isBreakfastIncluded() {
        return breakfastIncluded;
    }

    public void setBreakfastIncluded(boolean breakfastIncluded) {
        this.breakfastIncluded = breakfastIncluded;
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

        public int getOccupancy() {
            return occupancy;
        }

        public void setOccupancy(int occupancy) {
            this.occupancy = occupancy;
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

        public void setCheckOutDate(Date date) {
            this.checkOut = dateFormat.format(date);
        }

}

