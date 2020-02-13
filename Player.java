import java.util.ArrayList;

/**
 * One player in the game
 * Has a deck which cards can be added to or played from
 */ 

class Player{
  /** Deck of cards in the player's pile */
  private Deck deck;
  /** Name of the player */
  private String name;
  /** The key the player will press to play their card */
  private char playKey;
  /** The key the player will press to call a jack */
  private char callKey;
  
  /**
   * Create a new player
   * @param deck the deck of cards the player starts with
   * @param playKey The key the player will press to play their card
   * @param callKey The key the player will press to call a jack
   */ 
  public Player(Deck deck, String name, char playKey, char callKey) {
    this.deck = deck;
    this.name = name;
    this.playKey = playKey;
    this.callKey = callKey;
  }
  
  /**
   * Remove and return the top card of the deck (index 0)
   * @returns The card which was played
   */
  public Card play() {
    Card cardToRemove = deck.get(0);
    deck.remove(0);
    return cardToRemove;
  }
  
  /**
   * Adds a deck of cards to the bottom of the player's deck
   * @param deck The cards being added to the deck
   */ 
  public void addCards(Deck deck) {
    for (Card c : deck) {
      this.deck.add(c);
    }
  }
  
  /**
   * Accessor method for number of cards in deck
   * @returns number of cards left in the player's deck
   */ 
  public int cardsLeft() {
    return deck.size();
  }
  
  /**
   * Accessor method for the player's play key
   * @return The player's play key
   */
  public char playKey() {
    return playKey;
  }
  
    /**
   * Accessor method for the player's ca;; key
   * @return The player's call key
   */
  public char callKey() {
    return callKey;
  }
  
  public String toString() {
    return name;
  }
}