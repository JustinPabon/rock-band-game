/*
Made By: Justin Pabon
*/

public class RockBandChar{
  private String name, role;
  private String[] attacks = new String[4];
  private int[] maxDamage = new int[4];
  private int health, base, win;

  //Constructors
  public RockBandChar(){
    name = "";
    role = "";
  }
  public RockBandChar(String myName, String myRole, String attack1, String attack2, String attack3, String attack4){
    name = myName;
    role = myRole;

    attacks[0] = attack1;
    attacks[1] = attack2;
    attacks[2] = attack3;
    attacks[3] = attack4;

    win = 0;
  }

  public void setWin(int result){
    win = result;
  }

  public int getWin(){
    return win;
  }

  public void setHP(int myHP){
    health = myHP;
  }

  public int getHP(){
    return health;
  }

  public void setDamageBase(int damage){
    base = damage;
  }

  public int getDamageBase(){
    return base;
  }

  public void setMaxDamage(int ind, int max){
    maxDamage[ind] = max;
  }

  public int getMaxDamage(int ind){
    return maxDamage[ind];
  }

  public String getName(){
    return name;
  }

  public String getRole(){
    return role;
  }

  public String getattack(int choice){
    return attacks[choice];
  }

}
