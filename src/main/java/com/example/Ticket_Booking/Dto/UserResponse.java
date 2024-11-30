package com.example.Ticket_Booking.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserResponse {
    private int id;
    private String first_Name;
    private String last_Name;
    private String email;
}
