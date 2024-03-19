package com.dailycodework.marinahotel.repository;

import com.dailycodework.marinahotel.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomRepository extends JpaRepository<Room,Long> {
}
