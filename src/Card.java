import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public class Card {
    public int value;
    private String faceName, suit;

    public Card(String faceName, String suit) throws IllegalAccessException {
        setFaceName(faceName);
        setSuit(suit);
        setValue(faceName);
    }

    public Integer getValue() {return value;}

    private void setValue(String faceName) {
        if (faceName.equals("ace")) {
            value = 11;
        } else if (faceName.equals("king") || faceName.equals("queen") || faceName.equals("jack")) {
            value = 10;
        } else {
            value = Integer.parseInt(faceName);
        }
    }

    public String getFaceName() {
        return faceName;
    }

    /**
     * @param faceName 2,3,4,5,6,7,8,9,10,j,q,k
     *
     * */
    public void setFaceName(String faceName) throws IllegalAccessException {
        List<String> validFaceNames = getValidFaceName();
        faceName = faceName.toLowerCase();

        if (validFaceNames.contains(faceName))
            this.faceName = faceName;
        else
            throw new IllegalAccessException("Valid face names are: " + validFaceNames);
    }

    public static List<String> getValidFaceName() {
        return Arrays.asList("2","3","4","5","6","7","8","9","10","jack","queen","king","ace");
    }

    public String getSuit() {
        return suit;
    }

    public static List<String> getValidSuits(){
        return Arrays.asList("spades", "clubs", "hearts", "diamonds");
    }

    public void setSuit(String suit) throws IllegalAccessException {
        List<String> validSuitNames = getValidSuits();
        suit = suit.toLowerCase();
        if (validSuitNames.contains(suit))
            this.suit = suit;
        else
            throw new IllegalAccessException("Valid suit names are: " + validSuitNames);
    }
    public String toString() {
        return String.format("%s of %s", faceName, suit);
    }


}
