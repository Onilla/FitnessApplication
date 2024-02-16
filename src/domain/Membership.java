package domain;

import java.time.LocalDate;


public class Membership {
    private final LocalDate registrationDate;
    private final LocalDate endDate;
    private final Client owner;
    TypeTicket ticket;

    public Membership(LocalDate registrationDate, LocalDate endDate, Client owner, TypeTicket ticket){
        this.registrationDate = registrationDate;
        this.endDate = endDate;
        this.owner = owner;
        this.ticket = ticket;
    }
    public LocalDate getRegistrationDate(){
        return registrationDate;
    }
    public LocalDate getEndDate(){
        return endDate;
    }
    public TypeTicket getTypeTicket() {
        return ticket;
    }
    public Client getOwner(){
        return owner;
    }
}

