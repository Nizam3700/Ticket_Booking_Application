package com.example.Ticket_Booking.Controller;

import com.example.Ticket_Booking.Model.User;
import com.example.Ticket_Booking.Repository.UserRepository;
import com.example.Ticket_Booking.Services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v1")
public class UserController {

    @Autowired
    private UserService userService;

//    Post Method for register user, admin, super_admin
    @PostMapping("/user/register")
    public ResponseEntity<User> user(@Valid @RequestBody User user){
//        save the user data into database
        User saveUser = userService.user(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(saveUser);
    }

//    Get Method to fetch AllUsers
    @GetMapping("/admin/userdetails")
    public ResponseEntity<List<User>> getAllUser(){
        List<User> getAll = userService.getAllUsers();
        return ResponseEntity.ok(getAll);
    }

//    Get Method for specific user
    @GetMapping("/admin/userdetails/{id}")
    public ResponseEntity<User> userId(@PathVariable int id){
        Optional<User> userId= Optional.ofNullable(userService.userId(id));
        if(userId.isPresent()){
            return ResponseEntity.ok(userId.get());
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    // Put Method to Update the Password
    //ToDo.......
    @PutMapping("/forget/{email}")
    public ResponseEntity<User> forgetPassword(@RequestParam String email, @RequestParam String newPassword){
        User updatePassword = userService.forgetPassword(email,newPassword);
        return ResponseEntity.ok(updatePassword);
    }

    //Delete Method, To Delete the user Account
    @DeleteMapping("admin/delete-user/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable int id){
        String deleteUserId = userService.deleteUser(id);
        return ResponseEntity.ok(deleteUserId);
    }


}
