/**
 * 4 suits.
 * 4 higherachy of cards
 * each type idenified
 * prints each card.
 * effectively works and is randomized kinda
 */
public class Card {
    public static String suit;
    public static int num;
    public static String high;
    private static String[] suits = {"SPADES","DIAMONDS","CLUBS","HEARTS"};
    private static int[] nums = {1,2,3,4,5,6,7,8,9,10,11,12,13,14};
    private static String[] h = {"JACK","QUEEN","KING","ACE"};
    /**random card constuctor*/
    public Card(){ suit=randomS();num =randomN();new Card(suit,num);}
    /**card num and suit*/
    public Card(String s, int n){if (n>0&&n<15){num=n;new Card(n);}else{num=randomN();}
      if(s.toUpperCase()=="SPADES"||s.toUpperCase()=="DIAMONDS"||s.toUpperCase()=="CLUBS"||s.toUpperCase()=="HEARTS"){
        suit=s.toUpperCase();}else{suit=randomS();} }
    /**high card declaration*/
    public Card(int n){if(n>10&&n<15){if(n==11){high=h[0];num=n;}if(n==12){high=h[1];num=n;}
        if(n==13){high=h[2];num=n;}if(n==14){high=h[3];num=n;}}else{if(n>0&&n<11){num=n;}else{num=randomN();}}
    }
    /**random number*/
    public static int randomN(){num=nums[(int)(Math.random()*13)+1];return num;}
    /**random suit i love this portin it works perfectly*/
    public static String randomS(){suit=suits[(int)(Math.random()*3)+1];return suit;}
    /** declaring new cards with suit parameter*/
    public static String newCard(String s,int n){new Card(s,n);return string();}
     /** declaring new cards with jack parameter if*/
    public static String newCard(boolean j){if(j==true){new Card(11);suit=randomS();}return string();}
    /** declaring new cards with number parameter*/
    public static String newCard(int n){new Card(n);return string();}
    /** declaring new cards*/
    public static String newCard(){new Card();return string();}
    /**to string method*/
    public static String string(){if(num>10){return high+" of "+randomS();}else return num+" of "+randomS();}
    
}
