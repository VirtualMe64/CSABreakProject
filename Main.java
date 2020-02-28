import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;

/**
 * Controls game flow, file which is actually run
 * 
 * @author Sammy Taubman
 * @version 2/26/20
 */ 

class Main {
  
  public static int search(String[] list, String value) {
    for (int i = 0; i < list.length; i++) {
      if (list[i].equals(value)) {
        return i;
      }
    }
    return -1;
  }
  
  public static void drawAll(Player p1, Player p2, String[] filePaths, Deck playedDeck, int turn) {
    StdDraw.setPenColor(StdDraw.BLACK);
    StdDraw.picture(0.1, 0.5, filePaths[search(filePaths, "/Users/9259913/Documents/BreakProject/cards/back1.GIF")], 90);
    StdDraw.picture(0.9, 0.5, filePaths[search(filePaths, "/Users/9259913/Documents/BreakProject/cards/back1.GIF")], 270);
    StdDraw.text(0.1, 0.5, "" + p1.cardsLeft());
    StdDraw.text(0.9, 0.5, "" + p2.cardsLeft());
    StdDraw.text(0.1, 0.58, p1.toString());
    StdDraw.text(0.9, 0.58, p2.toString());
    StdDraw.text(0.5, 0.62, "" + playedDeck.size());
    StdDraw.setPenColor(StdDraw.GREEN);
    if (turn == 0) {
      StdDraw.filledCircle(0.1, 0.2, 0.03);
    }
    if (turn == 1) {
      StdDraw.filledCircle(0.9, 0.2, 0.03);
    }
    StdDraw.setPenColor(StdDraw.BLACK);
  }
  
  public static void drawAll(Player p1, Player p2, String[] filePaths, Deck playedDeck, Card topCard, int turn) {
    drawAll(p1, p2, filePaths, playedDeck, turn);
    StdDraw.picture(0.5, 0.5, filePaths[search(filePaths, "/Users/9259913/Documents/BreakProject/cards/" + topCard.rank() + topCard.suit() + ".GIF")]);
  }
  
  public static void main(String[] args) {
    StdDraw.enableDoubleBuffering();
    String path = "/Users/9259913/Documents/BreakProject/cards";
    File[] files = new File("/Users/9259913/Documents/BreakProject/cards").listFiles();
    String[] filePaths = new String[files.length];
    for (int i = 0; i < filePaths.length; i++) {
      filePaths[i] = files[i].toString();
    }
    Scanner scan = new Scanner(System.in);
    Deck playedDeck = new Deck(); // The deck of cards whcih have already been played
    playedDeck.clear();
    Deck tempDeck = new Deck();
    Deck deck1 = new Deck(tempDeck.subList(0, 26));
    Deck deck2 = new Deck(tempDeck.subList(26, 52));
    boolean done = false;
    boolean jackOnTop = false;
    
    StdDraw.text(0.5, 0.5, "P1, enter your name in the console");
    StdDraw.show();
    String name = scan.nextLine();
    Player p1 = new Player(deck1, name, 'q', 'w');
    StdDraw.clear();
    StdDraw.text(0.5, 0.5, "P2, enter your name in the console");
    StdDraw.show();
    name = scan.nextLine();
    Player p2 = new Player(deck2, name, 'n', 'm');
    StdDraw.clear();
    StdDraw.text(0.1, 0.5, "" + p1.cardsLeft());
    StdDraw.text(0.9, 0.5, "" + p2.cardsLeft());
    ArrayList<Player> playerList = new ArrayList<Player>();
    playerList.add(p1);
    playerList.add(p2);
    int turn = 0; // whose turn it is
    Card topCard = new Card("Temp", "Temp");
    
    drawAll(p1, p2, filePaths, playedDeck, turn);
    StdDraw.show();
    
    while (!done) { // main game loop
      Player currPlayer = playerList.get(turn);
      if (StdDraw.isKeyPressed(currPlayer.playKey() - 32)) {
        jackOnTop = false;
        turn += 1;
        turn %= playerList.size();
        Card playedCard = currPlayer.play();    
        playedDeck.add(playedCard);
        topCard = playedCard;
        StdDraw.clear();
        drawAll(p1, p2, filePaths, playedDeck, turn);
        StdDraw.picture(0.5, 0.5, filePaths[search(filePaths, "/Users/9259913/Documents/BreakProject/cards/" + playedCard.rank() + playedCard.suit() + ".GIF")]);
        StdDraw.show();
        if (playedCard.rank().equals("jack")) {
          jackOnTop = true;
        }
        System.out.println("The card is a " + playedCard);
      }
      
      for (Player p : playerList) {
        if (StdDraw.isKeyPressed(p.callKey() - 32)) {
          if (jackOnTop) {
            p.addCards(playedDeck);
            playedDeck.clear();
            StdDraw.clear();
            StdDraw.text(0.5, 0.9, "Nice reaction time! " + p.toString() + " called the jack and got the pile!");
            System.out.println("Nice reaction time! " + p.toString() + " called the jack and got the pile!");
            jackOnTop = false;
            drawAll(p1, p2, filePaths, playedDeck, turn);
            StdDraw.show();
            StdDraw.pause(1000);
          } else {
            if (p.cardsLeft() == 0) {
              done = true;
              int winnerIndex = (playerList.indexOf(p) + 1) % playerList.size();
              System.out.println(playerList.get(winnerIndex) + " wins!");
              break;
            }              
            int nextPlayerIndex = (playerList.indexOf(p) + 1) % playerList.size();
            playerList.get(nextPlayerIndex).addCard(p.play());
            StdDraw.clear();
            StdDraw.text(0.5, 0.9, "Too fast! Your top card goes to " + playerList.get(nextPlayerIndex));
            System.out.println("Too fast! Your top card goes to " + playerList.get(nextPlayerIndex));   
            drawAll(p1, p2, filePaths, playedDeck, topCard, turn);
            StdDraw.show();
            StdDraw.pause(1000);
          }
          StdDraw.pause(200);
        }
        if (p.cardsLeft() == 0) {
          done = true;
          int winnerIndex = (playerList.indexOf(p) + 1) % playerList.size(); 
          StdDraw.clear();
          StdDraw.text(0.5, 0.9, playerList.get(winnerIndex) + " wins!");
          System.out.println(playerList.get(winnerIndex) + " wins!");
          drawAll(p1, p2, filePaths, playedDeck, topCard, turn);
          StdDraw.show();
          break;
        }   
      }
      StdDraw.pause(20);
    }
  }
  }