package com.TuReserva2020.Reserva.Model;

import com.sun.istack.NotNull;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

/**
 *
 * @author Julito
 */
@Entity
@Table(name="cancellation")
public class Cancellation implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @JoinColumn(name = "booking_id", referencedColumnName = "id")
    @OneToOne(optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @NotNull
    private Booking booking;
    
    @NotNull
    @Column(name="created_at")
    private Date createdAt;
    
    public Cancellation(@NotNull Long id,@NotNull Date createdAt) {
        this.id = id;
        this.createdAt = createdAt;
    }

    public Cancellation() {
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
    
}
