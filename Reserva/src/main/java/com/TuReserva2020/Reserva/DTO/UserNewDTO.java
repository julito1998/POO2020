package com.TuReserva2020.Reserva.DTO;

import com.sun.istack.NotNull;

import javax.persistence.Column;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class UserNewDTO {
    private static final SimpleDateFormat dateFormat
            = new SimpleDateFormat("yyyy-MM-dd");

    private String email;
    private String password;
    private Long id;
    private String firstName;
    private String lastName;
    private String birthDate;
    private String nationality;

    public UserNewDTO(String email, String password, Long id, String firstName, String lastName, String birthDate, String nationality) {
        this.email = email;
        this.password = password;
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.nationality = nationality;
    }

    public UserNewDTO() {
        super();
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public static SimpleDateFormat getDateFormat() {
        return dateFormat;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getCheckInDateConverted() throws ParseException {
        return dateFormat.parse(this.birthDate);
    }

    public void setCheckInDate(Date date) {
        this.birthDate = dateFormat.format(date);
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
