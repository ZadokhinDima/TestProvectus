import java.util.Arrays;
import java.util.HashMap;

/**
 * Created by dimaz on 01.07.2017.
 */
public class Hand{

    private Card[] cards;

    private Combination higher;

    HashMap<Integer, Integer> Suits;
    HashMap<Integer, Integer> Values;

    public Hand(Card[] cards){
        this.cards = cards.clone();//Copying cards to the hand
        Values = new HashMap<>();
        Suits = new HashMap<>();
        for(Card c : cards){  //Counting same suits and values in the hand
            if(Values.containsKey(c.getValue())){
                int currentAmount = Values.get(c.getValue());
                Values.put(c.getValue(), currentAmount + 1);
            }
            else
                Values.put(c.getValue(), 1);

            if(Suits.containsKey(c.getSuit())){
                int currentAmount = Suits.get(c.getSuit());
                Suits.put(c.getSuit(), currentAmount + 1);
            }
            else
                Suits.put(c.getSuit(), 1);
        }
        chechCombination();
    }

    private void chechCombination(){ //Method, that fills "higher" variable with the highest combination in hand

        higher = Combination.highest_card;

        if(Values.size() == 4)
            higher = Combination.one_pair;

        if(Values.size() == 3) {
            higher = Combination.two_pairs;
            for(Integer i : Values.values())
                if(i == 3)
                    higher = Combination.three_of_a_kind;
        }

        if(check_straight())
            higher = Combination.straight;

        if(Suits.size() == 1) {
            higher = Combination.flush;
        }
        if(Values.size() == 2){
            higher = Combination.full_house;
            for(Integer i : Values.values())
                if(i == 4)
                    higher = Combination.four_of_a_kind;
        }

        if(higher == Combination.flush && check_straight())
            higher = Combination.straight_flush;
    }

    public Combination getHigher() {
        return higher;
    }

    private boolean check_straight(){ //Method that checks if cards in hand are "in straight"
        if(Values.size() == 5){
            Object[] values = Values.keySet().toArray();
            Arrays.sort(values);
            if((Integer)values[0] + 4 == (Integer) values[4])
                return true;
            if((Integer)values[4] == 14 && (Integer)values[0] == 2 && (Integer)values[3] == 5)
                return true;
        }
        return false;
    }

    public Hand addNewCard(Card newCard, int instead){ //returns new hand, that is made by replacing one of the card with new one
        Card[] cards = this.cards.clone();
        for(int i = instead; i < cards.length - 1; i++)
            cards[i] = cards[i + 1];
        cards[4] = newCard;
        return new Hand(cards);
    }


}
