package ch.bfh.swos.camp.model;

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

    public static AttributeBoundaries getAttrBoundariesByHeroType(HeroType heroType) {

        switch (heroType) {
            case WARRIOR:
                return new AttributeBoundaries(15, 20, 13, 18, 150, 200, 1, 5, 0.1, 0.2, 0.1, 0.2);
            case ROGUE:
                return new AttributeBoundaries(25, 35, 0, 5, 50, 80, 7, 12, 0.3, 0.4, 0.5, 0.7);
            case MAGE:
                return new AttributeBoundaries(10, 20, 5, 10, 80, 120, 1, 20, 0.3, 0.7, 0.1, 0.3);
            case ARCHER:
                return new AttributeBoundaries(35, 40, 3, 8, 30, 50, 5, 15, 0, 0.2, 0.3, 0.5);
        }
        return null;
    }


}
