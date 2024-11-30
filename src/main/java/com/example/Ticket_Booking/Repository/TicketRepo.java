package com.example.Ticket_Booking.Repository;

import com.example.Ticket_Booking.Model.Ticket;
import com.example.Ticket_Booking.Model.Train;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TicketRepo extends JpaRepository<Ticket,Integer> {
}
