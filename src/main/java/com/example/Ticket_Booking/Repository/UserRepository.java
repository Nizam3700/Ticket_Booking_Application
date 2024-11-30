package com.example.Ticket_Booking.Repository;

import com.example.Ticket_Booking.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
    Optional<User> findById(int id);
    public User findByEmail(String email);

    Optional<User> deleteById(int id);
}
