package com.example.Security.Service;

import com.example.Security.Dto.Login;
import com.example.Security.Model.Register;
import com.example.Security.Repo.RegisterRepo;
import jakarta.validation.Valid;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RegisterService {

    @Autowired
    private RegisterRepo registerRepo;
    @Autowired
    private JWTService jwtService;

    @Autowired
    AuthenticationManager authManager;

//    Password BCrypt
    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

//    postmapping
    public Register register(@Valid Register register){
        String password = register.getPassword();
        if(password.length() < 8){
            throw new ValidationException("Password must atleast 8 characters long");
        }
        register.setPassword(encoder.encode(register.getPassword()));

        return registerRepo.save(register);
    }

//    GetMapping
    public Register userId(Long id) {
        return registerRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    public Register forgetPassword(String email, String newPassword){
        Register existingregister = registerRepo.findByEmail(email);
        if(existingregister != null){
            existingregister.setPassword(newPassword);
            return registerRepo.save(existingregister);

        }else{
            throw new RuntimeException("Email Not Found");
        }
    }

    public String verify(Login login) {
        Authentication authentication = authManager
                .authenticate(new UsernamePasswordAuthenticationToken(login.getEmail(), login.getPassword()));
        if(authentication.isAuthenticated()){
            System.out.println("Correct user, Success !");
            return jwtService.generateToken(login.getEmail());
        }
        return "Wrong User, Please Enter correct Email & Password";
    }

    public List<Register> getAllUsers(){
        return registerRepo.findAll();
    }

    public Optional<Register> getUserById(Long id){
        return registerRepo.findById(id);
    }

    public String deleteUser(Long id){
        if(!registerRepo.existsById(id)){
            throw new ValidationException("User Not Found");
        }
        registerRepo.deleteById(id);
        return "User Deleted successfully";
    }


}
