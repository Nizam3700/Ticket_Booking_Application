package com.example.Ticket_Booking.Dto;

import com.example.Ticket_Booking.Enum.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class TrainResponse {
    private int trainId;
    private String trainName;
    private int trainNumber;
    private String source;
    private String destination;
    private double departure_time;
    private double arrival_time;
    private Status status = Status.Opeational;
    private Date Updated_time;

    private List<TicketResponse> passangers;
}
