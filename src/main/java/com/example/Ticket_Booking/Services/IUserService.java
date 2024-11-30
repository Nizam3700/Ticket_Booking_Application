package com.example.Ticket_Booking.Services;

import com.example.Ticket_Booking.Dto.Login;
import com.example.Ticket_Booking.Model.User;
import jakarta.validation.Valid;

import java.util.List;
import java.util.Optional;

public interface IUserService {

    User user(@Valid User user);
    List<User> getAllUsers();
    User userId(int id);
    User forgetPassword(String email, String newPassword);
    String deleteUser(int id);
    String verify(Login login);

}
