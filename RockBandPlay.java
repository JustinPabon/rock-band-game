import java.util.Scanner;
import java.util.Random;

/*
Operates game play.
Made By: Justin Pabon
*/
public class RockBandPlay{

  Scanner in = new Scanner(System.in);
  Random rand = new Random();
  private int choiceStart;
  private char choiceChar;
  private int choiceIndex;
  private int yourMove, damage, playerTotal, opponentTotal, level = 4;

  //Start Game
  public int startGame(){
    System.out.println();
    System.out.println("Ready to play? (1)Yes (0)No");
    choiceStart = in.nextInt();

    while(choiceStart != 1 && choiceStart != 0){
      System.out.println();
      System.out.println("Choice is not recognized."
      + " Ready to play? (1)Yes (0)No");
      choiceStart = in.nextInt();
    }

    return choiceStart;
  }

  //Commence fighting!!!
  public void fight(RockBandChar player, RockBandChar opponent){
    System.out.println();
    System.out.println("It's time to BATTLE! What are you going to do?");

    while(player.getHP() > 0 && opponent.getHP() > 0){
      /*Player's move.*/
      for(int i = 0; i < 4; i++){
        if(i != 3) System.out.print((i + 1) +") " + player.getattack(i) + "(" + player.getDamageBase() + "-" + player.getMaxDamage(i) + ")" + "\t");
        else System.out.println((i + 1) +") " + player.getattack(i) + "(" + player.getDamageBase() + "-" + player.getMaxDamage(i) + ")");
      }
      yourMove = in.nextInt() - 1;
      while(yourMove < 0 || yourMove > 3){
        System.out.println();
        System.out.println("That move doesn't exist. Choose again.");
        yourMove = in.nextInt() - 1;
      }

      System.out.println();
      System.out.println("->" + player.getName() + " used " + player.getattack(yourMove) + ".");

      damage = rand.nextInt((player.getMaxDamage(yourMove) - player.getDamageBase()) + 1) + player.getDamageBase();
      System.out.println("It inflicted " + damage + " damage.");

      if(damage >= (opponentTotal / 5)) System.out.println("That was INSANE!!!");
      else if(damage <= (player.getDamageBase() + 5)) System.out.println("Well that was lame...");

      opponent.setHP(opponent.getHP() - damage);
      if(opponent.getHP() <= 0) break;

      /*Opponent's move.*/
      yourMove = rand.nextInt(4);
      System.out.println("->" + opponent.getName() + " used " + opponent.getattack(yourMove) + ".");

      damage = rand.nextInt((opponent.getMaxDamage(yourMove) - opponent.getDamageBase()) + 1) + opponent.getDamageBase();
      System.out.println("It inflicted " + damage + " damage.");

      if(damage >= (playerTotal / 5)) System.out.println("That was INSANE!!!");
      else if(damage <= (opponent.getDamageBase() + 5 )) System.out.println("Well that was lame...");

      player.setHP(player.getHP() - damage);
      if(player.getHP() <= 0) break;

      System.out.println();
      System.out.println(player.getName() + ": " + player.getHP() + "/" + playerTotal);
      System.out.println(opponent.getName() + ": " + opponent.getHP() + "/" + opponentTotal);

      System.out.println();
      System.out.println(" What are you going to do?");
    }

    //Output Results
    System.out.println();
    System.out.println(player.getName() + ": " + player.getHP() + "/" + playerTotal);
    System.out.println(opponent.getName() + ": " + opponent.getHP() + "/" + opponentTotal);

    if(opponent.getHP() <= 0){
      System.out.println(opponent.getName() + " has been defeated. " + player.getName() + " WINS!!!");
      player.setWin(1);
    }

    else if(player.getHP() <= 0){
      System.out.println(player.getName() + " has been defeated. " + opponent.getName() + " is the winner.");
      player.setWin(0);
    }
  }

  //Level One Battle.
  /*
  Have each RockBandChar object have the same base attack integer and each
  level sets a random maximum damage integer for each attack.
  Also consider pp variable to limit how many times a player can use the
  same move.
  */
  public void level1(RockBandChar player, RockBandChar opponent){

    System.out.println();
    System.out.println("***** \\m/ LEVEL 1 \\m/ *****");
    //Player and opponent's health.
    player.setHP(rand.nextInt((150 - 100) + 1) + 100);
    opponent.setHP(player.getHP());
    playerTotal = player.getHP(); opponentTotal = opponent.getHP();


    System.out.println(player.getName() + ": " + player.getHP());
    System.out.println(opponent.getName() + ": " + opponent.getHP());

    //Set up the strength of the opponent and player's moves.
    player.setDamageBase(10);
    opponent.setDamageBase(player.getDamageBase());
    for(int i = 0; i < 4; i++){
      int highestMax = rand.nextInt(5) + 1; //System.out.println(highestMax);
      //System.out.println(i);
      player.setMaxDamage(i, rand.nextInt(( (player.getDamageBase() + (5 * highestMax)) - player.getDamageBase()) + 1) + player.getDamageBase());
      //System.out.println(player.getMaxDamage(i) + "****");
      opponent.setMaxDamage(i, player.getMaxDamage(i));
    }

    fight(player, opponent);
  }

  //Level Two Battle
  public void level2(RockBandChar player, RockBandChar opponent){

    System.out.println();
    System.out.println("***** \\m/ LEVEL 2 \\m/ *****");

    //Player and opponent's health.
    player.setHP(rand.nextInt((200 - 150) + 1) + 150);
    opponent.setHP(rand.nextInt((200 - 150) + 1) + 150);
    playerTotal = player.getHP(); opponentTotal = opponent.getHP();

    System.out.println(player.getName() + ": " + player.getHP());
    System.out.println(opponent.getName() + ": " + opponent.getHP());

    //Set up the strength of the opponent and player's moves.
    player.setDamageBase(25);
    opponent.setDamageBase(rand.nextInt((30 - 20) + 1) + 20); //Base ranges from 20 to 30.
    for(int i = 0; i < 4; i++){
      int highestMax = rand.nextInt(5) + 1;
      //System.out.println(i);
      //player.setMaxDamage(i, rand.nextInt(( (player.getDamageBase() + (5 * highestMax)) - player.getDamageBase()) + 1) + player.getDamageBase());
      player.setMaxDamage(i, player.getDamageBase() + (5 * highestMax));
      //System.out.println(player.getMaxDamage(i) + "****");
      //Opponent's Max damage plus 5 to opponent's max damage plus 25
      opponent.setMaxDamage(i, rand.nextInt(( (opponent.getDamageBase() + 25) - (opponent.getDamageBase() + 5)) + 1) + (opponent.getDamageBase() + 5));
    }

    fight(player, opponent);
  }

  //Level Three Battle
  public void level3(RockBandChar player, RockBandChar opponent){

    System.out.println();
    System.out.println("***** \\m/ LEVEL 3 \\m/ *****");

    //Player and opponent's health.
    player.setHP(rand.nextInt((300 - 200) + 1) + 200);
    opponent.setHP(rand.nextInt((300 - 200) + 1) + 200);
    playerTotal = player.getHP(); opponentTotal = opponent.getHP();

    System.out.println(player.getName() + ": " + player.getHP());
    System.out.println(opponent.getName() + ": " + opponent.getHP());

    //Set up the strength of the opponent and player's moves.
    player.setDamageBase(40);
    opponent.setDamageBase(rand.nextInt((50 - 30) + 1) + 30); //Base ranges from 30 to 50.
    for(int i = 0; i < 4; i++){
      int highestMax = rand.nextInt(5) + 3;
      //System.out.println(i);
      //player.setMaxDamage(i, rand.nextInt(( (player.getDamageBase() + (5 * highestMax)) - player.getDamageBase()) + 1) + player.getDamageBase());
      player.setMaxDamage(i, player.getDamageBase() + (5 * highestMax));
      //System.out.println(player.getMaxDamage(i) + "****");
      //Opponent's Max damage plus 15 to opponent's max damage plus 40
      opponent.setMaxDamage(i, rand.nextInt(( (opponent.getDamageBase() + 40) - (opponent.getDamageBase() + 15)) + 1) + (opponent.getDamageBase() + 15));
    }

    fight(player, opponent);
  }

  public void advancedLevel(RockBandChar player, RockBandChar opponent){
    System.out.println();
    System.out.println("***** \\m/ LEVEL " + level + " \\m/ *****");

    //Player and opponent's health.
    player.setHP(rand.nextInt((500 - 350) + 1) + 350);
    opponent.setHP(rand.nextInt((500 - 350) + 1) + 350);
    playerTotal = player.getHP(); opponentTotal = opponent.getHP();

    System.out.println(player.getName() + ": " + player.getHP());
    System.out.println(opponent.getName() + ": " + opponent.getHP());

    //Set up the strength of the opponent and player's moves.
    player.setDamageBase(rand.nextInt((50 - 30) + 1) + 30); //Base ranges from 30 to 50.
    opponent.setDamageBase(rand.nextInt((50 - 30) + 1) + 30);
    for(int i = 0; i < 4; i++){
      //int highestMax = rand.nextInt(5) + 2;
      //System.out.println(i);
      player.setMaxDamage(i, rand.nextInt(( (player.getDamageBase() + 50) - (player.getDamageBase() + 25)) + 1) + (player.getDamageBase() + 25));
      //System.out.println(player.getMaxDamage(i) + "****");
      //Opponent's Max damage plus 5 to opponent's max damage plus 30
      opponent.setMaxDamage(i, rand.nextInt(( (opponent.getDamageBase() + 50) - (opponent.getDamageBase() + 25)) + 1) + (opponent.getDamageBase() + 25));
    }

    fight(player, opponent);

    level++;
  }

  //Continue game play.
  public int keepPlaying(){
    System.out.println();
    System.out.println("Continue? (1) Yes (0) No");
    choiceStart = in.nextInt();

    while(choiceStart != 1 && choiceStart != 0){
      System.out.println();
      System.out.println("Choice is not recognized."
      + " Continue? (1)Yes (0)No");
      choiceStart = in.nextInt();
    }

    return choiceStart;
  }

  //Choose character
  public RockBandChar charSelect(RockBandChar[] voc, RockBandChar[] gui, RockBandChar[] bas, RockBandChar[] dru, int v, int g, int b, int d){
    int playerChar;
    RockBandChar[] bandRole = new RockBandChar[10];

    System.out.println();
    System.out.println("Rock on! First choose what band role you want to have"
    + "(v)Vocals (g)Guitarist (b)Bassist or (d)Drummer.");

    choiceChar = in.next(".").charAt(0);
    while(choiceChar != 'v' && choiceChar != 'g' && choiceChar != 'b' && choiceChar != 'd'){
      System.out.println();
      System.out.println("Uh, that's not a real band role. Choose again.");
      choiceChar = in.next(".").charAt(0);
    }

    switch(choiceChar){
      case 'v':

      bandRole = voc;
      System.out.println();
      System.out.println("Vocalists:");
      for(int i = 0; i < v; i++){
        System.out.println((i + 1) +") " + voc[i].getName());
      }

      choiceIndex = in.nextInt();
      while(choiceIndex < 1 || choiceIndex > v){
        System.out.println();
        System.out.println("That band member doesn't exist. Choose again.");
        choiceIndex = in.nextInt();
      }
      break;

      case 'g':

      bandRole = gui;
      System.out.println();
      System.out.println("Guitarists:");
      for(int i = 0; i < g; i++){
        System.out.println((i + 1) +") " + gui[i].getName());
      }

      choiceIndex = in.nextInt();
      while(choiceIndex < 1 || choiceIndex > g){
        System.out.println();
        System.out.println("That band member doesn't exist. Choose again.");
        choiceIndex = in.nextInt();
      }
      break;

      case 'b':

      bandRole = bas;
      System.out.println();
      System.out.println("Bassists:");
      for(int i = 0; i < b; i++){
        System.out.println((i + 1) +") " + bas[i].getName());
      }

      choiceIndex = in.nextInt();
      while(choiceIndex < 1 || choiceIndex > b){
        System.out.println();
        System.out.println("That band member doesn't exist. Choose again.");
        choiceIndex = in.nextInt();
      }
      break;

      default:

      bandRole = dru;
      System.out.println();
      System.out.println("Drummers:");
      for(int i = 0; i < v; i++){
        System.out.println((i + 1) +") " + dru[i].getName());
      }

      choiceIndex = in.nextInt();
      while(choiceIndex < 1 || choiceIndex > d){
        System.out.println();
        System.out.println("That band member doesn't exist. Choose again.");
        choiceIndex = in.nextInt();
      }
    }

    System.out.println();
    System.out.println("You chose " + bandRole[choiceIndex - 1].getRole() + ", " + bandRole[choiceIndex - 1].getName());

    return bandRole[choiceIndex - 1];
  }

  //Generate player's opponent randomly.
  public RockBandChar oppSelect(RockBandChar[] voc, RockBandChar[] gui, RockBandChar[] bas, RockBandChar[] dru, int v, int g, int b, int d){
    //System.out.println("Choosing opponent...");

    int enemyRole = rand.nextInt(4);

    RockBandChar[] bandRole = new RockBandChar[10];
    if(enemyRole == 0) return voc[rand.nextInt(v)];
    else if(enemyRole == 1) return gui[rand.nextInt(g)];
    else if(enemyRole == 2) return bas[rand.nextInt(b)];
    else return dru[rand.nextInt(d)];
  }

}
