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

    private final LocalDate currentDate = LocalDate.now();
    private final LocalTime currentTime = LocalTime.now();
    private final LocalTime openTime = LocalTime.of(8, 0);
    private final LocalTime closeTime = LocalTime.of(22, 0);
    private final LocalTime dailyTicketTime = LocalTime.of(16, 0);
        public void clientRegistration(Membership membership, TrainingZone zone) {
            if (currentTime.isBefore(openTime) || (currentTime.isAfter(closeTime))) {
                System.out.println("Время посещения клуба с 08:00 до 22:00. Приходите в другое время");
            } else if (currentDate.isAfter(membership.getEndDate())) {
                System.out.println("Абонемент просрочен");
            } else if (((membership.ticket).equals(TypeTicket.DAILY_TICKET)) && (currentTime.isAfter(dailyTicketTime))) {
                System.out.println("Доступ для дневного абонемента запрещен после 16:00");
            } else if ((membershipGym.size() > 20) | (membershipSwimPool.size() > 20) | (membershipSwimPool.size() > 20)) {
                System.out.println(zone + " переполнен");
                } else  {
                switch (zone) {
                    case GYM:
                        if ((membershipSwimPool.contains(membership.getOwner().getName()))
                            || (membershipWorkout.contains(membership.getOwner().getName()))) {
                        System.out.println("Абонемент зарегистрирован в другой зоне");
                        } else {
                            System.out.println("Добро пожаловать в " + zone.getTranslation());
                            membershipGym.add(membership.getOwner().getName() + " " + membership.getOwner().getSurName());
                        } break;
                    case SWIM_POOL:
                        if ((membership.ticket).equals(TypeTicket.DAILY_TICKET)) {
                            System.out.println("Ваш абонемент не позволяет пройти в эту зону");
                        } else if ((membershipGym.contains(membership.getOwner().getName()))
                                    || (membershipWorkout.contains(membership.getOwner().getName()))) {
                                System.out.println("Абонемент зарегистрирован в другой зоне");
                            } else {
                            System.out.println("Добро пожаловать в " + zone.getTranslation());
                            membershipSwimPool.add(membership.getOwner().getName() + " " + membership.getOwner().getSurName());
                        }
                        break;
                    case WORKOUT:
                        if ((membership.ticket).equals(TypeTicket.SINGLE_TICKET)) {
                            System.out.println("Ваш абонемент не позволяет пройти в эту зону");
                        } else if ((membershipSwimPool.contains(membership.getOwner().getName()))
                            || (membershipGym.contains(membership.getOwner().getName()))) {
                        System.out.println("Абонемент зарегистрирован в другой зоне");
                        } else {
                            System.out.println("Добро пожаловать в " + zone.getTranslation());
                            membershipWorkout.add(membership.getOwner().getName() + " " + membership.getOwner().getSurName());
                        }
                        break;
                }
            }

            System.out.println("Клиент: " + membership.getOwner().getName() + " " + membership.getOwner().getSurName() +
                    " " + membership.getOwner().getBirthYear() + " года рождения");
            System.out.println("Тренировочная зона: " + zone.getTranslation());
            System.out.println("Дата и время посещения: " + new Date());


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