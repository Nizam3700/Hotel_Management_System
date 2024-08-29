package com.example.Security.Repo;

import com.example.Security.Model.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HotelRepo extends JpaRepository<Hotel,Long> {
}
