package TestProvectus;
/**
 * Created by dimaz on 01.07.2017.
 */
public class Card {

    private int suit;
    private int value;


    public Card(char value, char suit) {

        if (Character.isDigit(value))
            this.value = Character.getNumericValue(value);
        else
            switch (value) {
                case 'T':
                    this.value = 10;
                    break;
                case 'J':
                    this.value = 11;
                    break;
                case 'Q':
                    this.value = 12;
                    break;
                case 'K':
                    this.value = 13;
                    break;
                case 'A':
                    this.value = 14;
                    break;
            }

        switch (suit) {
            case 'H':
                this.suit = 1;
                break;
            case 'D':
                this.suit = 2;
                break;
            case 'S':
                this.suit = 3;
                break;
            case 'C':
                this.suit = 4;
                break;
        }
    }

    public int getSuit() {
        return suit;
    }

    public int getValue() {
        return value;
    }
}
