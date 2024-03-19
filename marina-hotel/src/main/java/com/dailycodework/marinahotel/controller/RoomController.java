package com.dailycodework.marinahotel.controller;

import com.dailycodework.marinahotel.model.Room;
import com.dailycodework.marinahotel.response.RoomResponse;
import com.dailycodework.marinahotel.service.IRoomService;
import com.jayway.jsonpath.internal.Path;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/rooms")



public class RoomController {
    private final IRoomService roomService ;
    @PostMapping("/add/new-room")
    public ResponseEntity<RoomResponse> addNewRoom (
            @RequestParam("photo") MultipartFile photo ,
            @RequestParam("roomType") String roomType ,
            @RequestParam("roomPrice") BigDecimal roomPrice) throws SQLException, IOException {
        Room savedRoom = roomService.addNewRoom(photo,roomType,roomPrice);
        RoomResponse response = new RoomResponse(
                savedRoom.getId(),
                savedRoom.getRoomType(),
                savedRoom.getRoomPrice() ) ;
        return ResponseEntity.ok(response);
    }
}
