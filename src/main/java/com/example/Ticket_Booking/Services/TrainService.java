package com.example.Ticket_Booking.Services;

import com.example.Ticket_Booking.Dto.TicketResponse;
import com.example.Ticket_Booking.Dto.TrainResponse;
import com.example.Ticket_Booking.Dto.UserResponse;
import com.example.Ticket_Booking.Enum.Status;
import com.example.Ticket_Booking.Model.Ticket;
import com.example.Ticket_Booking.Model.Train;
import com.example.Ticket_Booking.Repository.TicketRepo;
import com.example.Ticket_Booking.Repository.TrainRespository;
import com.example.Ticket_Booking.Repository.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TrainService implements ITrainService{

    @Autowired
    private TrainRespository trainRespository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TicketRepo ticketRepo;

//    Post Method to Schedule the Train;
    @Override
    public Train train(@Valid Train train) {

        return trainRespository.save(train);
    }

    //    Get Method to fetch the getAllTrain
    @Override
    public List<TrainResponse> getAllTrain( String source,  String destination) {
        List<Train> trains;

        if(source != null && destination != null){
            trains = trainRespository.findBySourceAndDestination(source, destination);
        }else{
            trains = trainRespository.findAll();
        }

        return trains.stream()
                .map(train -> new TrainResponse(
                        train.getTrain_id(),
                        train.getTrain_Name(),
                        train.getTrainNumber(),
                        train.getSource(),
                        train.getDestination(),
                        train.getDeparture_time(),
                        train.getArrival_time(),
                        train.getStatus(),
                        train.getUpdated_time(),
                        null
                ))
                .collect(Collectors.toList());

    }


    //    get Method by specific train
    @Override
    public TrainResponse trainId(int trainNumber) {
        Train train = trainRespository.findByTrainNumber(trainNumber)
                .orElseThrow(() -> new RuntimeException("Train not Found"));

        List<TicketResponse> ticket = train.getTickets().stream()
                .map(tickets -> {
                    return new TicketResponse(
                            tickets.getTicket_id(),
                            tickets.getFullName(),
                            tickets.getAge(),
                            tickets.getStatus(),
                            tickets.getCreated_At(),
                            new UserResponse(
                                    tickets.getUser().getId(),
                                    tickets.getUser().getFirst_Name(),
                                    tickets.getUser().getLast_Name(),
                                    tickets.getUser().getEmail()
                            )

                    );
                }).collect(Collectors.toList());
        return new TrainResponse(
                train.getTrain_id(),
                train.getTrain_Name(),
                train.getTrainNumber(),
                train.getSource(),
                train.getDestination(),
                train.getDeparture_time(),
                train.getArrival_time(),
                train.getStatus(),
                train.getUpdated_time(),
                ticket
        );
    }

//    put method to update the Status of Train
    @Override
    public Train trainStatus(int trainNumber) {
        Train train = trainRespository.findByTrainNumber(trainNumber)
                .orElseThrow(()-> new RuntimeException("Train not Found"));

        if(train.getStatus().equals(Status.Opeational)){
            train.setStatus(Status.Cancelled);

            List<Ticket> tickets = train.getTickets();
            for(Ticket pass: tickets) {
                if(pass.getStatus().equals(Status.waitingList) || pass.getStatus().equals(Status.Confirmed)){
                    pass.setStatus(Status.Cancelled);
                }
                ticketRepo.save(pass);
            }
        }else{
            throw new RuntimeException("Train is not Opertional");
        }

        return trainRespository.save(train);

    }

//    Delete the train
    @Override
    public String deleteTrain(int id){
        if(!trainRespository.existsById(id)){
            return "Not found the Train with Number :" + id +", Provide the correct Id";
        }
        trainRespository.deleteById(id);
        return "Train Deleted Successfully of Number :"+ id +" Train";

    }
}
