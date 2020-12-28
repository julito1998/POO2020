package com.TuReserva2020.Reserva.Repository;


import com.TuReserva2020.Reserva.DTO.RoomDTO;
import com.TuReserva2020.Reserva.Model.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface RoomRepo extends JpaRepository<Room,Long> {
    @Query("select r from Room r where r.occupancy >= :occupancy and r.availability >= (" +
            "Select count(b) From Booking b where b.room=r and b.checkIn BETWEEN :checkIn and :checkOut)")
    List<Room> findRoomAvailable(@Param("checkIn") Date checkIn,@Param("checkOut") Date checkOut,@Param("occupancy")  int occupancy);

    @Query("select r from Room r where r.id = :roomId and r.availability >= (" +
            "Select count(b) From Booking b where b.room = r and b.checkIn between :checkIn and :checkOut)")
    Room isRoomAvailable(@Param("checkIn") Date checkIn,@Param("checkOut") Date checkOut, @Param("roomId") Long roomId);
}
