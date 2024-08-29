package com.example.Security.Controller;
import com.example.Security.Model.Book;
import com.example.Security.Model.Register;
import com.example.Security.Service.BookService;
import com.example.Security.Service.RegisterService;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;

@RestController
@RequestMapping("/bookings")
public class BookingController {

    @Autowired
    private BookService bookService;

    @Autowired
    private RegisterService registerService;

    @PostMapping("/{registerId}/book/{hotelId}")
    public ResponseEntity<String> bookRoom(@PathVariable("registerId") Long id, @PathVariable("hotelId") Long hotelId ) {
        Register register = registerService.getUserById(id)
                .orElseThrow(() -> new ValidationException("Register not found"));

        String response = bookService.bookRoom(register, hotelId);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<List<Book>> getAllBookings()
    {
        List<Book> bookings = bookService.getAllBookings();
        return ResponseEntity.ok(bookings);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookingById(@PathVariable Long id){
        Optional<Book> booking = bookService.getBookingById(id);
        return booking.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBooking(@PathVariable Long id){
        String response = bookService.deleteBooking(id);
        return ResponseEntity.ok(response);
    }

}
