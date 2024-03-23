package com.dailycodework.marinahotel.controller;
import com.dailycodework.marinahotel.exception.PhotoRetrievalException;
import com.dailycodework.marinahotel.exception.RessourceNotFoundException ;
import com.dailycodework.marinahotel.model.BookedRoom;
import com.dailycodework.marinahotel.model.Room;
import com.dailycodework.marinahotel.response.BookingResponse;
import com.dailycodework.marinahotel.response.RoomResponse;
import com.dailycodework.marinahotel.service.BookingService;
import com.dailycodework.marinahotel.service.IRoomService;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/rooms")



public class RoomController {
    private final IRoomService roomService ;
    private final BookingService bookingService ;
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

    @GetMapping("/room/types")
    public List<String> getRoomTypes (){
        return roomService.getAllRoomTypes();
    }

    @GetMapping("/all-rooms")
    public ResponseEntity<List<RoomResponse>> getAllRooms () throws SQLException {
        List<Room> rooms = roomService.getAllRooms();
        List<RoomResponse> roomResponses = new ArrayList<>();
        for (Room room :rooms){
            byte[] photoBytes = roomService.getRoomPhotoByRoomId(room.getId());
            if (photoBytes != null && photoBytes.length>0){
             String base64Photo = Base64.encodeBase64String(photoBytes);
             RoomResponse roomResponse = getRoomResponse(room);
             roomResponse.setPhoto(base64Photo);
             roomResponses.add(roomResponse);

            }
        }
        return ResponseEntity.ok(roomResponses);
    }

    @DeleteMapping("/delete/room/{roomId}")
public ResponseEntity<Void> deleteRoom(@PathVariable("roomId") Long roomId){
        roomService.deleteRoom(roomId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
}
    private RoomResponse getRoomResponse(Room room) {
      List<BookedRoom> bookings =getAllBookingsByRoomId(room.getId());
      List<BookingResponse> bookingInfo = bookings.stream().map(booking ->new BookingResponse(booking.getBookingId(),
              booking.getCheckInDate(),
              booking.getGetCheckInDateDate(),/* check out */
              booking.getBookingConformationCode())).toList();
      byte[] photoByte = null ;
      Blob photoBlob = room.getPhoto();
      if(photoBlob != null){
        try {
            photoByte =photoBlob.getBytes(1,(int) photoBlob.length());
        }catch (SQLException e){
            throw new PhotoRetrievalException("Error retrieving photo");
        }
      }

      return new RoomResponse(room.getId(),
              room.getRoomType(),
              room.getRoomPrice(),
              room.isBooked(),
              photoByte,
              bookingInfo
              ) ;
    }

    private List<BookedRoom> getAllBookingsByRoomId(Long roomId) {

        return bookingService.geyAllBookingsByRoomId(roomId) ;
    }
}
