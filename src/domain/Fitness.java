package domain;

import java.time.LocalDate;

import java.time.LocalTime;

import java.util.ArrayList;

import java.util.Date;

import java.util.List;

public class Fitness {
    final int MAX_CLIENT_IN_ZONE = 20;
    private final List<String> membershipGym = new ArrayList<>();
    private final List<String> membershipSwimPool = new ArrayList<>();
    private final List<String> membershipWorkout = new ArrayList<>();
    private final LocalDate currentDate = LocalDate.now();
    private final LocalTime dailyTicketTime = LocalTime.of(16, 0);
    private final LocalTime currentTime = LocalTime.now();
    private final LocalTime openTime = LocalTime.of(8, 0);
    private final LocalTime closeTime = LocalTime.of(22, 0);

    public void clientRegistration(Membership membership, TrainingZone zone) {
        if ((checkTimeVisit()) || (checkDateReg(membership.getEndDate())) || (checkTypeTicket(membership)) ||
                (checkMaxClient(zone)) || (checkClientInZone(membership, zone)) || (checkZoneClient(membership, zone))) {
            System.out.println("Мы всегда рады видеть вас снова");
        } else {
            addClientAtList(membership, zone);
            printInfo(membership, zone);
        }
    }

    public boolean checkTimeVisit() {
        if (currentTime.isBefore(openTime) || (currentTime.isAfter(closeTime))) {
            System.out.println("Время посещения клуба с 08:00 до 22:00. Приходите в другое время");
            return true;
        }
        return false;
    }

    public boolean checkDateReg(LocalDate endDate) {
        if (currentDate.isAfter(endDate)) {
            System.out.println("Абонемент просрочен");
            return true;
        }
        return false;
    }

    public boolean checkTypeTicket(Membership membership) {
        if (((membership.getTypeTicket()).equals(TypeTicket.DAILY_TICKET)) &&
                (currentTime.isAfter(dailyTicketTime))) {
            System.out.println("Доступ для дневного абонемента запрещен после 16:00");
            return true;
        }
        return false;
    }

    public boolean checkMaxClient(TrainingZone zone) {
        if ((membershipGym.size() > MAX_CLIENT_IN_ZONE) |
                (membershipSwimPool.size() > MAX_CLIENT_IN_ZONE) |
                (membershipWorkout.size() > MAX_CLIENT_IN_ZONE)) {
            System.out.println(zone.getTranslation() + " переполнен");
            return true;
        }
        return false;
    }

    public boolean checkClientInZone(Membership membership, TrainingZone zone) {
        switch (zone) {
            case GYM -> {
                if ((membershipSwimPool.contains(membership.getOwner().getName() + " " +
                        membership.getOwner().getSurName()))
                        || (membershipWorkout.contains(membership.getOwner().getName() + " " +
                        membership.getOwner().getSurName()))) {
                    System.out.println("Абонемент зарегистрирован в другой зоне");
                    return true;
                }
            }
            case SWIM_POOL -> {
                if ((membershipGym.contains(membership.getOwner().getName() + " " +
                        membership.getOwner().getSurName()))
                        || (membershipWorkout.contains(membership.getOwner().getName() + " " +
                        membership.getOwner().getSurName()))) {
                    System.out.println("Абонемент зарегистрирован в другой зоне");
                    return true;
                }
            }
            case WORKOUT -> {
                if ((membershipSwimPool.contains(membership.getOwner().getName() + " " +
                        membership.getOwner().getSurName()))
                        || (membershipGym.contains(membership.getOwner().getName() + " " +
                        membership.getOwner().getSurName()))) {
                    System.out.println("Абонемент зарегистрирован в другой зоне");
                    return true;
                }
            }
        }
        return false;
    }


    public boolean checkZoneClient(Membership membership, TrainingZone zone) {
        switch (zone) {
            case GYM -> {
                return false;
            }
            case SWIM_POOL -> {
                if ((membership.ticket).equals(TypeTicket.DAILY_TICKET)) {
                    System.out.println("Ваш абонемент не позволяет пройти в эту зону");
                    return true;
                }
            }
            case WORKOUT -> {
                if ((membership.ticket).equals(TypeTicket.SINGLE_TICKET)) {
                    System.out.println("Ваш абонемент не позволяет пройти в эту зону");
                    return true;
                }
            }
        }
        return false;
    }

    public void addClientAtList(Membership membership, TrainingZone zone) {
        switch (zone) {
            case GYM -> membershipGym.add(membership.getOwner().getName() +
                    " " + membership.getOwner().getSurName());
            case SWIM_POOL -> membershipSwimPool.add(membership.getOwner().getName() +
                    " " + membership.getOwner().getSurName());
            case WORKOUT -> membershipWorkout.add(membership.getOwner().getName() +
                    " " + membership.getOwner().getSurName());
        }
    }

    public void printInfo(Membership membership, TrainingZone zone) {
        System.out.println("Добро пожаловать " + "\nКлиент: " + membership.getOwner().getName() +
                " " + membership.getOwner().getSurName() +
                " " + membership.getOwner().getBirthYear() + " года рождения" +
                "\nТренировочная зона: " + zone.getTranslation() +
                "\nДата и время посещения: " + new Date());
        goHome();
    }

    public void printListOfMembership() {
        System.out.println("В данный момент занимаются в тренажерном зале:" + membershipGym +
                "\nВ данный момент занимаются в бассене:" + membershipSwimPool +
                "\nВ данный момент занимаются на групповых занятиях:" + membershipWorkout);
    }


    public void goHome() {
        if (LocalTime.now().isAfter(closeTime)) {
            membershipGym.clear();
            membershipSwimPool.clear();
            membershipWorkout.clear();
        }
    }
}