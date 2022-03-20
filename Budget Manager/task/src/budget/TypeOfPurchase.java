package budget;

public enum TypeOfPurchase {
    FOOD, CLOTHES, ENTERTAINMENT, OTHER, ALL;

    public String toString() {
        switch (this) {
            case FOOD:
                return "Food";
            case CLOTHES:
                return "Clothes";
            case ENTERTAINMENT:
                return "Entertainment";
            case OTHER:
                return "Other";
            case ALL:
                return "All";
        }
        return "";
    }
}
