package com.example.Security.Controller;

import com.example.Security.Model.Hotel;
import com.example.Security.Model.Register;
import com.example.Security.Service.HotelService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hotels")
@SecurityRequirement(name = "BasicAuth")
public class HotelController {

    @Autowired
    private HotelService hotelService;

    @Operation(
            tags = "Post Add Hotel",
            description = "Hotel will Add My Admin or Manage only"
    )
    @PostMapping
    public ResponseEntity<String> addHotel(@Valid @RequestBody Hotel hotel) {
        String response = hotelService.addHotel(hotel);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @Operation(
            tags = "Get All Hotel"
    )
    @GetMapping
    public ResponseEntity<List<Hotel>> getHotels(){
        List<Hotel> hotels = hotelService.getHotels();
        return ResponseEntity.ok(hotels);
    }
    @Operation(
            tags = "Get Specific Hotel"
    )
    @GetMapping("/{id}")
    public ResponseEntity<Hotel> getHotelById(@PathVariable Long id){
        Hotel hotel = hotelService.getHotelById(id);
        return ResponseEntity.ok(hotel);
    }

    @Operation(
            tags = "Delete Hotel",
            description = "Cancel the Hotel only by Admin"
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteHotel(@PathVariable Long id){
        String response = hotelService.deleteHotel(id);
        return ResponseEntity.ok(response);
    }

}
