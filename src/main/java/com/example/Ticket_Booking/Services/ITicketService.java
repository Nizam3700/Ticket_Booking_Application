package com.example.Ticket_Booking.Services;

import com.example.Ticket_Booking.Model.Ticket;
import jakarta.validation.Valid;


import java.util.List;
import java.util.Optional;

public interface ITicketService {
    Ticket ticket(@Valid Ticket ticket, int userId, int trainNumber);
    List<Ticket> ticketList();

    Ticket ticket_id(int ticketId);

    Optional<Ticket> ticket_status(int ticket_id);
}
