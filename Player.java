import java.util.ArrayList;

class Player{
  Deck deck;
  
  public Player(Deck deck) {
    this.deck = deck;
  }
  
  public Card play() {
    Card cardToRemove = deck.get(0);
    deck.remove(0);
    return cardToRemove;
  }
  
  public void addCards(Deck deck) {
    for (Card c : deck) {
      this.deck.add(c);
    }
  }
}