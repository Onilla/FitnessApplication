
import domain.*;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {

        Client client = new Client("Ivan","Ivanov",1990);
        Client client2 = new Client("Petr","Petrov",1992);
        Membership membershipIvan = new Membership(LocalDate.now(),
                LocalDate.of(2025,02,06), client, TypeTicket.DAILY_TICKET);
        Membership membershipPetr = new Membership(LocalDate.now(),
                LocalDate.of(2025,02,06), client2, TypeTicket.SINGLE_TICKET);
        Fitness fitness = new Fitness();
        fitness.clientRegistration(membershipIvan, TrainingZone.SWIM_POOL);
        fitness.clientRegistration(membershipPetr, TrainingZone.WORKOUT);
        fitness.ListMembershipGYM();
        fitness.ListMembershipSwimPool();
        fitness.ListMembershipWorkout();

    }
}

