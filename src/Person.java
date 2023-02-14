import java.util.ArrayList;

public class Person {
    // cards - 2 to start
    // count - +21 = Bust
    // actions - hit, stay
    // gameRule - +21 = lose
    private ArrayList<Card> hand = new ArrayList<>();
    private Card card1;
    private Card card2;

    public ArrayList<Card> getHand() {
        return hand;
    }

    public void setHand(ArrayList<Card> hand) {
        this.hand = hand;
    }

    public Card getCard1() {
        return card1;
    }

    public void setCard1(Card card1) {
        this.card1 = card1;
    }

    public Card getCard2() {
        return card2;
    }

    public void setCard2(Card card2) {
        this.card2 = card2;
    }

}
