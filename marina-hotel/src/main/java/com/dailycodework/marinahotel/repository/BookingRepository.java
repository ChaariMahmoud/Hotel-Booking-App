package com.dailycodework.marinahotel.repository;

import com.dailycodework.marinahotel.model.BookedRoom;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookingRepository extends JpaRepository<BookedRoom,Long> {
  
    List<BookedRoom> findByRoomId(Long roomId);

    BookedRoom findByBookingConfimationCode(String confirmationCode);
}
