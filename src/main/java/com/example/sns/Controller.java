package com.example.sns;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;


@RestController
public class Controller {
    @GetMapping("/api/get")
    public ResponseEntity<?> getStaticJson(User user) {
        return ResponseEntity.ok("{\"message\":\"Static_Json\"}");
    }

    @PostMapping("/api/processJson")
    public ResponseEntity<?> processJson(@RequestBody User user) {

        if (user.getLogin().trim().isEmpty()
                || user.getPassword().trim().isEmpty()){
            return ResponseEntity.badRequest().body("400 Invalid user password");        //throw new InvalidDataException("400 Invalid user password");
        }
            user.setData(LocalDate.now().toString());
            return ResponseEntity.ok(user);
    }

//        @ResponseStatus(HttpStatus.BAD_REQUEST)
//        public static class InvalidDataException extends RuntimeException {
//            public InvalidDataException(String message) {
//                super("400 Invalid user password");
//            }
//        }
}