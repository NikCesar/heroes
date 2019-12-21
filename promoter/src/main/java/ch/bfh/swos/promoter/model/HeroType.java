package ch.bfh.swos.promoter.model;

public enum HeroType {
    WARRIOR,
    ROGUE,
    MAGE,
    ARCHER;

    public static HeroType convertIntToHeroType(int i) {
        switch (i) {
            case 0:
                return WARRIOR;
            case 1:
                return ROGUE;
            case 2:
                return MAGE;
            case 3:
                return ARCHER;
            default:
                return WARRIOR;
        }
    }
}
