package TestProvectus;
import java.util.Scanner;

/**
 * Created by dimaz on 01.07.2017.
 */
public class Main {

    static Scanner scanner;
    static Combination maxCombination; //variable that stores the highest combination
    static Card[] deck; //top 5 cards of the deck

    public static void main(String[] args) {
        scanner = new Scanner(System.in); //initializing input scanner
        while (scanner.hasNext()) {
            maxCombination = Combination.highest_card;
            Card[] hand = new Card[5]; //initializing hand and deck
            deck = new Card[5];
            System.out.print("Hand: ");
            for (int i = 0; i < 5; i++) {
                String card = scanner.next();
                System.out.print(card + " ");
                hand[i] = new Card(card.charAt(0), card.charAt(1));
            }
            System.out.print("Deck: ");
            for(int i = 0; i < 5; i++){
                String card = scanner.next();
                System.out.print(card + " ");
                deck[i] = new Card(card.charAt(0), card.charAt(1));
            }

            System.out.print("Best hand: ");

            Hand currentHand = new Hand(hand);
            iteration(currentHand, 0);

            String result = maxCombination.toString().replaceAll("_", "-");
            System.out.println(result);

        }


    }

    private static void iteration(Hand hand, int count) {//recursive function, that do all the job
        if(hand.getHigher().ordinal() < maxCombination.ordinal()) //checking if current hand has higher combination then the highest one
            maxCombination = hand.getHigher();
        for (int i = 0; i < 5 - count; i++) { //replacing one of cards from the "first" hand with the next one from the deck
            iteration(hand.addNewCard(deck[count], i), count + 1);
        }

    }

}
