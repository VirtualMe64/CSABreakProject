import java.util.Scanner;
import java.util.ArrayList;

class Main {
  public static void main(String[] args) {
    Scanner scan = new Scanner(System.in);
    Deck playedDeck = new Deck(); // The deck of cards whcih have already been played
    boolean done = false;
    
    String name = scan.nextLine();
    Player p1 = new Player(deck1, name, 'q', 'w');
    name = scan.nextLine();
    Player p2 = new Player(deck2, name, ',', '.');
    ArrayList<Player> playerList = new ArrayList<Player>();
    playerList.add(p1);
    playerList.add(p2);
    int turn = 0; // whose turn it is
    
    while (!done) { // main game loop
      Player currPlayer = playerList.get(turn);
      boolean jackOnTop = false;
      if (StdDraw.isKeyPressed(currPlayer.playKey())) {
        Card playedCard = currPlayer.play();
        playedDeck.add(playedCard);
        if (playedCard.name().equals("Jack")) {
          jackOnTop = true;
        }
        System.out.println("The card is a " + playedCard);
      }
      
      for (Player p : playerList) {
        if (StdDraw.isKeyPressed(p.callKey())) {
          if (jackOnTop) {
            p.addCards(playedDeck);
            playedDeck.clear();
            System.out.println("Nice reaction time! You called the jack and got the pile!");
          } else {
            if (p.cardsLeft() == 0) {
              done = true;
              int winnerIndex = (playerList.indexOf(p) + 1) % playerList.size();
              System.out.println(playerList.get(winnerIndex) + " wins!");
              break;
            }              
            int nextPlayerIndex = (playerList.indexOf(p) + 1) % playerList.size();
            playerList.get(nextPlayerIndex).add(p.play());
            System.out.println("Too fast! Your top card goes to " + playerList.get(nextPlayerIndex));
          }
        }
        if (p.cardsLeft() == 0) {
          done = true;
          int winnerIndex = (playerList.indexOf(p) + 1) % playerList.size();       
          System.out.println(playerList.get(winnerIndex) + " wins!");
          break;
        }   
      }
      turn += 1;
      if (turn >= playerList.size()) {
        turn = 0;
      }    
      StdDraw.pause(20);
    }
  }
}