package com.example.Security.Repo;

import com.example.Security.Model.Book;
import com.example.Security.Model.Hotel;
import com.example.Security.Model.Register;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface BookingRepo extends JpaRepository<Book,Long> {
    List<Book> findByHotel(Hotel hotel);

    boolean existsByUserAndHotel(Register register, Hotel hotel);
}
