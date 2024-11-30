package com.example.Ticket_Booking.Services;

import com.example.Ticket_Booking.Enum.Status;
import com.example.Ticket_Booking.Model.Ticket;
import com.example.Ticket_Booking.Model.Train;
import com.example.Ticket_Booking.Model.User;
import com.example.Ticket_Booking.Repository.TicketRepo;
import com.example.Ticket_Booking.Repository.TrainRespository;
import com.example.Ticket_Booking.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TicketService implements ITicketService {
    @Autowired
    private TicketRepo ticketRepo;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TrainRespository trainRespository;
    @Override
    public Ticket ticket(Ticket ticket, int userId, int trainId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        Train train = trainRespository.findByTrainNumber(trainId)
                .orElseThrow(() ->new RuntimeException("Train not found"));


        if(train.getStatus().equals(Status.Cancelled)){
            throw new RuntimeException("Train is cancelled, cannot book passenger. Please select another train");
        }

        ticket.setUser(user);
        ticket.setTrain(train);

        return ticketRepo.save(ticket);
    }

    @Override
    public List<Ticket> ticketList() {
        return ticketRepo.findAll();
    }

    @Override
    public Ticket ticket_id(int ticketId) {

        return ticketRepo.findById(ticketId)
                .orElseThrow(() -> new RuntimeException("Passanger Not Found"));
    }

    @Override
    public Optional<Ticket> ticket_status(int ticket_id) {
        Optional<Ticket> pass = ticketRepo.findById(ticket_id);

        if(pass.isPresent()) {
            Ticket ticket = pass.get();

            if (ticket.getStatus().equals(Status.waitingList)) {
                ticket.setStatus(Status.Confirmed);
                ticketRepo.save(ticket);

                return Optional.of(ticket);
            } else {
                throw new RuntimeException("Passenger ticket status is Not in waitingList");
            }
        }else{
                throw new RuntimeException("Passenger not found");
        }



    }


}
