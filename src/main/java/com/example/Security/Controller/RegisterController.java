package com.example.Security.Controller;

import com.example.Security.Dto.Login;
import com.example.Security.Model.Register;
import com.example.Security.Service.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
//@RequestMapping("/register")
public class RegisterController {

    @Autowired
    private RegisterService registerService;


    @PostMapping("/userlogin")
    public String login(@RequestBody Login login){
        return registerService.verify(login);
    }
    @PostMapping("/register")
    public ResponseEntity<Register> registerUser(@RequestBody Register register) {
        // Save the user to the database
        Register savedRegister = registerService.register(register);
        return ResponseEntity.ok(savedRegister);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Register> getUserById(@PathVariable Long id) {
        Optional<Register> user = Optional.ofNullable(registerService.userId(id));
        if (user.isPresent()) {
            return ResponseEntity.ok(user.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{email}")
    public ResponseEntity<Register> ForgetUserpassword(@RequestParam String email, @RequestParam String newPassword){
        Register updateRegister = registerService.forgetPassword(email, newPassword);
        return ResponseEntity.ok(updateRegister);
    }
}


