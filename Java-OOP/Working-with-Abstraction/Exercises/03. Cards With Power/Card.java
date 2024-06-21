public class Card {
    private String rankName;
    private String suitName;
    private CardRank cardRank;
    private CardSuit cardSuit;

    public Card(String cardRank, String cardSuit) {
        this.rankName = cardRank;
        this.suitName = cardSuit;
        this.cardRank = CardRank.valueOf(cardRank);
        this.cardSuit = CardSuit.valueOf(cardSuit);
    }

    public CardRank getCardRank() {
        return cardRank;
    }

    public CardSuit getCardSuit() {
        return cardSuit;
    }

    public int calculateCardPower() {
        return this.cardRank.getRankPower() + this.cardSuit.getSuitPowers();
    }
}
