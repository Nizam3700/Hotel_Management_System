package com.example.Security.Repo;

import com.example.Security.Model.Register;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RegisterRepo extends JpaRepository<Register, Long> {

    Optional<Register> findById(Long id);
    public Register findByEmail(String email);

}
