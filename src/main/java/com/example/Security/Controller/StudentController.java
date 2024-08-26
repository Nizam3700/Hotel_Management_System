package com.example.Security.Controller;

import com.example.Security.Model.Student;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {

    private List<Student> students = new ArrayList<>(
            List.of( new Student(1, "NIZAM", 80),
                    new Student(2, "Akram" , 65)
    ));
    @GetMapping
    public List<Student> getStudents(){
        return students;
    }

    @GetMapping("/csrf-token")
    public CsrfToken getCsrfToken(HttpServletRequest request){

        return (CsrfToken) request.getAttribute("_csrf");
    }
    @PostMapping
    public Student addStudent(@RequestBody Student student){
        students.add(student);
        return student;
    }



}
