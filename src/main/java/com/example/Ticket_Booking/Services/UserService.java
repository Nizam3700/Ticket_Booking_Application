package com.example.Ticket_Booking.Services;

import com.example.Ticket_Booking.Dto.Login;
import com.example.Ticket_Booking.Model.User;
import com.example.Ticket_Booking.Repository.UserRepository;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService implements IUserService{

    @Autowired
    private UserRepository userRepository;

//    Post Method logic
    @Override
    public User user(User user) {
        String password = user.getPassword();
        if(password.length() < 8){
            throw new ValidationException("Password must have atleast 8 characters long");
        }
        user.setPassword(user.getPassword());

        return userRepository.save(user);
    }

//    get method to fetch AllUsers
    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

//    Get Method by id logic
    @Override
    public User userId(int id) {
        return userRepository.findById(id)
                .orElseThrow(() ->new  RuntimeException("User not Found"));
    }

//    put method for forget_Password logic
    @Override
    public User forgetPassword(String email, String newPassword) {
        User existingUser = userRepository.findByEmail(email);
        if(existingUser != null){
            existingUser.setPassword(newPassword);
            return userRepository.save(existingUser);
        }else{
            throw new RuntimeException("Email Not Found");
        }

//        return null;
    }

//    delete method, to delete the User Account
    @Override
    public String deleteUser(int id) {
        if(!userRepository.existsById(id)){
            return "Not found the User with Id :" + id +", Provide the correct Id";
        }
        userRepository.deleteById(id);
        return "User Deleted Successfully of Id :"+ id +" User";
    }

//    post method for login and verify logic
    @Override
    public String verify(Login login) {
//        if (user.getEmail())
        return null;
    }

}
