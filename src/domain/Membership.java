package domain;

import java.time.LocalDate;
import java.util.Date;
import java.util.Locale;

public class Membership {
    private final LocalDate registrationDate;
    private final LocalDate endDate;
    private final Client owner;
    TypeTicket ticket = TypeTicket.SINGLE_TICKET;
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
    public Client getOwner(){
        return owner;
    }
}

