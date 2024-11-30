package com.example.Ticket_Booking.Dto;

import com.example.Ticket_Booking.Enum.Status;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class TicketResponse {
    private int ticketId;
    private String fullName;
    private int age;

    private Status status;
    private Date Created_At;
    private UserResponse user;

}