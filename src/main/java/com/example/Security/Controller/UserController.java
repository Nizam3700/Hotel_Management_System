package com.example.Security.Controller;

import com.example.Security.Dto.Login;
import com.example.Security.Model.Register;
import com.example.Security.Service.RegisterService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private RegisterService registerService;

    @Operation(
            tags = "Post Login User",
            description = "login Using email & password"
    )
    @PostMapping("/userlogin")
    public String login(@RequestBody Login login){

        return registerService.verify(login);
    }

    @Operation(
            tags = "Post Register User",
            description = "Anyone can register CUSTOMER, ADMIN, MANAGER"
    )
    @PostMapping("/userregister")
    public ResponseEntity<Register> registerUser(@Valid @RequestBody Register register) {
        // Save the user to the database
        Register savedRegister = registerService.register(register);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedRegister);
    }
}
