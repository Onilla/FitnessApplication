package domain;
import java.time.LocalDate;
import java.time.LocalTime;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
public class Fitness {
    private final List<String> membershipGym = new ArrayList<>();
    final int MAX_CLIENT_IN_ZONE = 20;
    private final List<String> membershipSwimPool = new ArrayList<>();
    private final List<String> membershipWorkout = new ArrayList<>();
    private final LocalDate currentDate = LocalDate.now();
    private final LocalTime dailyTicketTime = LocalTime.of(16, 0);
    private final LocalTime currentTime = LocalTime.now();
    private final LocalTime openTime = LocalTime.of(8, 0);
    private final LocalTime closeTime = LocalTime.of(23, 0);
        public void clientRegistration(Membership membership, TrainingZone zone) {
            if (checkTimeVisit()) {
                System.out.println("Время посещения клуба с 08:00 до 22:00. Приходите в другое время");
            } else if (checkDateReg(membership.getEndDate())) {
                System.out.println("Абонемент просрочен");
            } else if (checkTypeTicket(membership)) {
                System.out.println("Доступ для дневного абонемента запрещен после 16:00");
            } else if (checkMaxClient()) {
                System.out.println(zone.getTranslation() + " переполнен");
            } else if (checkClientInZone(membership, zone)) {
                System.out.println("Абонемент зарегистрирован в другой зоне");
            } else if(checkZoneClient(membership,zone)) {
                System.out.println("Ваш абонемент не позволяет пройти в эту зону");
                } else {
                addClientAtList(membership, zone);
                printInfo(membership,zone);
            }
        }
        public void addClientAtList(Membership membership, TrainingZone zone){
            switch (zone){
                case GYM -> membershipGym.add(membership.getOwner().getName() + " "
                        + membership.getOwner().getSurName());
                case SWIM_POOL -> membershipSwimPool.add(membership.getOwner().getName() + " "
                        + membership.getOwner().getSurName());
                case WORKOUT -> membershipWorkout.add(membership.getOwner().getName() + " "
                        + membership.getOwner().getSurName());
            }
        }
        public void printInfo(Membership membership, TrainingZone zone) {
        System.out.println("Добро пожаловать " + "\nКлиент: " + membership.getOwner().getName() + " " + membership.getOwner().getSurName() +
                " " + membership.getOwner().getBirthYear() + " года рождения" +
                "\nТренировочная зона: " + zone.getTranslation() +
                "\nДата и время посещения: " + new Date());
        }
    public boolean checkTypeTicket(Membership membership){
            return (((membership.getTypeTicket()).equals(TypeTicket.DAILY_TICKET)) &&
                    (currentTime.isAfter(dailyTicketTime)));
    }
    public void printListOfMembership() {
        System.out.println("\nВ данный момент занимаются в тренажерном зале:" + membershipGym +
                "\nВ данный момент занимаются в бассене:" + membershipSwimPool +
                "\nВ данный момент занимаются на групповых занятиях:" + membershipWorkout);}
    public boolean checkMaxClient() {
        return ((membershipGym.size() > MAX_CLIENT_IN_ZONE) |
                (membershipSwimPool.size() > MAX_CLIENT_IN_ZONE) |
                (membershipWorkout.size() > MAX_CLIENT_IN_ZONE));
    }
    public boolean checkZoneClient(Membership membership,TrainingZone zone){
        return switch (zone) {
            case GYM -> false;
            case SWIM_POOL -> ((membership.ticket).equals(TypeTicket.DAILY_TICKET));
            case WORKOUT -> ((membership.ticket).equals(TypeTicket.SINGLE_TICKET));
        };
    }
    public boolean checkClientInZone(Membership membership, TrainingZone zone) {
        return switch (zone) {
            case GYM -> ((membershipSwimPool.contains(membership.getOwner().getName() + " " +
                    membership.getOwner().getSurName()))
                    || (membershipWorkout.contains(membership.getOwner().getName() + " " +
                    membership.getOwner().getSurName())));
            case SWIM_POOL -> ((membershipGym.contains(membership.getOwner().getName() + " " +
                    membership.getOwner().getSurName()))
                    || (membershipWorkout.contains(membership.getOwner().getName() + " " +
                    membership.getOwner().getSurName())));
            case WORKOUT -> ((membershipSwimPool.contains(membership.getOwner().getName() + " " +
                    membership.getOwner().getSurName()))
                    || (membershipGym.contains(membership.getOwner().getName() + " " +
                    membership.getOwner().getSurName())));
        };
    }
    public boolean checkTimeVisit() {
        return  (currentTime.isBefore(openTime) || (currentTime.isAfter(closeTime)));
    }
    public boolean checkDateReg(LocalDate endDate) {
        return  (currentDate.isAfter(endDate));
        }
    }