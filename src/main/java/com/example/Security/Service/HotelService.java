package com.example.Security.Service;

import com.example.Security.Enum.Role;
import com.example.Security.Exceptions.HotelAlreadyExistsException;
import com.example.Security.Model.Hotel;
import com.example.Security.Model.Register;
import com.example.Security.Repo.HotelRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HotelService implements IHotelService{

    @Autowired
    private HotelRepo hotelRepo;

    @Override
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
    public String addHotel(Hotel hotel) {
        if (hotelRepo.existsById(hotel.getId())) {
            throw new HotelAlreadyExistsException("Hotel with id " + hotel.getId() + " already exists.");
        }
        hotelRepo.save(hotel);
        return "Hotel added";
    }

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public String deleteHotel(Long id) {
        if(!hotelRepo.existsById(id)){
            throw new HotelAlreadyExistsException("Hotel with id" + id + "Not found");
        }
        hotelRepo.deleteById(id);
        return "hotel deleted";
    }

    @Override
    public Hotel getHotelById(Long id) {
        return hotelRepo.findById(id)
                .orElseThrow(() -> new HotelAlreadyExistsException("Hotel with id" + id + "Not Found"));
    }

    @Override
    public List<Hotel> getHotels() {
        return hotelRepo.findAll();
    }
}
