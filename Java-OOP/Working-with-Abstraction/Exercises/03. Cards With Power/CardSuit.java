public enum CardSuit {
    CLUBS(0),
    DIAMONDS(13),
    HEARTS(26),
    SPADES(39);

private int suitPowers;

    public int getSuitPowers() {
        return suitPowers;
    }

    CardSuit(int suitPowers) {
        this.suitPowers = suitPowers;
    }
}
