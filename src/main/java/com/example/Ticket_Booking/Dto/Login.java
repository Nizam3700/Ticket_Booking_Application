package com.example.Ticket_Booking.Dto;


public class Login {
//    Attributes
    private String email;
    private String password;

//    Constructor
    public Login(String email, String password){
        this.email = email;
        this.password = password;
    }

//    getter & setter

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
