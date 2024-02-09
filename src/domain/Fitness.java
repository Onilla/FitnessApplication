package domain;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Fitness {
    private final List<String> membershipGym = new ArrayList<>();
    private final List<String> membershipSwimPool = new ArrayList<>();
    private final List<String> membershipWorkout = new ArrayList<>();

    public void clientRegistration(Membership membership, TrainingZone zone) {
        LocalDate currentDate = LocalDate.now();
        LocalTime currentTime = LocalTime.now();
        LocalTime openTime = LocalTime.of(8, 0);
        LocalTime closeTime = LocalTime.of(22, 0);
        LocalTime dailyTicketTime = LocalTime.of(8, 16);
        if (currentTime.isBefore(openTime) || (currentTime.isAfter(closeTime))) {
            System.out.println("Время посещения клуба с 08:00 до 22:00. Приходите в другое время");
        } else if (((membership.ticket).equals(TypeTicket.DAILY_TICKET)) && (currentTime.isAfter(dailyTicketTime))) {
            System.out.println("Доступ для дневного абонемента запрещен после 16:00");

        } else{
            switch (zone) {
                case GYM:
                    if (currentDate.isAfter(membership.getEndDate())) {
                        System.out.println("Абонемент просрочен");
                        if (membershipGym.size() > 20) {
                            System.out.println("Тренажерный зал переполнен");
                            if ((membershipSwimPool.contains(membership.getOwner().getName()))
                                    || (membershipWorkout.contains(membership.getOwner().getName()))) {
                                System.out.println("Абонемент зарегистрирован в другой зоне");
                            }
                        }
                    } else {
                        System.out.println("Добро пожаловать в тренажерный зал");
                        membershipGym.add(membership.getOwner().getName() + " " + membership.getOwner().getSurName());
                    }
                    break;
                case SWIM_POOL:
                    if (currentDate.isAfter(membership.getEndDate())) {
                        System.out.println("Абонемент просрочен");
                    } else if ((membership.ticket).equals(TypeTicket.DAILY_TICKET)) {
                        System.out.println("Ваш абонемент не позволяет пройти в эту зону");
                        if (membershipSwimPool.size() > 20) {
                            System.out.println("Бассейн переполнен");
                            if ((membershipGym.contains(membership.getOwner().getName()))
                                    || (membershipWorkout.contains(membership.getOwner().getName()))) {
                                System.out.println("Абонемент зарегистрирован в другой зоне");
                            }
                        }
                    } else {
                        System.out.println("Добро пожаловать в бассейн");
                        membershipSwimPool.add(membership.getOwner().getName() + " " + membership.getOwner().getSurName());
                    }
                    break;
                case WORKOUT:
                    if (currentDate.isAfter(membership.getEndDate())) {
                        System.out.println("Абонемент просрочен");
                        if ((membership.ticket).equals(TypeTicket.SINGLE_TICKET)) {
                            System.out.println("Ваш абонемент не позволяет пройти в эту зону");
                            if (membershipSwimPool.size() > 20) {
                                System.out.println("Зал групповых занятий переполнен");
                                if ((membershipSwimPool.contains(membership.getOwner().getName()))
                                        || (membershipGym.contains(membership.getOwner().getName()))) {
                                    System.out.println("Абонемент зарегистрирован в другой зоне");
                                }
                            }
                        }
                    } else {
                        System.out.println("Добро пожаловать на групповые занятия");
                        membershipWorkout.add(membership.getOwner().getName() + " " + membership.getOwner().getSurName());
                    }
                    break;
            }
            System.out.println("Клиент: " + membership.getOwner().getName() + " " + membership.getOwner().getSurName() +
                    " " + membership.getOwner().getBirthYear() + " года рождения");
            System.out.println("Тренировочная зона: " + zone);
            System.out.println("Дата и время посещения: " + new Date());
        }
    }

    public void ListMembershipGYM() {
        System.out.println("В данный момент занимаются в тренажерном зале:" + membershipGym);
    }

    public void ListMembershipSwimPool() {
        System.out.println("В данный момент занимаются в бассене:" + membershipSwimPool);
    }

    public void ListMembershipWorkout() {
        System.out.println("В данный момент занимаются на групповых занятиях:" + membershipWorkout);
    }
}
