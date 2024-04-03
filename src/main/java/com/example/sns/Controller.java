package com.example.sns;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.sql.SQLException;
import java.util.Map;

@RequestMapping("/api")
@RestController
public class Controller {

    @GetMapping("/get/{login}")
    public ResponseEntity<?> getStaticJson(@PathVariable String login) {
        DbManager dbmanager = new DbManager();
        User user = dbmanager.select(login);
        return ResponseEntity.ok(user);
    }

    @PostMapping("/processJson")
    public ResponseEntity<?> processJson(@RequestBody Map<String,String> userReq) {
        try {
          DbManager dbManager = new DbManager();
          User user = new User(userReq.get("login"), userReq.get("password"),userReq.get("dat"), userReq.get("email"));
          int rowsUpdated = dbManager.insert(user);
          if ((rowsUpdated == 0)
                  ||(userReq.size()!=4)
                  ||(!userReq.containsKey("login"))
                  || (!userReq.containsKey("password"))
                  || (!userReq.containsKey("dat"))
                  || (!userReq.containsKey("email"))){
              throw new Exception("Failed to insert user");
          }
          return ResponseEntity.ok("User inserted successfully: " + user);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }
}
