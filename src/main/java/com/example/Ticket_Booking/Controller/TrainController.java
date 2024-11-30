package com.example.Ticket_Booking.Controller;

import com.example.Ticket_Booking.Dto.TrainResponse;
import com.example.Ticket_Booking.Model.Train;
import com.example.Ticket_Booking.Services.TrainService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1")
public class TrainController {

    @Autowired
    private TrainService trainService;

//    Post Method for scheduleTrain
    @PostMapping("/super-admin/train-schedule")
    public ResponseEntity<Train> scheduleTrain(@Valid @RequestBody Train train){
        Train schedule_train = trainService.train(train);
        return ResponseEntity.status(HttpStatus.CREATED).body(schedule_train);
    }

//    Get Method for fetch AllTrains specific location
    @GetMapping("/train")
    public ResponseEntity<?> AllTrain(@RequestParam(required = false) String source,
                                      @RequestParam(required = false) String destination){
        List<TrainResponse> trains = trainService.getAllTrain(source, destination);

        if(trains.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No trains found for your jourany");
        }
        return ResponseEntity.ok(trains);
    }

//    Get Method to Search specific Train
    @GetMapping("/super-admin/train-number/{trainNumber}")
    public ResponseEntity<TrainResponse> getTrain(@PathVariable int trainNumber){
        TrainResponse gettrain = trainService.trainId(trainNumber);
        return ResponseEntity.ok(gettrain);
    }

//    put method for cancel the train
    @PutMapping("/super-admin/cancel-train/{trainNumber}")
    public ResponseEntity<Train> cancelTrain(@PathVariable int trainNumber){
        Train trainCancel = trainService.trainStatus(trainNumber);
        return ResponseEntity.ok(trainCancel);
    }


//    Delete Method to delete Train
    @DeleteMapping("/super-admin/delete-train/{train_id}")
    public ResponseEntity<String> deleteTrain(@PathVariable int train_id){
        String deletedtrain = trainService.deleteTrain(train_id);
        return ResponseEntity.ok(deletedtrain);
    }
}
