package com.example.Security.Controller;

import com.example.Security.Dto.Login;
import com.example.Security.Model.Register;
import com.example.Security.Service.RegisterService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@SecurityRequirement(name = "BasicAuth")
@RequestMapping("/register")
public class RegisterController {

    @Autowired
    private RegisterService registerService;


    @Operation(
            tags = "Get All Users",
            description = "Check by Admin or Manager"
    )
    @GetMapping()
    public ResponseEntity<List<Register>> getAllUser(){
        List<Register> allUser = registerService.getAllUsers();
        return ResponseEntity.ok(allUser);
    }

    @Operation(
            tags = "Get Specific User",
            description = "Check by Admin or Manager"
    )
    @GetMapping("/{id}")
    public ResponseEntity<Register> getUserById(@PathVariable Long id) {
        Optional<Register> user = Optional.ofNullable(registerService.userId(id));
        if (user.isPresent()) {
            return ResponseEntity.ok(user.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(
            tags = "Put User email",
            description = "Forget Password of User"
    )
    @PutMapping("/{email}")
    public ResponseEntity<Register> ForgetUserpassword(@RequestParam String email, @RequestParam String newPassword){
        Register updateRegister = registerService.forgetPassword(email, newPassword);
        return ResponseEntity.ok(updateRegister);
    }

    @Operation(
            tags = "Delete User",
            description = "Delete by Admin or Manager"
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id){
        String response = registerService.deleteUser(id);
        return ResponseEntity.ok(response);
    }
}


