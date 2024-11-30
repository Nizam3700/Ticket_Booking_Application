package com.example.Ticket_Booking.Controller;

import com.example.Ticket_Booking.Model.Ticket;
import com.example.Ticket_Booking.Services.TicketService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v1/ticket")
public class TicketController {
    @Autowired
    private TicketService ticketService;

//    post method for Booking Train ticket
    @PostMapping("/booking/{userId}/{trainNumber}")
    public ResponseEntity<Ticket> addpass(@Valid @RequestBody Ticket ticket,
                                          @PathVariable Integer userId,
                                          @PathVariable Integer trainNumber){
        Ticket getPass = ticketService.ticket(ticket,userId, trainNumber);
        return ResponseEntity.ok(getPass);
    }


    @GetMapping("ticket-list")
    public ResponseEntity<List<Ticket>> ticketList(){
        List<Ticket> ticketList = ticketService.ticketList();
        return ResponseEntity.ok(ticketList);
    }

    @GetMapping("ticket-id/{id}")
    public ResponseEntity<Ticket> passById(@PathVariable int id){
        Ticket passId = ticketService.ticket_id(id);
        return ResponseEntity.ok(passId);
    }

    @PutMapping("/waiting-list/{id}")
    public ResponseEntity<Optional<Ticket>> passStatus(@PathVariable int id){
        Optional<Ticket> passStatus = ticketService.ticket_status(id);
        return ResponseEntity.ok(passStatus);
    }



}
