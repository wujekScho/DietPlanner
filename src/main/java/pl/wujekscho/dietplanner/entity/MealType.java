package pl.wujekscho.dietplanner.entity;

public enum MealType {
    ŚNIADANIE("Śniadanie"),
    DRUGIE_ŚNIADANIE("Drugie śniadanie"),
    OBIAD("Obiad"),
    PODWIECZOREK("Podwieczorek"),
    KOLACJA("Kolacja");
    private final String type;

    MealType(final String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
