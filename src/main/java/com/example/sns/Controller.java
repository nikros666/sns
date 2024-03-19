package com.example.sns;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.Map;

@RequestMapping("/api")
@RestController
public class Controller {
    @GetMapping("/get")
    public ResponseEntity<?> getStaticJson(User user) {
        return ResponseEntity.ok("{\"message\":\"Static_Json\"}");
    }

    @PostMapping("/processJson")
    public ResponseEntity<?> processJson(@RequestBody Map<String,String> userReq) {
        try {
            if((userReq.size()!=2)
                    || (!userReq.containsKey("login"))
                    ||(!userReq.containsKey("password"))){
                throw new Exception("BAD Request");
            }
            User user = new User(userReq.get("login"),userReq.get("password"));
            return ResponseEntity.ok(user.toString());

        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }
}
