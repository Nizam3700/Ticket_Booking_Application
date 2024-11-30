package com.example.Ticket_Booking.Repository;

import com.example.Ticket_Booking.Model.Train;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@Repository
public interface TrainRespository extends JpaRepository<Train,Integer> {
     Optional<Train>  findByTrainNumber(int trainNumber);
     List<Train> findBySourceAndDestination(String source, String destination);


}
