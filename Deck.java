/**
 * need to generate a 1-14 cards in each suit.
 * 4 jacks each.
 * 4 of every card though.
 * deal the cards.
 * shuffel the cards.
 * dont copy cards. when you do run out of cards each hand isnt the same.
 * step1 : 4 jacks
 */
public class Deck{
  public static String[] card=new String[52];
  public Deck(){
    card[0]= Card.newCard(true);
    card[1]= Card.newCard(true);
    card[2]= Card.newCard(true);
    card[3]= Card.newCard(true);
  }
  public static boolean newDeck(){new Deck();return true;}
  public static String string(){return card[0]+" "+card[1]+" "+card[2]+" "+card[3]+" ";}

}
    