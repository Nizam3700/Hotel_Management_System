package com.example.Security.Controller;
import com.example.Security.Model.Book;
import com.example.Security.Model.Register;
import com.example.Security.Service.BookService;
import com.example.Security.Service.RegisterService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
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
@SecurityRequirement(name = "BasicAuth")
public class BookingController {

    @Autowired
    private BookService bookService;

    @Autowired
    private RegisterService registerService;

    @Operation(
            tags = "Post Booking",
            description = "Booking Allocate by Admin or Manage only"
    )
    @PostMapping("/{registerId}/book/{hotelId}")
    public ResponseEntity<String> bookRoom(@PathVariable("registerId") Long id, @PathVariable("hotelId") Long hotelId ) {
        Register register = registerService.getUserById(id)
                .orElseThrow(() -> new ValidationException("Register not found"));

        String response = bookService.bookRoom(register, hotelId);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @Operation(
            tags = "Get All Booking",
            description = "Booking Check by Admin only"
    )
    @GetMapping
    public ResponseEntity<List<Book>> getAllBookings()
    {
        List<Book> bookings = bookService.getAllBookings();
        return ResponseEntity.ok(bookings);
    }

    @Operation(
            tags = "Get specific Hotel",
            description = "Search by Admin or Manage only"
    )
    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookingById(@PathVariable Long id){
        Optional<Book> booking = bookService.getBookingById(id);
        return booking.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(
            tags = "Delete Booking",
            description = "Booking Cancel only by Admin only"
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBooking(@PathVariable Long id){
        String response = bookService.deleteBooking(id);
        return ResponseEntity.ok(response);
    }

}
