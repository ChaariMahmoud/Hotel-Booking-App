package com.dailycodework.marinahotel.service;
import com.dailycodework.marinahotel.exception.RessourceNotFoundException;
import com.dailycodework.marinahotel.model.Room ;
import com.dailycodework.marinahotel.exception.InvalidBookingRequestException;
import com.dailycodework.marinahotel.model.BookedRoom;
import com.dailycodework.marinahotel.repository.BookingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@RequiredArgsConstructor
@Service
public class BookingService implements IBookingService {
    private final BookingRepository bookingRepository ;
    private final IRoomService roomService ;

    @Override
    public List<BookedRoom> getAllBookings() {
        return bookingRepository.findAll();
    }

    @Override
    public List<BookedRoom> getBookingsByUserEmail(String email) {
        return bookingRepository.findByGuestEmail(email);
    }

    @Override
    public List<BookedRoom> getAllBookingsByRoomId(Long roomId) {
        return bookingRepository.findByRoomId(roomId);
    }

    public List<BookedRoom> geyAllBookingsByRoomId(Long roomId) {

        return bookingRepository.findByRoomId(roomId) ;
    }

    @Override
    public void cancelBooking(Long bookingId) {
      bookingRepository.deleteById(bookingId);
    }

    @Override
    public String saveBooking(Long roomId, BookedRoom bookingRequest) {
        if (bookingRequest.getCheckOutDate().isBefore(bookingRequest.getCheckInDate())) {
            throw new InvalidBookingRequestException("Check-in date should come before check-out date");
        }

        Room room = roomService.getRoomById(roomId)
                .orElseThrow(() -> new RessourceNotFoundException("Room not found with id: " + roomId));

        List<BookedRoom> existingBookings = room.getBookings();
        boolean roomIsAvailable = roomIsAvailable(bookingRequest, existingBookings);

        if (roomIsAvailable) {
            room.addBooking(bookingRequest);
            bookingRepository.save(bookingRequest);
            return bookingRequest.getBookingConfirmationCode();
        } else {
            throw new InvalidBookingRequestException("Sorry, this room is not available for the selected dates");
        }
    }




    @Override
    public BookedRoom findByBookingConfirmationCode(String confirmationCode) {
        return bookingRepository.findByBookingConfirmationCode(confirmationCode);
    }

    private boolean roomIsAvailable(BookedRoom bookingRequest, List<BookedRoom> existingBookings) {
        return existingBookings.stream()
                .noneMatch(existingBooking ->
                         bookingRequest.getCheckInDate().equals(existingBooking.getCheckInDate())
                        ||bookingRequest.getCheckOutDate().isBefore(existingBooking.getCheckOutDate())
                        ||(bookingRequest.getCheckInDate().isAfter(existingBooking.getCheckInDate())
                        && bookingRequest.getCheckInDate().isBefore(existingBooking.getCheckOutDate()))
                        ||(bookingRequest.getCheckInDate().isBefore(existingBooking.getCheckInDate())
                        && bookingRequest.getCheckOutDate().equals(existingBooking.getCheckOutDate()))
                        ||(bookingRequest.getCheckInDate().isBefore(existingBooking.getCheckInDate())
                        && bookingRequest.getCheckOutDate().isAfter(existingBooking.getCheckOutDate()))
                        ||(bookingRequest.getCheckInDate().equals(existingBooking.getCheckOutDate())
                         && bookingRequest.getCheckOutDate().equals(existingBooking.getCheckInDate()))
                        || (bookingRequest.getCheckInDate().equals(existingBooking.getCheckOutDate())
                         && bookingRequest.getCheckOutDate().equals(bookingRequest.getCheckInDate()))
                );
    }
}
