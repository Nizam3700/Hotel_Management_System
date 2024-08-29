package com.example.Security.Service;

import com.example.Security.Model.Hotel;

import java.util.List;

public interface IHotelService {

    String addHotel(Hotel hotel);
    String deleteHotel(Long id);
    Hotel getHotelById(Long id);
    List<Hotel> getHotels();


}
