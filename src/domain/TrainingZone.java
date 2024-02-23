package domain;

public enum TrainingZone {
    GYM("Тренажерный зал"),
    SWIM_POOL("Бассейн"),
    WORKOUT("Зал групповых занятий");
    private final String translation;

    TrainingZone(String translation) {
        this.translation = translation;
    }

    public String getTranslation() {
        return translation;
    }
}
