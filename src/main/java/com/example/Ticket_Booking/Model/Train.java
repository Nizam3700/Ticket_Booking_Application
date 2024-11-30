package com.example.Ticket_Booking.Model;

import com.example.Ticket_Booking.Enum.Status;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Train {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int train_id;

    private String train_Name;
    @NonNull
    private int trainNumber;

    private String source;
    private String destination;
    private double departure_time;
    private double arrival_time;
    @NonNull
    private Status status = Status.Opeational;
    @UpdateTimestamp
    private Date Updated_time;

    //One Train -> Many Tickets
    @OneToMany(mappedBy = "train", cascade = CascadeType.ALL)
    @JsonBackReference
    private List<Ticket> tickets;

}
