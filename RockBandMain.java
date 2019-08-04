/*
RPG of members of rock bands battling one another.
Made By: Justin Pabon
*/

import java.io.File;
import java.util.Scanner;

public class RockBandMain{
  public static void main(String[] args) throws Exception{
    RockBandPlay manage = new RockBandPlay();

    //Object array for each type of character.
    RockBandChar[] memberV = new RockBandChar[10];
    RockBandChar[] memberG = new RockBandChar[10];
    RockBandChar[] memberB = new RockBandChar[10];
    RockBandChar[] memberD = new RockBandChar[10];

    RockBandChar player = new RockBandChar();
    RockBandChar opponent = new RockBandChar();

    //Index counters for each type of character.
    int v = 0, g = 0, b = 0, d = 0;

    int play, charChoice;

    File file = new File("characters.txt");
    Scanner sc = new Scanner(file);

    //Generate playable characters from characters.txt
    while(sc.hasNextLine()){
      String[] tokens = sc.nextLine().split(" ");

      if(tokens[1].compareTo("V") == 0){
        memberV[v] = new RockBandChar(tokens[0], "Vocalist", tokens[2], tokens[3], tokens[4], tokens[5]);
        v++;
      }

      else if(tokens[1].compareTo("G") == 0){
        memberG[g] = new RockBandChar(tokens[0], "Guitarist", tokens[2], tokens[3], tokens[4], tokens[5]);
        g++;
      }

      else if(tokens[1].compareTo("B") == 0){
        memberB[b] = new RockBandChar(tokens[0], "Bassist", tokens[2], tokens[3], tokens[4], tokens[5]);
        b++;
      }

      else if(tokens[1].compareTo("D") == 0){
        memberD[d] = new RockBandChar(tokens[0], "Drummer", tokens[2], tokens[3], tokens[4], tokens[5]);
        d++;
      }
    }

    System.out.println();
    System.out.println("****************************************************************************************************************");
    System.out.println("Welcome to BATTLE OF THE BANDS; an RPG style RBG. There are "
    + v + " vocalists, " + g + " guitarists, " + b + " bassists and " + d + " drummers."
    + " Choose a band member to fight against other band members.");
    System.out.println("****************************************************************************************************************");

    play = manage.startGame();

    //Level One
    if(play == 1){
      player = manage.charSelect(memberV, memberG, memberB, memberD, v, g, b, d);
      opponent = manage.oppSelect(memberV, memberG, memberB, memberD, v, g, b, d);
      while(opponent.getName().compareTo(player.getName()) == 0) opponent = manage.oppSelect(memberV, memberG, memberB, memberD, v, g, b, d);
      System.out.println("The wild " + opponent.getRole() + ", " + opponent.getName() + " appeared.");

      manage.level1(player, opponent);

      if(player.getWin() == 0) play = 0;
      else play = manage.keepPlaying();
    }

    //Level Two
    if(play == 1){
      player = manage.charSelect(memberV, memberG, memberB, memberD, v, g, b, d);
      opponent = manage.oppSelect(memberV, memberG, memberB, memberD, v, g, b, d);
      while(opponent.getName().compareTo(player.getName()) == 0) opponent = manage.oppSelect(memberV, memberG, memberB, memberD, v, g, b, d);
      System.out.println("The wild " + opponent.getRole() + ", " + opponent.getName() + " appeared.");

      manage.level2(player, opponent);

      if(player.getWin() == 0) play = 0;
      else play = manage.keepPlaying();
    }

    //Level Three
    if(play == 1){
      player = manage.charSelect(memberV, memberG, memberB, memberD, v, g, b, d);
      opponent = manage.oppSelect(memberV, memberG, memberB, memberD, v, g, b, d);
      while(opponent.getName().compareTo(player.getName()) == 0) opponent = manage.oppSelect(memberV, memberG, memberB, memberD, v, g, b, d);
      System.out.println("The wild " + opponent.getRole() + ", " + opponent.getName() + " appeared.");

      manage.level3(player, opponent);

      if(player.getWin() == 0) play = 0;
      else play = manage.keepPlaying();
    }

    //Advanced Levels!
    while(play == 1){
      player = manage.charSelect(memberV, memberG, memberB, memberD, v, g, b, d);

      opponent = manage.oppSelect(memberV, memberG, memberB, memberD, v, g, b, d);
      while(opponent.getName().compareTo(player.getName()) == 0) opponent = manage.oppSelect(memberV, memberG, memberB, memberD, v, g, b, d);
      System.out.println("The wild " + opponent.getRole() + ", " + opponent.getName() + " appeared.");

      manage.advancedLevel(player, opponent);

      if(player.getWin() == 0) play = 0;
      else play = manage.keepPlaying();
    }

    if(play == 0){
      System.out.println();
      System.out.println("Game Over. See ya!");
    }
  }
}
