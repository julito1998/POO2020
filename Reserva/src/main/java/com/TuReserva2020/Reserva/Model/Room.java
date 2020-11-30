package com.TuReserva2020.Reserva.Model;

import com.sun.istack.NotNull;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="rooms")
public class Room {
        
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @NotNull
        private String name;

        @NotNull
        private float price;

        @NotNull
        private int occupancy;

        @NotNull
        private int availability;

        @NotNull
        private String facilities;
        
        public Room(){
              super();
        }

        public Room(@NotNull String name ,@NotNull float price, @NotNull int occupancy, @NotNull int availability, @NotNull String facilities) {
            super();
            this.name = name;
            this.price = price;
            this.occupancy = occupancy;
            this.availability = availability;
            this.facilities = facilities;
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
