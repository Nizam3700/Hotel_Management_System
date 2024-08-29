package com.example.Security.Service;

import com.example.Security.Model.Book;
import com.example.Security.Model.Hotel;
import com.example.Security.Model.Register;
import com.example.Security.Repo.BookingRepo;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    @Autowired
    private IHotelService hotelService;

    @Autowired
    private BookingRepo bookingRepo;


    public String bookRoom (Register register, Long hotelId){
        Hotel hotel = hotelService.getHotelById(hotelId);

        List<Book> bookings = bookingRepo.findByHotel(hotel);
        boolean exists = bookingRepo.existsByUserAndHotel(register, hotel);
        if(exists){
            throw new ValidationException("User has already booked a room in this hotel");

        }

        if(bookings.size() >= hotel.getAvailableRooms()){
            throw new ValidationException("No rooms available in this hotel");

        }

        Book booking = new Book();
        booking.setHotel(hotel);
        booking.setUser(register);
        bookingRepo.save(booking);

        return "Room booked Successfully";
    }

    public List<Book> getAllBookings(){
        return bookingRepo.findAll();
    }

    public Optional<Book> getBookingById(Long id){
        return bookingRepo.findById(id);
    }

    public String deleteBooking(long id){
        if(!bookingRepo.existsById(id)){
            throw new ValidationException("Booking not Found");
        }
        bookingRepo.deleteById(id);
        return "Booking deleted successfully";
    }


}
