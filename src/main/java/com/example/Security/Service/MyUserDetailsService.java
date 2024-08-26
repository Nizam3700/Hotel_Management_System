package com.example.Security.Service;

import com.example.Security.Model.UserPrincipal;
import com.example.Security.Model.Register;
import com.example.Security.Repo.RegisterRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private RegisterRepo registerRepo;

    @Override
    public UserDetails loadUserByUsername(String Email) throws UsernameNotFoundException {

        Register email = registerRepo.findByEmail(Email);
        if(email == null){
            System.out.println("Email Not Found !!!");
            throw  new UsernameNotFoundException("Email Not Found");
        }
        return new UserPrincipal(email);
    }
}
