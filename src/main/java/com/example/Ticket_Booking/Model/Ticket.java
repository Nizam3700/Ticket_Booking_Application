package com.example.Ticket_Booking.Model;

import com.example.Ticket_Booking.Enum.Status;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int ticket_id;

    private String fullName;

    private int age;

    private Status status = Status.waitingList;
    @CreationTimestamp
    private Date Created_At;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @JsonBackReference
    private User user;

    @ManyToOne
    @JoinColumn(name = "train_id", nullable= false)
//    @JsonBackReference
    private Train train;

}
