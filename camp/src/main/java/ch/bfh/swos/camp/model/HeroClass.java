package ch.bfh.swos.camp.model;

public enum HeroClass {
    WARRIOR,
    ROGUE,
    MAGE,
    ARCHER;

    public static HeroClass intToHeroClass(int i) {
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
