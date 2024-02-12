import domain.*;
import java.time.LocalDate;
public class Main {
    public static void main(String[] args) {
        Client client = new Client("Ivan","Ivanov",1990);
        Membership membershipIvan = new Membership(LocalDate.now(),
                LocalDate.of(2025,2,6), client, TypeTicket.DAILY_TICKET);
                Client client2 = new Client("Petr","Petrov",1992);
          Membership membershipPetr = new Membership(LocalDate.now(),
                LocalDate.of(2025,2,6), client2, TypeTicket.SINGLE_TICKET);
          Fitness fitness = new Fitness();
          fitness.clientRegistration(membershipIvan, TrainingZone.GYM);
          fitness.clientRegistration(membershipPetr, TrainingZone.WORKOUT);
          fitness.ListMembershipGYM();
          fitness.ListMembershipSwimPool();
          fitness.ListMembershipWorkout();

    }
}

