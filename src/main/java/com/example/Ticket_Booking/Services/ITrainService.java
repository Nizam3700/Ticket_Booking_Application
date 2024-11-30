package com.example.Ticket_Booking.Services;

import com.example.Ticket_Booking.Dto.TrainResponse;
import com.example.Ticket_Booking.Model.Train;
import jakarta.validation.Valid;

import java.util.List;
import java.util.Optional;

public interface ITrainService {
    Train train(@Valid Train train);
    List<TrainResponse> getAllTrain(String source, String destination);

    //    Get Method to fetch the allTrain
//    List<TrainResponse> getAllTrain();

    TrainResponse trainId(int trainNumber);
    Train trainStatus(int trainNumber);
    String deleteTrain(int train_id);
}
